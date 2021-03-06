create schema if not exists springbank_dev;
use springbank_dev;

create table usuario (
	id BigInt not null auto_increment,
	nome varchar(10) not null,
    senha varchar(256) not null,
    data_criacao timestamp DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp,
    primary key(id));

insert into usuario(nome,senha) values ("admin","$2a$10$k05ciT7ZaENN/aNaHnHQp.1Ni4TsxiNhddiANEuQjA5AA1SPNSZQu");
delete from usuario;

INSERT INTO usuario (nome, senha)
SELECT * FROM (SELECT "admin", "$2a$10$k05ciT7ZaENN/aNaHnHQp.1Ni4TsxiNhddiANEuQjA5AA1SPNSZQu") AS tmp
WHERE NOT EXISTS (
    SELECT nome FROM usuario WHERE nome = "admin"
) LIMIT 1;

INSERT INTO USUARIO(nome,senha) VALUES ('renan','$2a$10$k05ciT7ZaENN/aNaHnHQp.1Ni4TsxiNhddiANEuQjA5AA1SPNSZQu');
select * from usuario;
desc usuario;
desc perfil;
INSERT INTO perfil(nome,data_cadastro) VALUES ("ROLE_ADMIN", timestamp);
select * from perfil;
select * from usuario_perfis;
