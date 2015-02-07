# --- !Ups

create table model1s (
  id                        varchar(255) not null,
  value                     bigint  not null,
  flag                      boolean not null,
  due_date                  varchar(255) not null,
  constraint pk_model1s primary key (id));

# --- !Downs

drop table if exists model1s;

