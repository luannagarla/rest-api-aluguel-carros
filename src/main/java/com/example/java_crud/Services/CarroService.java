package com.example.java_crud.Services;

import com.example.java_crud.Exceptions.NotFoundException;
import com.example.java_crud.Exceptions.ValidationException;
import com.example.java_crud.Models.Carro;
import com.example.java_crud.Models.Funcionario;
import com.example.java_crud.Repositories.CarroRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarroService {

    private final CarroRepository repo;

    public CarroService(CarroRepository repo) {
        this.repo = repo;
    }

    public Carro salvar(Carro carro) {
        try {
            return repo.save(carro);
        }
        catch (DataIntegrityViolationException e) {
            throw new ValidationException("Placa já cadastrada.");
        }
        catch (Exception e) {
            throw new ValidationException("Erro ao salvar o carro.");
        }
    }

    public List<Carro> listarTodos() {
        return repo.findAll();
    }

    public Carro buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Carro não encontrado");
        }
        repo.softDelete(id);
    }

    public List<Carro> listarDisponiveis() {
        return repo.findByDisponivelTrueAndExcluidoFalse();
    }

    public Carro buscarPelaPlaca(String placa) {
        return repo.findByPlacaIgnoreCaseAndExcluidoFalse(placa)
                .orElseThrow(() -> new NotFoundException("Placa não encontrada"));
    }

    public List<Carro> buscar(String termo) {
        return repo.findByModeloContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrPlacaContainingIgnoreCase(
                termo, termo, termo
        );
    }
}
