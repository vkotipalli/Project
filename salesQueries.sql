-- Name(s): Madison Maloney & Vasavi Kotipalli
-- Date: 03/21/2021
-- Assignment 4 Phase III
-- 
-- Queries
-- 
-- 
-- 1. A query to retrieve the main attributes of each person (their code, and last/first name)
select Person.personCode, Person.lastName, Person.firstName from Person;
-- 
-- 2. A query to retrieve the major fields for every person including their address (but excluding emails)
select p.personCode, p.type, p.lastName, p.firstName, a.street, a.city, a.state, a.country, a.zip from Person p 
left join Address a on a.addressId=p.addressId;
-- 
-- 3. A query to get the email addresses of a specific person
select e.email, p.lastName, p.firstName from Email e left join Person p on e.personCode = p.personCode where p.personCode = 'E3SBKH0OK6';
-- 
-- 4. A query to change the email address of a specific email record
#New email updated:
update Email set email = 'mm300@hawaii.edu' where emailId = 4;
 #Existing email changed: 
-- 
-- 5. A query (or series of queries) to remove a specific person record

-- 
-- 6. A query to get all the items on a specific sales record
select s.saleCode, s.customerId, s.storeId, s.salesPersonId, i.itemId from Sale s left join SaleItem i on s.saleId = i.saleId;
-- 
-- 7. A query to get all the items purchased by a specific person
select s.saleCode, s.customerId, i.itemId, p.firstName, p.lastName from Sale s 
left join SaleItem i on s.saleId = i.saleId left join Person p on p.personId=s.customerId where personId = 1;
-- 
-- 8. A query to find the total number of sales made at each store

-- 
-- 9. A query to find the total number of sales made by each employee

-- 
-- 10. A query to find the total charge of all services in each sale (hint: you can take an aggregate of a mathematical expression)

-- 
-- 11. A query to detect invalid data in sales as follows. In a single sale, a particular product should only appear once (since any 
-- number of units can be consolidated to a single record). Write a query to find any sale that includes multiple instances of the same product.

-- 
-- 12. Write a query to detect a potential instance of fraud where an employee makes a sale to themselves (the same person is the sales person as well as the customer).

-- 
-- 13. Write a query to detect possible fraud where an employee is using their employee discount to make a lot of gift card purchases. 
-- This would include only sales made by an employee to themselves and an amount of over $250 or more (totaled over all sales, not just 
-- one so they can’t “hide” the fraud by making many small purchases).

-- 
-- 
-- 
-- 
