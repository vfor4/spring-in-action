create table if not exists Ingredient (
id varchar(4) not null,
name varchar(25) not null,
type varchar(10) not null
);
create table if not exists Taco (
id identity,
name varchar(50) not null,
createdAt timestamp not null
);
create table if not exists Taco_Ingredients (
	taco_id bigint not null,
	ingredient_id varchar(4) not null
);
alter table Taco_Ingredients
	add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients
	add foreign key (ingredient_id) references Ingredient(id);
create table if not exists Taco_Order (
id identity,
delivery_Name varchar(50) not null,
delivery_Street varchar(50) not null,
delivery_City varchar(50) not null,
delivery_State varchar(2) not null,
delivery_Zip varchar(10) not null,
cc_Number varchar(16) not null,
cc_Expiration varchar(5) not null,
cc_CVV varchar(3) not null,
placed_At timestamp not null,
user_id bigint not null
);
create table if not exists user (
id identity,
username varchar(80),
password varchar(80),
fullname varchar(50),
street varchar(50),
city varchar(50),
state varchar(50) ,
zip varchar(50),
phone_Number varchar(12)
);
alter table Taco_Order
add foreign key (user_id) references user(id);
create table if not exists Taco_Order_Tacos (
taco_Order_id bigint not null,
taco_id bigint not null
);
alter table Taco_Order_Tacos
add foreign key (taco_Order_id) references Taco_Order(id);
alter table Taco_Order_Tacos
add foreign key (taco_id) references Taco(id);

