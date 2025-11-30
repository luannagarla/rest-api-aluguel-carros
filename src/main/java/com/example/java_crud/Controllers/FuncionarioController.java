package com.example.java_crud.Controllers;

import com.example.java_crud.Models.Funcionario;
import com.example.java_crud.Services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Funcionario> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Funcionario buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Funcionario salvar(@Valid @RequestBody Funcionario funcionario)
    {
        return service.salvar(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario editar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {
        funcionario.setId(id);
        return service.salvar(funcionario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/buscar")
    public List<Funcionario> buscar(@RequestParam String q) {
        return service.buscar(q);
    }

}
