create database CasadeFerramentas;
use CasadeFerramentas;
create table Usuario(
	iduser int primary key,
    usuario varchar(15) not null,
    cpf varchar(15),
    login varchar(15) not null unique,
    senha varchar(50) not null
    );
  
  insert into Usuario(iduser,usuario,login,senha,cpf)
  values(1,'Administrador','admin','admin','12345678955');
insert into Usuario(iduser,usuario,login,senha,cpf)
  values(3,'Joaquim Silva','joaquimf','joca123','12355677955');
  insert into Usuario(iduser,usuario,login,senha,cpf)
  values(9,'Fernando Gomes','Fernandoh','fer123456','15667895587');
insert into Usuario(iduser,usuario,login,senha,cpf)
  values(10,'Giovana lima','GiovanaC','gio2023','12989558766');
  insert into Usuario(iduser,usuario,login,senha,cpf)
  values(12,'Mariana souza','MariFer','Mari123','18857846944');
  
  select * from Usuario;
  
  Create table Cliente(
  idcli int primary key auto_increment,
    nomec varchar(50) not null,
    enderecoc varchar(100),
    telefonec varchar(15) not null,
    emailc varchar(50) unique);
    
    insert into Cliente(nomec,enderecoc,telefonec,emailc)
    values('Larissa mendes souza','Rua a , 25 , cruzeiro celeste','3852-0054','marina.silva@gmail.com');

create table AluguelFerramentas(
    Ferramentas int primary key auto_increment,
    data_alug timestamp default current_timestamp,
    situacao varchar(20) not null,
    modelof varchar(150) not null,
    numdeserie varchar(30),
    valor decimal(10,2),
    idcli int not null,
    foreign key(idcli) references Cliente(idcli)
);

ALTER TABLE AluguelFerramentas ADD Ferramenta VARCHAR (20);

describe AluguelFerramentas;
insert into AluguelFerramentas(situacao,modelof,numdeserie,valor,idcli,ferramenta)
values('Alugada','Martelo','NB54DFG',10.90,1,'Martelo de Bola');


insert into AluguelFerramentas(situacao,modelof,numdeserie,valor,idcli,ferramenta)
values('Disponivel','Martelo','PQB54DFG',08.90,1,'Martelo de unha');






