package com.example.java_crud.Repositories;

import com.example.java_crud.Models.Carro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByDisponivelTrueAndExcluidoFalse();
    Optional<Carro> findByPlacaIgnoreCaseAndExcluidoFalse(String placa);

    List<Carro> findByModeloContainingIgnoreCaseOrMarcaContainingIgnoreCaseOrPlacaContainingIgnoreCase(
            String modelo, String marca, String placa
    );

    @Modifying
    @Transactional
    @Query("UPDATE Carro c SET c.excluido = true WHERE c.id = :id")
    void softDelete(@Param("id") Long id);
}
