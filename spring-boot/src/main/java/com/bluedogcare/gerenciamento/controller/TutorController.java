package com.bluedogcare.gerenciamento.controller;

import com.bluedogcare.gerenciamento.model.Cachorro;
import com.bluedogcare.gerenciamento.model.Tutor;
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
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/manutencao")
    public String manutencao() {
        return "manutencao";
    }

    @GetMapping("/cadastrar-tutor")
    public String exibirFormTutor(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "cadastrar-tutor";
    }

    @PostMapping("/cadastrar-tutor")
    public String confirmaTutor(@ModelAttribute Tutor tutor, Model model) {

        tutorService.salvar(tutor);
        model.addAttribute("tutor", tutor);
        return "confirma-tutor";
    }

    @GetMapping("/listar-tutor")
    public String listarTutores(@RequestParam(value = "busca", required = false) String busca, Model model) {

        List<Tutor> tutores;

        if (busca != null && !busca.isEmpty()) {
            tutores = tutorService.buscarPorNome(busca);
        } else {
            // Listar todos os tutores
            tutores = tutorService.listarTodos();
        }
        model.addAttribute("tutores", tutores);
        return "listar-tutor";
    }

    @GetMapping("/editar-tutor/{id}")
    public String editarTutor(@PathVariable int id, Model model) {

        Tutor tutor = tutorService.buscaPorId(id);
        model.addAttribute("tutor", tutor);
        return "editar-tutor";
    }

    @PostMapping("/editar-tutor/{id}")
    public String atualizarTutor(@PathVariable int id, @ModelAttribute Tutor tutor) {
        
        tutorService.atualizar(id, tutor);
        return "redirect:/listar-tutor";
    }

    @GetMapping("/excluir-tutor/{id}")
    public String excluirTutor(@PathVariable int id) {
        
        tutorService.excluir(id);
        return "redirect:/listar-tutor";
    }
}
