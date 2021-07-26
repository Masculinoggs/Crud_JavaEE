create database dbusuarios;
show databases;
use dbusuarios;
create table usuarios(
iduse int primary key auto_increment,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar(50),
senha varchar(50) not null
);
show tables;

select * from usuarios;