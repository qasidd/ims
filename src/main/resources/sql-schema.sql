create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40) NOT NULL, surname varchar(40) NOT NULL);
create table if not exists ims.items(id int primary key auto_increment NOT NULL, title varchar(60) NOT NULL, price decimal NOT NULL);