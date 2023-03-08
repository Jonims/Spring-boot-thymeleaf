package com.cadastro.pessoas.repository;

import com.cadastro.pessoas.model.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, String> {
}
