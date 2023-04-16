CREATE SEQUENCE IF NOT EXISTS roles_id_seq
    start 1
    increment 1;
CREATE SEQUENCE IF NOT EXISTS users_id_seq
    start 1
    increment 1;
create table if not exists users
(
    id       bigint              not null primary key,
    login    varchar(255) unique not null,
    password varchar(255)        not null
);
create table if not exists roles
(
    id      bigint       not null primary key,
    role    varchar(255) not null,
    user_id bigint references users
);

insert into users(id, login, password)
values (-1, 'user',
        '$2a$12$6BlCjcTXmS/3IfcSiNI2COpDKk8LEqKr7PXcv9GJuLWgJfIXhGvRC'),
       ( -2, 'doctor'
       , '$2a$12$Hrm75q6NsauybkERr3Kz9eKX1JzIm2FaGMpRmg9t7HizfnPoqVG/2'),
       (-3, 'admin',
        '$2a$12$6aZWlxR/7WmzEobBqezqh.uYXjXdV0VCpO2FZVRXB/Pl9gD5D.qiC'),
       (-4, 'user2',
        '$2a$12$6BlCjcTXmS/3IfcSiNI2COpDKk8LEqKr7PXcv9GJuLWgJfIXhGvRC');

insert into roles(id, role, user_id)
values (-1, 'USER', -1),
       (-2, 'DOCTOR', -2),
       (-3, 'ADMIN', -3),
       (-4, 'USER', -4);

CREATE SEQUENCE IF NOT EXISTS documents_id_seq
    start 1
    increment 1;

create table if not exists documents
(
      id      bigint       not null
      primary key,
      content varchar(255) not null
      );

insert into documents (id, content)
values (-1, 'Document 1'),
       (-2, 'Document 2'),
       (-3, 'Document 3');
