create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40) not null, surname varchar(40) not null);
create table if not exists ims.items(id int primary key auto_increment, title varchar(60) not null, price decimal(8,2) not null);
create table if not exists ims.orders(id int primary key auto_increment, customer_id int not null, foreign key (customer_id) references customers(id));
create table if not exists ims.orders_items(id int primary key auto_increment, order_id int not null, item_id int not null, foreign key (item_id) references items(id));