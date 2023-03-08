package com.cadastro.pessoas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Funcionario {

    @Id
    private String id;
    private String nome;
    private String naturalidade;
    private String telefone;
    private String email;
    private String cpf;
    private String dataNascimento;
    private String cidade;
    private String rua;
    private String cep;
    private int numeroCasa;
    private String funcao;
    private double salario;
    private String dataEntrada;
    private String dataSaida;
}
