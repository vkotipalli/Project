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
	select e.email, p.lastName, p.firstName from Email e 
    left join Person p on e.personId= p.personId 
    where p.personCode = 'E3SBKH0OK6';
-- 
-- 4. A query to change the email address of a specific email record
	#New email updated:
	update Email set email = 'mm300@hawaii.edu' where emailId = 4;
	#Existing email changed: 
    update Email set email = 'madisonmaloney2002@gmail.com' where emailId = 1;
    #To prove boths email's was updated (Mallory before didn't have an email and now she has 'mm300@hawaii.edu'
	#									 Madison's was changed from 'mmaloney2@unl.edu' to 'madisonmaloney2002@gmail.com'): 
    select * from Email;
-- 
-- 5. A query (or series of queries) to remove a specific person record
	delete from Email where emailId = 15;
    delete from Person where personId = 11;
	delete from Address where addressId = 21;
    #To prove deletion (Crystal does not appear and was deleted): 
    select * from Person;
-- 
-- 6. A query to get all the items on a specific sales record
	select s.saleId, s.saleCode, s.customerId, s.storeId, s.salesPersonId, si.itemId, i.itemName, i.cost from Sale s 
	left join SaleItem si on s.saleId = si.saleId
	left join Item i on si.itemId=i.itemId
	where s.saleCode = 'AB2Q7D53KT';
-- 
-- 7. A query to get all the items purchased by a specific person
	select s.saleCode, s.customerId, i.itemId, i.itemName, i.cost, p.firstName, p.lastName from Sale s 
	left join SaleItem si on s.saleId = si.saleId 
    left join Person p on p.personId=s.customerId
    left join Item i on i.itemId=si.itemId
    where personId = 1;
-- 
-- 8. A query to find the total number of sales made at each store
	select s.storeId, st.storeCode, count(s.saleId) as numSalesByStore from Sale s 
	left join Store st on st.storeId=s.storeId group by s.storeId;
-- 
-- 9. A query to find the total number of sales made by each employee
	select s.salespersonId, p.personCode, p.firstName, p.lastName, count(s.saleId) as numSalesByEmployee from Sale s 
	left join Person p on s.salespersonId=p.personId group by s.salespersonId;
-- 
-- 10. A query to find the total charge of all services in each sale (hint: you can take an aggregate of a mathematical expression)
-- Author Note: had service query implemented before handout change on 03/25/21.
	select si.saleId, si.itemId, i.itemCode, s.saleCode, avg(i.cost * i.numHours) as totalCharge from SaleItem si 
	left join Item i on si.itemId=i.itemid 
	left join Sale s on s.saleId=si.saleId
	where i.itemType like "SV%"
	group by i.itemCode;
-- 
-- 11. A query to detect invalid data in sales as follows. In a single sale, a particular product should only appear once (since any 
-- number of units can be consolidated to a single record). Write a query to find any sale that includes multiple instances of the same product.
	select s.saleId, s.saleCode, si.itemId, i.itemName, i.itemCode from Sale s
	inner join SaleItem si on s.saleId=si.saleId
    inner join Item i on si.itemId=i.itemId
    group by s.saleId, i.itemId
    having count(s.saleId) > 1;   
-- 
-- 12. Write a query to detect a potential instance of fraud where an employee makes a sale to themselves (the same person is the sales person as well as the customer).
	select s.salespersonId, p.lastName, p.firstName, p.personCode as salespersonCode, s.saleCode from Sale s
	inner join Person p on p.personId = s.salespersonId 
    where salespersonId = customerId;
-- 
-- 13. Write a query to detect possible fraud where an employee is using their employee discount to make a lot of gift card purchases. 
-- This would include only sales made by an employee to themselves and an amount of over $250 or more (totaled over all sales, not just 
-- one so they can’t “hide” the fraud by making many small purchases).
   select p.firstName, p.lastName, sum(i.cost) as purchaseTotal from Item i
   inner join SaleItem si on si.itemId=i.itemId
   inner join Sale s on s.saleId=si.saleId
   inner join Person p on p.personId=s.customerId
   where s.salespersonId = s.customerId
   group by s.salespersonId having sum(i.cost) >= 250;
-- 
-- 
-- 
-- 
