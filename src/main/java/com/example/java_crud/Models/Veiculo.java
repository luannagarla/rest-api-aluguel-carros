package com.example.java_crud.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A marca é obrigatória")
    @Column(nullable = false)
    private String marca;

    @NotBlank(message = "O modelo é obrigatório")
    @Column(nullable = false)
    private String modelo;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1950, message = "Ano inválido")
    @Max(value = 2050, message = "Ano inválido")
    private Integer ano;

    @NotBlank(message = "A placa é obrigatória")
    @Pattern(
            regexp = "[A-Za-z]{3}\\d{4}|[A-Za-z]{3}\\d[A-Za-z]\\d{2}",
            message = "Placa inválida"
    )
    @Column(nullable = false, unique = true)
    private String placa;

    private boolean disponivel = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}
