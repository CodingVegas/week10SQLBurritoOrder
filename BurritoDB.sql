create database if not exists orders;

use orders;

drop table if exists burritos;
drop table if exists orders;

create table orders (
id int not null auto_increment,
name varchar(50) not null,
primary key(id)
);

create table burritos (
id int not null auto_increment,
meat varchar(25) not null,
rice varchar(25) not null,
bean varchar(25) not null,
order_id int not null,
primary key(id),
foreign key(order_id) references orders(id)
);
