package com.bluedogcare.gerenciamento.service;

import com.bluedogcare.gerenciamento.model.Cachorro;
import com.bluedogcare.gerenciamento.model.Tutor;
import com.bluedogcare.gerenciamento.repository.CachorroRepository;
import com.bluedogcare.gerenciamento.repository.TutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CachorroService {

    @Autowired
    private CachorroRepository cachorroRepository;

    @Autowired
    private TutorRepository tutorRepository;
    
    public List<Cachorro> listarTodos() {
        return cachorroRepository.findAll();
    }

    public Cachorro buscaPorId(int id) {
        return cachorroRepository.findById(id).orElse(null);
    }

    public List<Cachorro> buscarPorNome(String busca) {
        return cachorroRepository.findByNomeContaining(busca);
    }

    public Cachorro salvar(Cachorro cachorro) {
        return cachorroRepository.save(cachorro);
    }

    public void excluir(int id) {
        cachorroRepository.deleteById(id);
    }

    public void atualizar(int id, Cachorro cachorroAtualizado) {
        
        Cachorro cachorroExistente = cachorroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cachorro não encontrado"));
        
        Tutor tutor = tutorRepository.findById(cachorroAtualizado.getTutor().getId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado!"));

        cachorroExistente.setNome(cachorroAtualizado.getNome());
        cachorroExistente.setSexo(cachorroAtualizado.getSexo());
        cachorroExistente.setRaca(cachorroAtualizado.getRaca());
        cachorroExistente.setPorte(cachorroAtualizado.getPorte());
        cachorroExistente.setCastrado(cachorroAtualizado.getCastrado());
        cachorroExistente.setDataNasc(cachorroAtualizado.getDataNasc());
        cachorroExistente.setObs(cachorroAtualizado.getObs());
        cachorroExistente.setTutor(tutor);

        cachorroRepository.save(cachorroExistente);
    }
}
