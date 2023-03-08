package com.cadastro.pessoas.service;

import com.cadastro.pessoas.model.Funcionario;
import com.cadastro.pessoas.repository.FuncionarioRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario criar(Funcionario funcionario){
        if (funcionario.getId() != null) {
            throw new IllegalArgumentException();
        }
        funcionario.setId(UUID.randomUUID().toString());
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario obter(String id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public List<Funcionario> obterTodos() {
        List<Funcionario> lista = new ArrayList<>();
        funcionarioRepository.findAll().forEach(comodo -> lista.add(comodo));
        return lista;
    }

    public Funcionario atualizar(Funcionario funcionario) {
        funcionario.setDataNascimento(funcionario.getDataNascimento());
        funcionario.setNaturalidade(funcionario.getNaturalidade());
        funcionario.setTelefone(funcionario.getTelefone());
        funcionario.setEmail(funcionario.getEmail());
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario delete(String id) {
        Funcionario reserva = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        funcionarioRepository.delete(reserva);
        return reserva;
    }

}
