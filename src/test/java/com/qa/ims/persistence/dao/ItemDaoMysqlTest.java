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
import com.qa.ims.persistence.domain.Item;

public class ItemDaoMysqlTest {
	public static final Logger LOGGER = Logger.getLogger(ItemDaoMysql.class);

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
			statement.executeUpdate("delete from items;");
			statement.executeUpdate("alter table items AUTO_INCREMENT=1");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Test
	public void readTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);
		List<Item> itemList = List.of(new Item(1L, "Xbox", 499.99), new Item(2L, "PS", 599.99),
				new Item(3L, "Wii", 299));
		for (Item i : itemList) {
			itemDaoMysql.create(i);
		}
		assertEquals(itemList, itemDaoMysql.readAll());
	}

	@Test
	public void createTest() {

		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);

		String in = "xbox";
		double ip = 499.99;

		Item item = new Item(in, ip);
		//
		Item savedItem = new Item(1L, in, ip);

		item = itemDaoMysql.create(item);
		assertEquals(savedItem, item);
	}

	@Test
	public void updateTest() {

		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(username, password);

		String in = "xbox";
		double ip = 499.99;

		Item item = new Item(in, ip);
		//
		Item savedItem = new Item(1L, in, ip);

		item = itemDaoMysql.update(item);
		assertNotEquals(savedItem, item);
	}

	@Test
	public void deleteTest() {

		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrl, username, password);

		String in = "xbox";
		double ip = 499.99;

		Item item = new Item(2L, in, ip);
		//
		Item savedItem = new Item(1L, in, ip);

		itemDaoMysql.delete(1L);
		assertNotEquals(item, savedItem);
	}
}
