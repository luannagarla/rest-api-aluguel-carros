package com.example.java_crud.Services;

import com.example.java_crud.Exceptions.NotFoundException;
import com.example.java_crud.Exceptions.ValidationException;
import com.example.java_crud.Models.Carro;
import com.example.java_crud.Models.Venda;
import com.example.java_crud.Repositories.CarroRepository;
import com.example.java_crud.Repositories.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepo;
    private final CarroRepository carroRepo;

    public VendaService(VendaRepository vendaRepo, CarroRepository carroRepo) {
        this.vendaRepo = vendaRepo;
        this.carroRepo = carroRepo;
    }

    public Venda salvar(Venda venda) {

        if (venda.getCarro() == null ||
                venda.getCliente() == null ||
                venda.getFuncionario() == null) {
            throw new ValidationException("Venda incompleta");
        }

        Carro carro = carroRepo.findById(venda.getCarro().getId())
                .orElseThrow(() -> new NotFoundException("Carro não encontrado"));

        if (!carro.isDisponivel()) {
            throw new ValidationException("Carro não está disponível");
        }

        if (venda.getDataInicio() == null || venda.getDataFim() == null) {
            throw new ValidationException("As datas da venda são obrigatórias");
        }

        if (venda.getDataInicio().isAfter(venda.getDataFim())) {
            throw new ValidationException("A data de início não pode ser maior que a data final");
        }

        carro.setDisponivel(false);
        carroRepo.save(carro);

        return vendaRepo.save(venda);
    }

    public List<Venda> listarTodas() {
        return vendaRepo.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Venda não encontrada"));
    }

    public void excluir(Long id) {
        Venda venda = buscarPorId(id);

        Carro carro = venda.getCarro();
        carro.setDisponivel(true);
        carroRepo.save(carro);

        vendaRepo.deleteById(id);
    }

    public List<Venda> buscar(String q) {

        if (q.matches("\\d+")) {
            Long idCliente = Long.parseLong(q);
            return vendaRepo.findByClienteId(idCliente);
        }

        if (q.length() >= 3 && q.length() <= 8) {
            List<Venda> vendasPorPlaca = vendaRepo.findByCarroPlacaContainingIgnoreCase(q);
            if (!vendasPorPlaca.isEmpty()) {
                return vendasPorPlaca;
            }
        }

        return vendaRepo.findByClienteNomeContainingIgnoreCase(q);
    }
}
