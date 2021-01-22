package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Customer;

public class CusterDaoMysqlTest {
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

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
			statement.executeUpdate("delete from customers;");
			statement.executeUpdate("alter table customers AUTO_INCREMENT=1");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Test
	public void createTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		String fn = "leighton";
		String sn = "manning";

		Customer customer = new Customer(fn, sn);
		Customer savedCustomer = new Customer(1L, fn, sn);

		customer = customerDaoMysql.create(customer);
		assertEquals(savedCustomer, customer);
	}

	@Test
	public void readTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		List<Customer> customerList = List.of(new Customer(1L, "Leighton", "Manning"), new Customer(2L, "Paul", "Test"),
				new Customer(3L, "Ben", "Bob"));
		for (Customer c : customerList) {
			customerDaoMysql.create(c);
		}
		assertEquals(customerList, customerDaoMysql.readAll());
	}

	@Test
	public void updateTest() {
		// create an instance of the class
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(username, password);
		// setting vals
		String fn = "leighton";
		String sn = "manning";
		// create a new customer
		Customer customer = new Customer(fn, sn);
		//
		Customer savedCustomer = new Customer(1L, fn, sn);

		customer = customerDaoMysql.update(customer);
		assertNotEquals(savedCustomer, customer);
	}

	@Test
	public void deleteTest() {
		// create an instance of the class
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		// setting vals
		String fn = "leighton";
		String sn = "manning";
		Long id = 1L;
		// create a new customer
		Customer customer = new Customer(fn, sn);
		//
		customer = customerDaoMysql.create(customer);

		customerDaoMysql.delete(id);

	}

	@Test
	public void readLatestTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrl, username, password);
		String fn = "leighton";
		String sn = "manning";
		Long id = 1L;
		Long id2 = 2L;
		Customer customer = new Customer(id, fn, sn);
		Customer customer1 = new Customer(id2, fn, sn);
		Customer savedCustomer = new Customer(2L, fn, sn);

		customerDaoMysql.create(customer);
		customerDaoMysql.create(customer1);
		assertEquals(savedCustomer, customerDaoMysql.readLatest());
	}

}
