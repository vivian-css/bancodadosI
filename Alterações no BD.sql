use casadeferramentas;
describe usuario;
alter table usuario add column perfil varchar (20) not null;
select * from usuario;
update usuario set perfil = "admin" where iduser=1;

insert into Usuario(iduser,usuario,login,senha,cpf,perfil)
  values(2,'Vivian Cristina','vivian','vivi123','15089987786','admin');
  
  update usuario set perfil = "user" where iduser=3;
  update usuario set perfil = "user" where iduser=9;
  update usuario set perfil = "user" where iduser=10;
  update usuario set perfil = "user" where iduser=54;
  update usuario set perfil = "user" where iduser=12;
