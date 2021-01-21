--insert into ims_test.customers(first_name, surname) values('rhys', 'thompson');
--insert into ims_test.customers(first_name, surname) values('nic', 'johnson');
--insert into ims_test.customers(first_name, surname) values('jordon', 'harrison');
--insert into ims_test.customers(first_name, surname) values('Leighton', 'Manning');
--insert into ims_test.items(itemName, itemPrice) values('xbox', 499.99);
insert into ims_test.orders(customerId, postcode) values(1, "postcode");
insert into ims_test.orderlines(orderId, itemId) values(1, 1);
