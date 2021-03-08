create schema springbank_dev;
use springbank_dev;

insert into perfil(nome, data_cadastro) values ('ROLE_ADMIN', now());
insert into usuario(nome,senha,data_criacao) values ('ADMIN','$2a$10$GqUa0pdruuWofiSRIDm6o.0Kg.bcmsaSgo2.tfK4SkGCRZgCkCn7C',now());
insert into usuario(nome,senha,data_criacao) values ('RENAN','$2a$10$GqUa0pdruuWofiSRIDm6o.0Kg.bcmsaSgo2.tfK4SkGCRZgCkCn7C',now());
insert into usuario(nome,senha,data_criacao) values ('VINICIUS','$2a$10$GqUa0pdruuWofiSRIDm6o.0Kg.bcmsaSgo2.tfK4SkGCRZgCkCn7C',now());
insert into usuario_perfis(usuario_id,perfis_id) values (1,1);
insert into usuario_perfis(usuario_id,perfis_id) values (2,1);
insert into usuario_perfis(usuario_id,perfis_id) values (3,1);

commit;

select * from usuario;
select * from perfil;
select * from usuario_perfis;
Select * from agencia;
select * from conta;