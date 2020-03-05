create table estado (
    id int not null,
    nome varchar(30) not null,
    sigla varchar(2) not null,
    codigo_ibge int not null,
    primary key(id)
);

create table municipio (
    id int not null,
    estado_id int not null,
    nome varchar(100),
    codigo_ibge int not null,
    primary key(id)
);

alter table municipio add constraint fk_estado foreign key (estado_id) references estado(id);


