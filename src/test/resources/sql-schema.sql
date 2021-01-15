create database if not exists ims_test;
drop table if exists ims_test.customers;
create table ims_test.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
