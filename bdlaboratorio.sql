drop database if exists laboratorio;

create database if not exists laboratorio_db
character set utf8mb4
collate utf8mb4_unicode_ci;
use laboratorio_db;


create table if not exists equipamentos(
patrimonio int primary key,
tipo varchar(20) not null,
descricao varchar(150) not null,
laboratorio varchar(50) not null,
situacao varchar(30) not null,
memoria_ram int null,
luminosidade int null
);

