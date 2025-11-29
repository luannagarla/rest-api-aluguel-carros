package com.example.java_crud.Services;

import com.example.java_crud.Exceptions.NotFoundException;
import com.example.java_crud.Models.Cliente;
import com.example.java_crud.Repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public Cliente salvar(Cliente cliente) {
        return repo.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    public void excluir(Long id) {
        Cliente c = buscarPorId(id);
        c.setExcluido(true);
        repo.save(c);
    }

    public List<Cliente> listarAtivos() {
        return repo.findByExcluidoFalse();
    }

    public Cliente buscarPorNome(String nome) {
        return repo.findByNomeIgnoreCaseAndExcluidoFalse(nome)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    public List<Cliente> buscar(String nome) {
        return repo.findByNomeContainingIgnoreCaseAndExcluidoFalse(nome);
    }

}
