package com.example.java_crud.Services;

import com.example.java_crud.Exceptions.NotFoundException;
import com.example.java_crud.Exceptions.ValidationException;
import com.example.java_crud.Models.Cliente;
import com.example.java_crud.Repositories.ClienteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public Cliente salvar(Cliente cliente) {
        try {
            return repo.save(cliente);
        }
        catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ValidationException("E-mail ou CPF já cadastrado.");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ValidationException("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Carro não encontrado");
        }
        repo.softDelete(id);
    }

    public List<Cliente> listarAtivos() {
        return repo.findByExcluidoFalse();
    }

    public Cliente buscarPorNome(String nome) {
        return repo.findByNomeIgnoreCaseAndExcluidoFalse(nome)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    public List<Cliente> buscar(String termo) {
        return repo.findByNomeContainingIgnoreCaseAndExcluidoFalse(termo);
    }
}
