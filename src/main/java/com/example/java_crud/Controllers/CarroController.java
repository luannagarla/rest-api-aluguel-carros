package com.example.java_crud.Controllers;

import com.example.java_crud.Exceptions.ValidationException;
import com.example.java_crud.Models.Carro;
import com.example.java_crud.Services.CarroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@CrossOrigin(origins = "*")
public class CarroController {

    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Carro> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Carro buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Carro carro)
    {
        try {
            return ResponseEntity.ok(service.salvar(carro));
        } catch (ValidationException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Carro atualizar(@PathVariable Long id, @Valid @RequestBody Carro carro) {
        carro.setId(id);
        return service.salvar(carro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/placa/{placa}")
    public Carro buscarPorPlaca(@PathVariable String placa) {
        return service.buscarPelaPlaca(placa);
    }

    @GetMapping("/disponiveis")
    public List<Carro> listarDisponiveis() {
        return service.listarDisponiveis();
    }

    @GetMapping("/buscar")
    public List<Carro> buscar(@RequestParam String q) {
        return service.buscar(q);
    }

}
