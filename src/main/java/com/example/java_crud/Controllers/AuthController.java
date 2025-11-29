package com.example.java_crud.Controllers;

import com.example.java_crud.DTOs.LoginRequest;
import com.example.java_crud.Exceptions.ValidationException;
import com.example.java_crud.Models.Funcionario;
import com.example.java_crud.Services.FuncionarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final FuncionarioService funcionarioService;

    public AuthController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request,
            HttpSession session
    ) {

        try {
            Funcionario funcionario = funcionarioService.login(
                    request.getMatricula(),
                    request.getSenha()
            );

            session.setAttribute("usuario", funcionario);

            return ResponseEntity.ok("Logado com sucesso");
        }
        catch (ValidationException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
