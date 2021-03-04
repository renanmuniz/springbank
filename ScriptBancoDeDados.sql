create schema springbank_dev;
use springbank_dev;

create table usuario (
	id BigInt not null auto_increment,
	usuario varchar(10) not null,
    senha varchar(256) not null,
    data_criacao timestamp DEFAULT CURRENT_TIMESTAMP,
    data_alteracao timestamp,
    primary key(id));
    
insert into usuario(usuario,senha) values ("admin","$2a$10$k05ciT7ZaENN/aNaHnHQp.1Ni4TsxiNhddiANEuQjA5AA1SPNSZQu");

