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
                venda.getFuncionario() == null)
            throw new ValidationException("Venda incompleta");

        Carro carro = carroRepo.findById(venda.getCarro().getId())
                .orElseThrow(() -> new NotFoundException("Carro não encontrado"));

        if (!carro.isDisponivel())
            throw new ValidationException("Carro não está disponível");

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
}
