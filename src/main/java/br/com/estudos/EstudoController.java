package br.com.estudos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EstudoController {
    private final EstudoService estudoService;
    private final ConsultaRepository repository;

    public EstudoController(EstudoService estudoService, ConsultaRepository repository) {
        this.estudoService = estudoService;
        this.repository = repository;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("consultas", repository.findAllByOrderByIdDesc());
        return "index";
    }

    @PostMapping("/gerar")
    public String gerar(@RequestParam String tipo, @RequestParam String assunto,
                        RedirectAttributes mensagem) {
        if (assunto.isBlank() || assunto.length() > 4000 ||
                !(tipo.equals("Explicação") || tipo.equals("Resumo") || tipo.equals("Exercícios"))) {
            mensagem.addFlashAttribute("erro", "Informe um assunto válido e escolha uma opção.");
            return "redirect:/";
        }
        try {
            String resposta = estudoService.gerar(tipo, assunto);
            if (resposta == null || resposta.isBlank()) {
                mensagem.addFlashAttribute("erro", "A IA não retornou uma resposta. Tente novamente.");
            } else {
                repository.save(new Consulta(tipo, assunto, resposta));
            }
        } catch (Exception e) {
            mensagem.addFlashAttribute("erro", "Não foi possível gerar a resposta. Verifique a chave da API, os créditos e a conexão.");
        }
        return "redirect:/";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
