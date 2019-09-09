--liquibase formatted sql
--changeset bruno:AUT-002-00

CREATE TABLE permissao (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(100),
    PRIMARY KEY (id)
);

CREATE TABLE perfil (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(80),
    descricao varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(20),
    password varchar(80),
    uuid varchar(40),
    ativo tinyint(1),
    pessoa_id int,
    PRIMARY KEY (id)
);

CREATE TABLE perfil_permissoes (
    perfil_id int,
    permissao_id int
);

CREATE TABLE usuario_perfis (
    usuario_id int,
    perfil_id int
);