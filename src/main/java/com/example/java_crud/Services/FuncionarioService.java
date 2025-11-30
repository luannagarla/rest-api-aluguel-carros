package com.example.java_crud.Services;

import com.example.java_crud.Exceptions.NotFoundException;
import com.example.java_crud.Exceptions.ValidationException;
import com.example.java_crud.Models.Funcionario;
import com.example.java_crud.Repositories.FuncionarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repo;

    public FuncionarioService(FuncionarioRepository repo) {
        this.repo = repo;
    }

    public Funcionario salvar(Funcionario funcionario) {
        try {
            return repo.save(funcionario);
        }
        catch (DataIntegrityViolationException e) {
            throw new ValidationException("CPF, login ou matrícula já cadastrados.");
        }
        catch (Exception e) {
            throw new ValidationException("Erro ao salvar funcionário.");
        }
    }

    public List<Funcionario> listarTodos() {
        return repo.findAll();
    }

    public List<Funcionario> listarAtivos() {
        return repo.findByExcluidoFalse();
    }

    public Funcionario buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Carro não encontrado");
        }
        repo.softDelete(id);
    }

    public Funcionario login(String matricula, String senha) {
        return repo.findByMatriculaAndSenha(matricula, senha)
                .orElseThrow(() -> new ValidationException("Login ou senha inválidos"));
    }

    public Funcionario buscarPorNome(String nome) {
        return repo.findByNomeIgnoreCaseAndExcluidoFalse(nome)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado"));
    }

    public List<Funcionario> buscar(String termo) {
        return repo.findByNomeContainingIgnoreCaseAndExcluidoFalse(termo);
    }
}
