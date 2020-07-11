Create Table supermercado(
idsupermercado int not null auto_increment,
nome varchar(200),
primary key(idsupermercado))
ENGINE=InnoDB DEFAULT CHARSET=UTF8;

Create Table produto(
idproduto int not null auto_increment,
nome varchar(255),
supermercado_id int not null,
primary key(idproduto))
ENGINE=InnoDB DEFAULT CHARSET=UTF8;

alter table produto add constraint fk_produto_supermercado foreign key(supermercado_id) references supermercado(idsupermercado); 

insert into supermercado(nome) values ('Jaú Serve');

insert into produto(nome, supermercado_id) values ('Maça', 1);