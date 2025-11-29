package com.example.java_crud.Services;

import com.example.java_crud.Exceptions.NotFoundException;
import com.example.java_crud.Exceptions.ValidationException;
import com.example.java_crud.Models.Funcionario;
import com.example.java_crud.Repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repo;

    public FuncionarioService(FuncionarioRepository repo) {
        this.repo = repo;
    }

    public Funcionario salvar(Funcionario funcionario) {
        return repo.save(funcionario);
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

    public void excluir(Long id) {
        Funcionario f = buscarPorId(id);
        f.setExcluido(true);
        repo.save(f);
    }

    public Funcionario login(String matricula, String senha) {
        return repo.findByMatriculaAndSenha(matricula, senha)
                .orElseThrow(() -> new ValidationException("Login ou senha inválidos"));
    }

    public Funcionario buscarPorNome(String nome) {
        return repo.findByNomeIgnoreCaseAndExcluidoFalse(nome)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado"));
    }

    public List<Funcionario> buscar(String nome) {
        return repo.findByNomeContainingIgnoreCaseAndExcluidoFalse(nome);
    }

}
