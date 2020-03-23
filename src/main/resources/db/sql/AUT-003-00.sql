--liquibase formatted sql
--changeset bruno:AUT-003-00

insert into Usuario(email, senha, uuid, situacao, apikey) values('autenticacao-ms@sistema.com','autenticacaoMS@1', 'dc7cf9f4-6476-4bf4-87f2-71231f419bcd', 'ATIVO', '$2a$10$7RLi6PVkrSjOlruTPFf5tO1JlPP0YY5qQTT8cpz8GRgAzwlWV4Yg');