create database if not exists ims_test;
drop table if exists ims_test.orders_items;
drop table if exists ims_test.orders;
drop table if exists ims_test.customers;
drop table if exists ims_test.items;
create table ims_test.customers(id int primary key auto_increment, first_name varchar(40) not null, surname varchar(40) not null);
create table ims_test.items(id int primary key auto_increment, title varchar(60) not null, price decimal(8,2) not null);
create table ims_test.orders(id int primary key auto_increment, customer_id int not null, foreign key (customer_id) references customers(id));
create table ims_test.orders_items(id int primary key auto_increment, order_id int not null, item_id int not null, quantity int default 1 not null, foreign key (order_id) references orders(id), foreign key (item_id) references items(id));
