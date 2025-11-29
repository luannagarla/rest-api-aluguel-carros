package com.example.java_crud.Repositories;

import com.example.java_crud.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByExcluidoFalse();
    Optional<Cliente> findByNomeIgnoreCaseAndExcluidoFalse(String nome);

    List<Cliente> findByNomeContainingIgnoreCaseAndExcluidoFalse(String nome);
}
