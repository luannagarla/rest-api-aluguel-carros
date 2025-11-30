package com.example.java_crud.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos")
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
            regexp = "\\d{10,11}",
            message = "O telefone deve ter entre 10 e 11 dígitos"
    )
    @Column(nullable = false)
    private String telefone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
