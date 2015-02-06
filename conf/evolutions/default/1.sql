# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table model1entity (
  id                        bigint not null,
  name                      varchar(255),
  done                      boolean,
  due_date                  timestamp,
  constraint pk_model1entity primary key (id))
;

create sequence model1entity_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists model1entity;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists model1entity_seq;

