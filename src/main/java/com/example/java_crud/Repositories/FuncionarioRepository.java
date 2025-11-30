package com.example.java_crud.Repositories;

import com.example.java_crud.Models.Funcionario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByMatriculaAndSenha(String matricula, String senha);

    List<Funcionario> findByExcluidoFalse();
    Optional<Funcionario> findByNomeIgnoreCaseAndExcluidoFalse(String nome);
    List<Funcionario> findByNomeContainingIgnoreCaseAndExcluidoFalse(String nome);

    @Modifying
    @Transactional
    @Query("UPDATE Funcionario c SET c.excluido = true WHERE c.id = :id")
    void softDelete(@Param("id") Long id);
}
