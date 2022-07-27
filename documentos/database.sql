CREATE DATABASE VENDAS;

CREATE TABLE PRODUTO(
        ID BIGSERIAL NOT NULL PRIMARY KEY,
        NOME VARCHAR(100) NOT NULL,
        DESCRICAO VARCHAR(255),
        PRECO NUMERIC(16,2),
        SKU VARCHAR(20),
        DATA_CADASTRO DATE
);        