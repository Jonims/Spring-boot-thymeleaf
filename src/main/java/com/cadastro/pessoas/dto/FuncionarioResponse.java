package com.cadastro.pessoas.dto;

import lombok.Data;

@Data
public class FuncionarioResponse {
    private String nome;
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
