--liquibase formatted sql
--changeset bruno:AUT-002-01

--permissoes
INSERT INTO Permissao(nome, descricao) VALUES ('permissao.usuario.criar.usuario.fornecedor', 'permissão para poder criar um usuario para um fornecedor');
SELECT @id_permissao_usuario_fornecedor := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissao.usuario.vincular.pessoa', 'permissão para poder vincular uma pessoa a um usuário');
SELECT @id_permissao_vincular_pessoa := LAST_INSERT_ID();

--perfis
INSERT INTO Perfil(nome, descricao) VALUES ('ADMINISTRADOR', 'Perfil para usuarios dos administradores do sistema');
SELECT @id_perfil_adminstrador := LAST_INSERT_ID();

INSERT INTO Perfil(nome, descricao) VALUES ('SISTEMA', 'Perfil para usuarios do sistema');
SELECT @id_perfil_sistema := LAST_INSERT_ID();

INSERT INTO Perfil(nome, descricao) VALUES ('CONSUMIDOR', 'Perfil para usuarios dos consumidores');
INSERT INTO Perfil(nome, descricao) VALUES ('FORNECEDOR', 'Perfil para usuarios dos fornecedores');

--inserindo premissoes para os perfis ADMINISTRADOR e SISTEMA
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_adminstrador, @id_permissao_usuario_fornecedor);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_sistema, @id_permissao_vincular_pessoa);