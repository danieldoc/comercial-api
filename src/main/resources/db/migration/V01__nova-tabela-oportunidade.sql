create table oportunidade (
  id bigserial not null,
  nome_prospecto varchar(80) not null,
  descricao varchar(200) not null,
  valor decimal(10,2),

  primary key (id)
);