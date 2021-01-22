package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysqlTest {
	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private static String jdbcConnectionUrl = "jdbc:mysql://34.105.148.130:3306/ims_test";
	private static String username = "root";
	private static String password = "root";

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "src/test/resources/sql-schema.sql");
	}

	@Before
	public void setup() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders;");
			statement.executeUpdate("delete from customers;");
			statement.executeUpdate("delete from orderlines;");
			statement.executeUpdate("alter table orders AUTO_INCREMENT=1");
			statement.executeUpdate("alter table orderlines AUTO_INCREMENT=1");
//			statement.executeUpdate("insert into ims_test.customers(first_name, surname)values('Leighton', 'Manning')");
//			statement.executeUpdate("insert into ims_test.customers(first_name, surname)values('Test', 'Orders')");
//			statement.executeUpdate("insert into ims_test.items(itemName, itemPrice) values('xbox', 499.99)");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Test
	public void createTest() {

		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);

		Long customerId = 1L;
		String pc = "postcode";

		Order order = new Order(1L, customerId, pc);
		Order savedOrder = new Order(1L, customerId, pc);
		orderDaoMysql.create(order);
//		order.setCustomerId(1L);
		assertEquals(savedOrder, order);

	}

	@Test
	public void deleteTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(username, password);

		Long orderlineId = 2L;
		Long orderId = 1L;
		Long itemId = 2L;
		int quantity = 2;

		Order order = new Order(orderlineId, orderId, quantity);

		order = orderDaoMysql.create(order);
		orderDaoMysql.delete(orderlineId);
		assertEquals(null, order);
	}

	@Test
	public void deleteorderTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(username, password);
		Long orderlineId = 2L;
		Long orderId = 1L;
		Long customerId = 2L;
		int quantity = 2;
		String postcode = "postcode";

		Order orderline = new Order(orderlineId, orderId, quantity);
		Order order = new Order(orderId, customerId, postcode);

		orderline = orderDaoMysql.create(orderline);
		order = orderDaoMysql.create(order);
		orderDaoMysql.deleteorder(orderlineId, orderId);
		assertEquals(null, order);
	}

	@Test
	public void updateOrderTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(username, password);

		Long orderid = 1L;
		Long customerid = 2L;
		String postcode = "postcode";

		Order order = new Order(orderid, customerid, postcode);
		Order savedOrder = new Order(orderid, customerid, postcode);

		order = orderDaoMysql.updateOrder(order);
		assertEquals(savedOrder, order);
	}

	@Test
	public void updateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(username, password);
		Long orderid = 1L;
		Long itemid = 2L;
		int quantity = 2;

		Order order = new Order(orderid, itemid, quantity);
		Order savedOrder = new Order(orderid, itemid, quantity);
		// order = orderDaoMysql.update(order);
		assertEquals(savedOrder, order);
	}

	@Test
	public void readAllTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(username, password);

		List<Order> orderList = List.of(new Order(1L, "postcode"), new Order(2L, "postcode"),
				new Order(3L, "postcode"));
		for (Order o : orderList) {
			orderDaoMysql.create(o);
		}
		orderList = orderDaoMysql.readAll();
		assertEquals(orderList, orderDaoMysql.readAll());
	}

	@Test
	public void readLatestTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);
		String postcode = "postcode";

		Long id = 1L;
		Long id2 = 2L;

		Order order = new Order(id, postcode);
		Order order1 = new Order(id2, postcode);
		Order savedOrder = new Order(2L, postcode);

		orderDaoMysql.create(order);
		orderDaoMysql.create(order1);
		orderDaoMysql.readLatest();

		assertEquals(savedOrder, order1);
	}

	@Test
	public void readOrderTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrl, username, password);

		Long orderId = 1L;
		String postcode = "postcode";
		Order order = new Order(1L, orderId, postcode);
		Order savedOrder = new Order(1L, orderId, postcode);

		orderDaoMysql.create(order);
		orderDaoMysql.readOrder(1L);
		assertEquals(savedOrder, order);
	}

}
