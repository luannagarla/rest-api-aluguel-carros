package com.example.java_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.java_crud.Repositories")
@EntityScan(basePackages = "com.example.java_crud.Models")
public class JavaCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCrudApplication.class, args);
    }
}
