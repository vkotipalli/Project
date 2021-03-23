-- Name(s): Madison Maloney and Vasavi Kotipalli 
-- Date: 03/21/2021
-- Purpose of Program: This is a database representation  

use mmaloney;

## Questions to ask: 
# is this foreign key correct when referencing the person table "foreign key (employeeId) references Person(personId)" Does naming matter?

# is this required when createing tables: "engine=InnoDB,collate=latin1_general_cs;"

# is an itemId required for each Service, Subscription, Product using it as a foreign key as well when creating those tables. 

# should beginDate and endDate be referenced as a varchar? 

# What are examples of things that can be null and not null?

##FOR ITEMSALE ONLY! Is this the right way to go about inserting data into itemSale? Especially with sales that have multiple items. 

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
    country varchar(255), 
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
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (1, 'PGHPA5859I','G','Maloney','Madison', 1, 1);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (2, '72OFN7R0RL','P','Kotipalli','Vasavi',2, 2);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (3, 'R0E2QECOTP','C','Maloney','Mallory', 3, 3);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (4, '4CQ9USQH10','E','Scherf','Jordan', 4, 4);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (5, 'EVCDH4PET3','P','Smith','Sam', 5, 5);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (6, '8XUXEI4T0W','E','Ponnatha','Niheala', 6, 6);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (7, 'UWUA6YGS9X','E','Chada','Shreya', 7, 7);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (8, 'CZDOL8TJ6I','G','Boinpally','Teju', 8, 8);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (9, 'E3SBKH0OK6','E','Builder','Bob', 9, 9);
INSERT INTO Person(personId,personCode,type,lastName,firstName,addressId,emailId) VALUES (10, 'XC9P4020OQ','G','Brady','Tom', 10, 10);

#Inserting Person Address Test Data
insert into Address(addressId, street, city, state, country, zip) values (1, '3464 Evening Sun Dr.','Las Vegas','NV',89117,'US');
insert into Address(addressId, street, city, state, country, zip) values (2, '2625 N 169th St','Omaha','NE',68116,'US');
insert into Address(addressId, street, city, state, country, zip) values (3, '2575 Dole St 326','Honolulu','HI',96822,'US');
insert into Address(addressId, street, city, state, country, zip) values (4, '6621 Lowden Ln','Las Vegas','NV',89107,'US');
insert into Address(addressId, street, city, state, country, zip) values (5, '871 CobbleStone Dr.','Grandville','MI',49418,'US');
insert into Address(addressId, street, city, state, country, zip) values(6, '8187 Summerhouse Av','Ronkonkoma','NY',11779,'US');
insert into Address(addressId, street, city, state, country, zip) values (7, '8408 Theatre Dr.','Gettysburg','PA',17325,'US');
insert into Address(addressId, street, city, state, country, zip) values (8, '210 Military St.','Tucson','AZ',85718,'US');
insert into Address(addressId, street, city, state, country, zip) values (9, '710 Center Dr.','Flushing','NY',11354,'US');
insert into Address(addressId, street, city, state, country, zip) values (10, '99 Rainbow Ln','Louisa','KY',41230,'US');

#Inserting Email Test Data
#Email may not allow multiple emails
insert into Email(emailId, email) values (1, 'mmaloney2@unl.edu');
insert into Email(emailId, email) values (2, 'vkotipalli086@gmail.com', 'vasavi.kotipalli@yahoo.com');
insert into Email(emailId, email) values (3, NULL);
insert into Email(emailId, email) values (4, NULL);
insert into Email(emailId, email) values (5, NULL);
insert into Email(emailId, email) values (6, 'Niheala.P@yahoo.com','nponnatha@gmail.com');
insert into Email(emailId, email) values (7, NULL); 
insert into Email(emailId, email) values (8, 'tboinpally275@gamil.com');
insert into Email(emailId, email) values (9, 'bbuilder123@yahoo.com','bob.builder@gmail.com','bobb@hotmail.com');
insert into Email(emailId, email) values (10, 'tomBrady@gmail.com');

#Inserting PersonEmail Test Data 
#May have to alter to allow multiple emails to the same personId.

insert into PersonEmail(personEmailId, emailId, personId) values (1, 1);
insert into PersonEmail(personEmailId, emailId, personId) values (2, 2);
insert into PersonEmail(personEmailId, emailId, personId) values (3, 3);
insert into PersonEmail(personEmailId, emailId, personId) values (4, 4);
insert into PersonEmail(personEmailId, emailId, personId) values (5, 5);
insert into PersonEmail(personEmailId, emailId, personId) values (6, 6);
insert into PersonEmail(personEmailId, emailId, personId) values (7, 7);
insert into PersonEmail(personEmailId, emailId, personId) values (8, 8);
insert into PersonEmail(personEmailId, emailId, personId) values (9, 9);
insert into PersonEmail(personEmailId, emailId, personId) values (10, 10);

#Inserting Store Test Data
insert into Store(storeId, storeCode, managerId, addressId) VALUES (1, 'HJWPT13Z3V', 6, 11);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (2, 'BSNGQH84ZW', 4, 12);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (3, 'Y33N4W1E8G', 4, 13);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (4, 'YYW8GS50PL', 6, 14);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (5, 'BXOLMSCUQV', 4, 15);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (6, '9KOICUR4S1', 7, 16);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (7, 'H04SOSFZ5J', 6, 17);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (8, 'F9ZAUR57XO', 7, 18);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (9, 'ZG1F5MHIOG', 6, 19);
INSERT INTO Store(storeId, storeCode, managerId, addressId) VALUES (10, '100HYR52FL', 7, 20);

#Inserting Store Address Test Data 
insert into Address(addressId, street, city, state, country, zip) values (11, '973 Spring Avenue','Phila','PA',19103,'US');
insert into Address(addressId, street, city, state, country, zip) values (12, '2434 Mount Tabor','Winchester','TN',37398,'US');
insert into Address(addressId, street, city, state, country, zip) values (13, '1956 Ashford Drive','Alexandria','VA',22304,'US');
insert into Address(addressId, street, city, state, country, zip) values (14, '2189 Rowes Lane','Paducah','KY',42001,'US');
insert into Address(addressId, street, city, state, country, zip) values (15, '4288 Parrish Avenue','San Antonio','TX',78217,'US');
insert into Address(addressId, street, city, state, country, zip) values (16, '406 Essex Court','South Burlington','VT',50403,'US');
insert into Address(addressId, street, city, state, country, zip) values (17, '893 Front Street','Southfield','MI',48075,'US');
insert into Address(addressId, street, city, state, country, zip) values (18, '2148 Middleville Road','Pasadena','CA',91109,'US');
insert into Address(addressId, street, city, state, country, zip) values (19, '4294 Old House Drive','Summerfield','OH',43788,'US');
insert into Address(addressId, street, city, state, country, zip) values (20, '4021 Gnatty Creek Road','Westbury','NY',11590,'US');

#Inserting Item Test Data

insert into Item(itemId, itemCode, itemName, itemType) values (1, 'fc63ef', 'Cyberpunk 2077' 'PU');
insert into Item(itemId, itemCode, itemName, itemType) values (2, '84594c', 'Grand Theft Auto V', 'PN');
insert into Item(itemId, itemCode, itemName, itemType) values (3, '68cdc8', 'Xbox Series X' 'PN');
insert into Item(itemId, itemCode, itemName, itemType) values (4, '1719c4', 'Nintendo Switch (Animal Crossing Edition)' 'PU');
insert into Item(itemId, itemCode, itemName, itemType) values (5, '90a51d', 'Nintendo eShop Credit' 'PG');
insert into Item(itemId, itemCode, itemName, itemType) values (6, 'e4ed68', 'League of Legends Riot Points' 'PG');
insert into Item(itemId, itemCode, itemName, itemType) values (7, '76c395', 'Stardew Valley' 'PU');
insert into Item(itemId, itemCode, itemName, itemType) values (8, '8b095e', 'Computer Repair' 'SV');
insert into Item(itemId, itemCode, itemName, itemType) values (9, '3c43d0', 'Game Room Design' 'SV');
insert into Item(itemId, itemCode, itemName, itemType) values (10, '516454', 'Nintendo Switch Online' 'SB');
insert into Item(itemId, itemCode, itemName, itemType) values (11, '7982fe', 'Xbox Magazine', 'SB');
insert into Item(itemId, itemCode, itemName, itemType) values (12, 'd09a87', 'Netflix', 'SB');
insert into Item(itemId, itemCode, itemName, itemType) values (13, '774196', 'Apple Arcade', 'SB');

#Inserting Service Test Data

insert into Service(serviceId, hourlyRate, numHours, employeeId, itemId) values (1, 39.99, 4, 7, 9);
insert into Service(serviceId, hourlyRate, numHours, employeeId, itemId) values (2, 19.99, 6, 9, 8);


#Inserting Subscription Test Data

insert into Subscription(subscriptionId, annualFee, beginDate, endDate, itemId) values (1, 19.99, '2020-06-15', '2021-02-08', 10);
insert into Subscription(subscriptionId, annualFee, beginDate, endDate, itemId) values (2, 101.99, '2016-03-23', '2019-11-02', 12);
insert into Subscription(subscriptionId, annualFee, beginDate, endDate, itemId) values (3, 69.99, '2018-10-06', '2020-09-29', 13);
insert into Subcription(subscriptionId, annualFee, beginDate, endDate, itemId) values ( 4, 19.99, '2019-04-26', '2019-12-21', 10);


#Inserting Product Test Data

insert into Product(productId, basePrice, quantity, itemId) values (1, 10.99, 3, 7);
insert into Product(productId, basePrice, quantity, itemId) values (2, 10.99, 3, 7);
insert into Product(productId, basePrice, quantity, itemId) values (3, 59.99, 1, 2);
insert into Product(productId, basePrice, quantity, itemId) values (4, 55.99, 2, 1);

#Inserting Sale Test Data

insert into Sale(saleId, saleCode, customerId, storeId, salespersonId) values (1, 'N7HJZM4AA6', 1, 1, 4);
insert into Sale(saleId, saleCode, customerId, storeId, salespersonId) values (2, '05254TNLQ4', 8, 2, 7);
insert into Sale(saleId, saleCode, customerId, storeId, salespersonId) values (3, 'AB2Q7D53KT', 5, 3, 6);
insert into Sale(saleId, saleCode, customerId, storeId, salespersonId) values (4, 'LLQFRHT4HW', 10, 4, 7);
insert into Sale(saleId, saleCode, customerId, storeId, salespersonId) values (5, 'NUDVU2C0CB', 3, 7, 9);
insert into Sale(saleId, saleCode, customerId, storeId, salespersonId) values (6, 'OPX58M9BZ8', 2, 9, 4);

#Inserting SaleItem Test Data

insert into SaleItem (saleItemId, saleId, itemId) values (1, 1, 10); 
insert into SaleItem (saleItemId, saleId, itemId) values (2, 1, 7);
insert into SaleItem (saleItemId, saleId, itemId) values (3, 2, 9); 
insert into SaleItem (saleItemId, saleId, itemId) values (4, 2, 12);
insert into SaleItem (saleItemId, saleId, itemId) values (5, 3, 7);
insert into SaleItem (saleItemId, saleId, itemId) values (6, 3, 2);
insert into SaleItem (saleItemId, saleId, itemId) values (7, 4, 13);
insert into SaleItem (saleItemId, saleId, itemId) values (8, 5, 8);
insert into SaleItem (saleItemId, saleId, itemId) values (9, 6, 1);
insert into SaleItem (saleItemID, saleId, itemId) values (10, 6, 10);


 


