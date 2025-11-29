CREATE DATABASE aluguel_carros CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE DATABASE IF NOT EXISTS aluguel_carros
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE aluguel_carros;

CREATE TABLE carros (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        marca VARCHAR(255) NOT NULL,
                        modelo VARCHAR(255) NOT NULL,
                        ano INT,
                        placa VARCHAR(255) NOT NULL,
                        disponivel TINYINT(1) NOT NULL DEFAULT 1,
                        categoria VARCHAR(255),
                        quilometragem INT,
                        excluido TINYINT(1) NOT NULL DEFAULT 0,
                        PRIMARY KEY (id),
                        UNIQUE KEY uk_carros_placa (placa)
);

CREATE TABLE clientes (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nome VARCHAR(255) NOT NULL,
                          cpf VARCHAR(255) NOT NULL,
                          telefone VARCHAR(50) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          cep VARCHAR(20) NOT NULL,
                          excluido TINYINT(1) NOT NULL DEFAULT 0,
                          PRIMARY KEY (id),
                          UNIQUE KEY uk_clientes_cpf (cpf),
                          UNIQUE KEY uk_clientes_email (email)
);

CREATE TABLE funcionarios (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              nome VARCHAR(255) NOT NULL,
                              cpf VARCHAR(255) NOT NULL,
                              telefone VARCHAR(50) NOT NULL,
                              matricula VARCHAR(100) NOT NULL,
                              login VARCHAR(255) NOT NULL,
                              senha VARCHAR(255) NOT NULL,
                              cargo VARCHAR(255) NOT NULL,
                              excluido TINYINT(1) NOT NULL DEFAULT 0,
                              PRIMARY KEY (id),
                              UNIQUE KEY uk_funcionarios_cpf (cpf),
                              UNIQUE KEY uk_funcionarios_matricula (matricula)
);

CREATE TABLE vendas (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        cliente_id BIGINT NOT NULL,
                        funcionario_id BIGINT NOT NULL,
                        carro_id BIGINT NOT NULL,
                        data_inicio DATE,
                        data_fim DATE,
                        valor_total DECIMAL(10,2),
                        PRIMARY KEY (id),
                        CONSTRAINT fk_vendas_cliente
                            FOREIGN KEY (cliente_id) REFERENCES clientes(id),
                        CONSTRAINT fk_vendas_funcionario
                            FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
                        CONSTRAINT fk_vendas_carro
                            FOREIGN KEY (carro_id) REFERENCES carros(id)
);
