package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.dto.FuncionarioRequest;
import com.cadastro.pessoas.model.Funcionario;
import com.cadastro.pessoas.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final FuncionarioService funcionarioService;
    private final FuncionarioController funcionarioController;


    @RequestMapping("/funcionarios")
    public String index(Model funcionario){
        funcionario.addAttribute("funcionarios", funcionarioService.obterTodos());
        return "listar-funcionario";
    }

    @GetMapping("/novo-cadastro")
    public String cadastro(Funcionario funcionario){
        return "criar-funcionario";

    }

    @GetMapping("/funcionario-edit/{id}")
    public String editarFuncionario(@PathVariable("id")String id, Model model){
        Funcionario funcionario = funcionarioService.obter(id);
        return "atualizar-funcionario";
    }

    @GetMapping("/funcionario-delete/{id}")
    public String mostrardeteteFuncionario(@PathVariable("id") String id, Model model) {
        Funcionario funcionario = funcionarioService.obter(id);
        model.addAttribute("funcionario", funcionario);
        return "excluir-funcionario";
    }

    @PostMapping("/excluir/{id}")
    public String delete(@PathVariable("id") String id) {
        funcionarioController.delete(id);
        return "redirect:/funcionarios";
    }

    @PostMapping("/add-funcionario")
    public String addfuncionario(Funcionario funcionario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "criar-funcionario";
        }

        funcionarioService.criar(funcionario);
        return "redirect:/funcionarios";
    }

    @PostMapping("/update/{id}")
    public String updatefuncionario(@PathVariable("id") String id, Funcionario funcionario,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            funcionario.setId(id);
            return "atualizar-funcionario";
        }

        FuncionarioRequest funcionarioRequest = new FuncionarioRequest();
        funcionarioRequest.setNome(funcionario.getNome());
        funcionarioRequest.setCpf(funcionario.getCpf());
        funcionarioRequest.setCidade(funcionario.getCidade());
        funcionarioRequest.setRua(funcionario.getRua());
        funcionarioRequest.setCep(funcionario.getCep());
        funcionarioRequest.setNumeroCasa(funcionario.getNumeroCasa());
        funcionarioRequest.setFuncao(funcionario.getFuncao());
        funcionarioRequest.setDataEntrada(funcionario.getDataEntrada());
        funcionarioRequest.setDataSaida(funcionario.getDataSaida());
        funcionarioRequest.setSalario(funcionario.getSalario());

        funcionarioController.update(funcionario.getId(), funcionarioRequest);
        return "redirect:/funcionarios";
    }


}
