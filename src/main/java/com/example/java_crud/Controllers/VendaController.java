package com.example.java_crud.Controllers;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.java_crud.Models.Carro;
import com.example.java_crud.Models.Venda;
import com.example.java_crud.Services.CarroService;
import com.example.java_crud.Services.ClienteService;
import com.example.java_crud.Services.FuncionarioService;
import com.example.java_crud.Services.VendaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public List<Venda> listar() {
        return vendaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Venda buscar(@PathVariable Long id) {
        return vendaService.buscarPorId(id);
    }

    @PostMapping
    public Venda salvar(@Valid @RequestBody Venda venda) {
        return vendaService.salvar(venda);
    }

    @PutMapping("/{id}")
    public Venda editar(@PathVariable Long id, @Valid @RequestBody Venda venda) {
        venda.setId(id);
        return vendaService.salvar(venda);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        vendaService.excluir(id);
    }

    @GetMapping("/buscar")
    public List<Venda> buscar(@RequestParam String q) {
        return vendaService.buscar(q);
    }

}
