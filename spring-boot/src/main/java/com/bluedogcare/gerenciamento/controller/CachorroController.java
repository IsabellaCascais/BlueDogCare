package com.bluedogcare.gerenciamento.controller;

import com.bluedogcare.gerenciamento.model.Cachorro;
import com.bluedogcare.gerenciamento.model.Tutor;
import com.bluedogcare.gerenciamento.service.CachorroService;
import com.bluedogcare.gerenciamento.service.TutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CachorroController {

    @Autowired
    private CachorroService cachorroService;

    @Autowired
    private TutorService tutorService;

    @GetMapping("/cadastrar-cachorro/{tutorId}")
    public String exibirFormCachorro(@PathVariable int tutorId, Model model) {

        Tutor tutor = tutorService.buscaPorId(tutorId);

        if (tutor == null) {
            return "redirect:/listar-tutor";
        }

        Cachorro cachorro = new Cachorro();
        cachorro.setTutor(tutor);

        model.addAttribute("tutor", tutor);
        model.addAttribute("cachorro", cachorro);
        return "cadastrar-cachorro";
    }

    @PostMapping("/cadastrar-cachorro/{tutorId}")
    public String confirmaCachorro(@PathVariable int tutorId, @ModelAttribute Cachorro novoCachorro) {

        Tutor tutor = tutorService.buscaPorId(tutorId);

        if (tutor == null) {
            System.out.println("\n\nErro: Tutor não encontrado para ID " + tutorId);
            return "redirect:/listar-tutor";
        }

        novoCachorro.setTutor(tutor);
        cachorroService.salvar(novoCachorro);

        return "confirma-cachorro";
    }

    @GetMapping("/listar-cachorro")
    public String listarCachorros(@RequestParam(value = "busca", required = false) String busca, Model model) {

        List<Cachorro> cachorros;

        if (busca != null && !busca.isEmpty()) {
            cachorros = cachorroService.buscarPorNome(busca);
        } else {
            cachorros = cachorroService.listarTodos();
        }
        model.addAttribute("cachorros", cachorros);
        return "listar-cachorro";
    }

    @GetMapping("/detalhes-cachorro/{id}")
    public String detalhesCachorro(@PathVariable int id, Model model) {

        Cachorro cachorro = cachorroService.buscaPorId(id);
        if (cachorro != null) {
            model.addAttribute("cachorro", cachorro);
            return "detalhes-cachorro";
        }
        return "redirect:/listar-cachorro";
    }

    @GetMapping("/editar-cachorro/{id}")
    public String editarCachorro(@PathVariable int id, Model model) {
        
        Cachorro cachorro = cachorroService.buscaPorId(id);
        model.addAttribute("cachorro", cachorro);
        return "editar-cachorro";  // Agora aponta para a tela de edição correta
    }

    @PostMapping("/editar-cachorro/{id}")
    public String atualizarCachorro(@PathVariable int id, @ModelAttribute Cachorro cachorro) {
        
        cachorroService.atualizar(id, cachorro);
        return "redirect:/listar-cachorro";
    }

    @GetMapping("/excluir-cachorro/{id}")
    public String excluirCachorro(@PathVariable int id) {

        cachorroService.excluir(id);
        return "redirect:/listar-cachorro";
    }
}
