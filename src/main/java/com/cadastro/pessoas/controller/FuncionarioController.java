package com.cadastro.pessoas.controller;

import com.cadastro.pessoas.dto.FuncionarioRequest;
import com.cadastro.pessoas.dto.FuncionarioResponse;
import com.cadastro.pessoas.model.Funcionario;
import com.cadastro.pessoas.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("funcionario")
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    @PostMapping
    public String create(@RequestBody FuncionarioRequest request) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.getNome());
        funcionario.setCpf(request.getCpf());
        funcionario.setCidade(request.getCidade());
        funcionario.setRua(request.getRua());
        funcionario.setCep(request.getCep());
        funcionario.setNumeroCasa(request.getNumeroCasa());
        funcionario.setFuncao(request.getFuncao());
        funcionario.setDataEntrada(request.getDataEntrada());
        funcionario.setDataSaida(request.getDataSaida());
        funcionario.setSalario(request.getSalario());
        funcionario = funcionarioService.criar(funcionario);
        return funcionario.getId();
    }

    @GetMapping
    public List<FuncionarioResponse> readAll() {
        return funcionarioService.obterTodos().stream().map(funcionario -> {
            FuncionarioResponse funcionarioResponse = new FuncionarioResponse();
            BeanUtils.copyProperties(funcionario, funcionarioResponse);
            return funcionarioResponse;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public FuncionarioResponse read(@PathVariable String id) {
        Funcionario funcionario = funcionarioService.obter(id);
        FuncionarioResponse response = new FuncionarioResponse();
        response.setNome(funcionario.getNome());
        response.setCpf(funcionario.getCpf());
        response.setCidade(funcionario.getCidade());
        response.setRua(funcionario.getRua());
        response.setCep(funcionario.getCep());
        response.setNumeroCasa(funcionario.getNumeroCasa());
        response.setFuncao(funcionario.getFuncao());
        response.setDataEntrada(funcionario.getDataEntrada());
        response.setDataSaida(funcionario.getDataSaida());
        response.setSalario(funcionario.getSalario());
        return response;
    }

    @PutMapping("{id}")
    public FuncionarioResponse update(@PathVariable String id, @RequestBody FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = funcionarioService.obter(id);
        BeanUtils.copyProperties(funcionarioRequest, funcionario);
        funcionario = funcionarioService.atualizar(funcionario);
        FuncionarioResponse response = new FuncionarioResponse();
        BeanUtils.copyProperties(funcionario, response);
        return response;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        funcionarioService.delete(id);
    }
}
