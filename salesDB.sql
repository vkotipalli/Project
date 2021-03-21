-- Name(s): Madison Maloney and Vasavi Kotipalli 
-- Date: 03/21/2021
-- Purpose of Program: This is a database representation  

use mmaloney;

## Questions to ask: 
# is this foreign key correct when referencing the person table "foreign key (employeeId) references Person(personId)"
# is this required when createing tables: "engine=InnoDB,collate=latin1_general_cs;"
# is an itemId required for each Service, Subscription, Product using it as a foreign key as well when creating those tables. 
# should beginDate and endDate be referenced as a varchar? 
# What are examples of things that can be null and not null?

drop table if exists Person; 
drop table if exists Address;
drop table if exists Email; 
drop table if exists PersonEmail; 
drop table if exists Store; 
drop table if exists Item; 
drop table if exists Service; 
drop table if exists Subscription;
drop table if exists Product; 
drop table if exists Sale; 
drop table if exists SaleItem;

create table Person (
	personId int not null primary key auto_increment,
    personCode varchar(255),
    type varchar(255),
    lastName varchar(255), 
    firstName varchar(255), 
    addressId int,
    emailId int,
    foreign key (addressId) references Address(addressId), 
    foreign key (emailId) references Email(emailId)
)engine=InnoDB,collate=latin1_general_cs;

create table Address (
	addressId int not null primary key auto_increment, 
    street varchar(255), 
    city varchar(255), 
    state varchar(255), 
    county varchar(255), 
    zip varchar(255)
)engine=InnoDB,collate=latin1_general_cs;

create table Email (
	emailId int not null primary key auto_increment, 
    email varchar(255)
)engine=InnoDB,collate=latin1_general_cs;

create table PersonEmail (
	personEmailId int not null primary key auto_increment, 
    emailId int not null, 
    personId int not null,
    foreign key (emailId) references Email (emailId),
    foreign key (personId) references Person (personId)
)engine=InnoDB,collate=latin1_general_cs;


create table Store (
	storeId int not null primary key auto_increment, 
    storeCode varchar(255), 
    managerId int, 
    addressId int, 
	foreign key (managerId) references Person(personId),
    foreign key (addressId) references Address(addressId)
)engine=InnoDB,collate=latin1_general_cs;


create table Item (
	itemId int not null primary key auto_increment, 
    itemCode varchar(255), 
    itemName varchar(255), 
    ##1-1 with items can have specific type PU, PN, PG, SV, SU
    itemType varchar(255)
)engine=InnoDB,collate=latin1_general_cs;


create table Service (
	serviceId int not null primary key auto_increment, 
    hourlyRate double,
    numHours int, 
    employeeId int, 
    itemId int, 
    foreign key (employeeId) references Person(personId), 
    foreign key (itemId) references Item(itemId)
)engine=InnoDB,collate=latin1_general_cs;


create table Subscription (
	subscriptionId int not null primary key auto_increment, 
    annualFee double, 
    beginDate varchar(255), 
    endDate varchar(255),
	itemId int, 
    foreign key (itemId) references Item(itemId)
)engine=InnoDB,collate=latin1_general_cs;

create table Product (
	productId int not null primary key auto_increment, 
    basePrice double, 
    quantity int, 
    itemId int, 
    foreign key (itemId) references Item(itemId)
)engine=InnoDB,collate=latin1_general_cs;

create table Sale (
	saleId int not null primary key auto_increment, 
    saleCode varchar(255), 
    customerId int not null, 
    storeId int not null, 
    salespersonId int not null, 
    foreign key (customerId) references Person(personId), 
    foreign key (storeId) references Store(storeId), 
    foreign Key (salespersonId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;


create table SaleItem (
	saleItemId int not null primary key auto_increment, 
    saleId int not null, 
    itemId int not null, 
    foreign key (saleId) references Sale(saleId),
    foreign key (itemId) references Sale(itemId)
)engine=InnoDB,collate=latin1_general_cs;


#Inserting Person Test Data



