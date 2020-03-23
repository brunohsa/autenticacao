--liquibase formatted sql
--changeset bruno:AUT-002-00

CREATE TABLE Permissao (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL UNIQUE,
    descricao varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE Perfil (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(80) NOT NULL UNIQUE,
    descricao varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE Usuario (
    id int NOT NULL AUTO_INCREMENT,
    email varchar(60) NOT NULL UNIQUE,
    senha varchar(40),
    uuid varchar(40) NOT NULL UNIQUE,
    situacao varchar(30),
    apikey varchar(100),
    cadastro_uuid varchar(40),
    firebase_id varchar(60),
    PRIMARY KEY (id)
);

CREATE TABLE Perfil_Permissoes (
    perfil_id int,
    permissao_id int
);

CREATE TABLE Usuario_Perfis (
    usuario_id int,
    perfil_id int
);