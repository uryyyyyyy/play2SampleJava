# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table model1s (
  id                        varchar(255) not null,
  value                     bigint,
  flag                      boolean,
  due_date                  varchar(255),
  constraint pk_model1s primary key (id))
;

create sequence model1s_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists model1s;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists model1s_seq;

