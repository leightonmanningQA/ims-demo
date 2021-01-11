create database if not exists ims;
create table if not exists ims.customers(customerId int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(itemId int primary key auto_increment, itemName varchar(40), itemPrice decimal(6,2));
create table if not exists ims.orders(orderId int primary key auto_increment, customerId int, orderDate date not null, foreign key(customerId) references customers(customerId));
create table if not exists ims.orderline(orderlineId int primary key auto_increment, orderId int, itemId int, foreign key(orderId) references orders(orderId), foreign key(itemId) references items(itemId));