package com.example.java_crud.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "carros")
public class Carro extends Veiculo {

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    @NotNull(message = "A quilometragem é obrigatória")
    @Min(value = 0, message = "A quilometragem não pode ser negativa")
    private Integer quilometragem;

    @NotNull
    private Boolean excluido = false;

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }

    public Boolean getExcluido() { return excluido; }
    public void setExcluido(Boolean excluido) { this.excluido = excluido; }
}
