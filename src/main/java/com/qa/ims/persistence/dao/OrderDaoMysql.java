package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysql implements Dao<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://34.105.148.130:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("orderId");
		Long customerId = resultSet.getLong("customerId");
		String postcode = resultSet.getString("postcode");
		return new Order(orderId, customerId, postcode);
	}

	/**
	 * Reads all orders from the database
	 * 
	 * @return A list of orders
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orderId DESC LIMIT 1");
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a order in the database
	 * 
	 * @param order - takes in a order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orders(customerId, postcode) values('" + order.getCustomerId() + "','"
					+ order.getPostcode() + "')");

			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrder(Long orderId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where orderId = " + orderId);) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order in the database
	 * 
	 * @param order - takes in a order object, the id field will be used to update
	 *              that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		Item item = order.getItem();
		int quantity = order.getQuantity();

		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orderlines(orderId,itemId, quantity)" + "VALUES(" + order.getOrderId()
					+ ", " + item.getId() + ", " + quantity + ")");
			return readOrder(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order updateOrder(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(
					"UPDATE orders set postcode ='" + order.getPostcode() + "' where orderId =" + order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return order;
	}

	/**
	 * Deletes a order in the database
	 * 
	 * @param id - id of the order
	 */
	@Override
	public void delete(long orderlineId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orderlines where orderlineId = " + orderlineId);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void deleteorder(long orderlineId, long orderId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orderlines where orderlineId = " + orderlineId);
			statement.executeUpdate("delete from orderlines where orderId = " + orderId);
			statement.executeUpdate("delete from orders where orderId = " + orderId);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
