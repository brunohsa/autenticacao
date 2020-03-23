--liquibase formatted sql
--changeset bruno:AUT-002-01

--permissoes pf, pj e sistema
INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.cadastro', 'permissão para poder buscar um cadastro');
SELECT @id_permissao_buscar_cadastro := LAST_INSERT_ID();

--permissoes pj e sistema
INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.produto', 'permissão para busca de produtos');
SELECT @id_permissao_buscar_produto := LAST_INSERT_ID();

--permissoes sistema
INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.cadastrar.pessoa.fisica', 'permissão para poder cadastrar uma pessoa fisica');
SELECT @id_permissao_cadastrar_pf := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.cadastrar.pessoa.juridica', 'permissão para poder cadastrar uma pessoa juridica');
SELECT @id_permissao_cadastrar_pj := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.coordenadas', 'permissão para buscar coordenadas');
SELECT @id_permissao_buscar_coordenadas := LAST_INSERT_ID();

--permissoes pf
INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.pessoa.fisica', 'permissão para buscar uma pessoa fisica');
SELECT @id_permissao_buscar_pf := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.alterar.pessoa.fisica', 'permissão para alterar uma pessoa fisica');
SELECT @id_permissao_alterar_pf := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.lojas.por.proximidade', 'permissão para buscar lojas por proximidade');
SELECT @id_permissao_buscar_lojas_proximas := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.endereco.por.cep', 'permissão para buscar um endereço por cep');
SELECT @id_permissao_buscar_endereco_por_cep := LAST_INSERT_ID();

--permissoes pj
INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.pessoa.juridica', 'permissão para buscar uma pessoa juridica');
SELECT @id_permissao_buscar_pj := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.alterar.pessoa.juridica', 'permissão para alterar uma pessoa juridica');
SELECT @id_permissao_alterar_pj := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.criar.cardapio', 'permissão para criação de um cardapio');
SELECT @id_permissao_criar_cardapio := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.cardapio', 'permissão para busca de um cardapio');
SELECT @id_permissao_buscar_cardapio := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.alterar.cardapio', 'permissão para alterar um cardapio');
SELECT @id_permissao_alterar_cardapio := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.alterar.imagem.produto', 'permissão para alterar a imagem de um produto');
SELECT @id_permissao_alterar_imagem_produto := LAST_INSERT_ID();

--premissoes pf, pj
INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.buscar.endereco', 'permissão para buscar um endereco');
SELECT @id_permissao_buscar_endereco := LAST_INSERT_ID();

INSERT INTO Permissao(nome, descricao) VALUES ('permissoes.adicionar.endereco', 'permissão para adicionar um endereco');
SELECT @id_permissao_alterar_endereco := LAST_INSERT_ID();

--perfis
INSERT INTO Perfil(nome, descricao) VALUES ('SISTEMA', 'Perfil para usuarios do sistema');
SELECT @id_perfil_sistema := LAST_INSERT_ID();

INSERT INTO Perfil(nome, descricao) VALUES ('CONSUMIDOR', 'Perfil para usuarios dos consumidores');
SELECT @id_perfil_consumidor := LAST_INSERT_ID();

INSERT INTO Perfil(nome, descricao) VALUES ('FORNECEDOR', 'Perfil para usuarios dos fornecedores');
SELECT @id_perfil_fornecedor := LAST_INSERT_ID();

--inserindo premissoes para os perfis

--PERFIL SISTEMA
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_sistema, @id_permissao_buscar_cadastro);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_sistema, @id_permissao_cadastrar_pf);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_sistema, @id_permissao_cadastrar_pj);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_sistema, @id_permissao_buscar_produto);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_sistema, @id_permissao_buscar_coordenadas);

--PERFIL PESSOA FISICA
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_consumidor, @id_permissao_buscar_cadastro);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_consumidor, @id_permissao_buscar_pf);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_consumidor, @id_permissao_alterar_pf);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_consumidor, @id_permissao_buscar_endereco);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_consumidor, @id_permissao_alterar_endereco);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_consumidor, @id_permissao_buscar_lojas_proximas);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_consumidor, @id_permissao_buscar_endereco_por_cep);

--PERFIL PESSOA JURIDICA
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_buscar_cadastro);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_buscar_pj);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_alterar_pj);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_buscar_endereco);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_alterar_endereco);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_criar_cardapio);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_buscar_cardapio);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_alterar_cardapio);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_alterar_imagem_produto);
INSERT INTO Perfil_Permissoes VALUES (@id_perfil_fornecedor, @id_permissao_buscar_produto);