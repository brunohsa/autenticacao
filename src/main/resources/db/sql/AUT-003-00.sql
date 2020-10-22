--liquibase formatted sql
--changeset bruno:AUT-003-00

insert into Usuario(email, senha, uuid, situacao, apikey) values('autenticacao-ms@sistema.com','autenticacaoMS@1', 'dc7cf9f4-6476-4bf4-87f2-71231f419bcd', 'ATIVO', '0b942a9ee88f446eaa2343ebc7aff2ac');
insert into Usuario(email, senha, uuid, situacao, apikey) values('carrinho-ms@sistema.com','carrinhoMS@1', '5b230e70-752c-4399-afda-b4f4ba2522c8', 'ATIVO', '99d2dd1106d2-43a6803efdebc9fb64af');