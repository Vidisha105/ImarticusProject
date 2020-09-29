CREATE SCHEMA `oms` ;
use oms;
CREATE TABLE oms.orders(
   order_id   INT ,
   buy_or_sell varchar(10),
	order_time datetime ,
   quantity int ,
   order_type  varchar(10) ,
   price int ,
   order_status varchar(10) ,
	all_or_none int,
   min_fill int ,
   PRIMARY KEY (order_id)
);
select * from oms.orders;

create table oms.users(
	user_id int,
    username varchar(20),
    email_id varchar(30),
    pass_word varchar(10),
    primary key(user_id)
);	 
 select * from oms.users;
 
 insert into oms.users values(1, 'user1', 'user1@email.com', 'password1');
 
 insert into oms.users values(2, 'user2', 'user2@email.com', 'password2');