package com.example.java_crud.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos")
    @Column(nullable = false)
    private String cep;

    @NotNull
    private Boolean excluido = false;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public Boolean getExcluido() { return excluido; }
    public void setExcluido(Boolean excluido) { this.excluido = excluido; }
}
