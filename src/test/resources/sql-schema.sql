drop schema if exists ims_test
create database if not exists ims_test;
create table if not exists ims_test.customers(customerId int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims_test.items(itemId int primary key auto_increment, itemName varchar(40), itemPrice decimal(6,2));
create table if not exists ims_test.orders(orderId int primary key auto_increment, customerId int, postcode varchar(40) not null, foreign key(customerId) references customers(customerId));
create table if not exists ims_test.orderlines(orderlineId int primary key auto_increment, orderId int, itemId int, foreign key(orderId) references orders(orderId), foreign key(itemId) references items(itemId));