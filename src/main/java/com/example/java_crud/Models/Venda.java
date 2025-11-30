package com.example.java_crud.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O cliente é obrigatório")
    @ManyToOne(optional = false)
    private Cliente cliente;

    @NotNull(message = "O funcionário é obrigatório")
    @ManyToOne(optional = false)
    private Funcionario funcionario;

    @NotNull(message = "O carro é obrigatório")
    @ManyToOne(optional = false)
    private Carro carro;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória")
    private LocalDate dataFim;

    @NotNull(message = "O valor total é obrigatório")
    @Positive(message = "O valor total deve ser maior que zero")
    private BigDecimal valorTotal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Carro getCarro() { return carro; }
    public void setCarro(Carro carro) { this.carro = carro; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}
