package com.bluedogcare.gerenciamento.service;

import com.bluedogcare.gerenciamento.model.Tutor;
import com.bluedogcare.gerenciamento.repository.TutorRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    public Tutor buscaPorId(int id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tutor não encontrado!"));
    }

    public List<Tutor> buscarPorNome(String busca) {
        return tutorRepository.findByNomeContaining(busca);
    }

    public Tutor salvar(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public void excluir(int id) {
        tutorRepository.deleteById(id);
    }

    public void atualizar(int id, Tutor tutorAtualizado) {
        Tutor tutorExistente = tutorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tutor não encontrado"));

        tutorExistente.setNome(tutorAtualizado.getNome());
        tutorExistente.setCpf(tutorAtualizado.getCpf());
        tutorExistente.setEndereco(tutorAtualizado.getEndereco());
        tutorExistente.setTelefone(tutorAtualizado.getTelefone());

        tutorRepository.save(tutorExistente);
    }
}
