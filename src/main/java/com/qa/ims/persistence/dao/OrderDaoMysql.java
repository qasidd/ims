package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;

public class OrderDaoMysql implements DaoExtended<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://localhost:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerId = resultSet.getLong("customer_id");
		return new Order(id, customerId);
	}
	
	Order orderFromResultSet(ResultSet resultSetOrder, ResultSet resultSetOrderItems) throws SQLException {
		Long orderId = resultSetOrder.getLong("id");
		Long customerId = resultSetOrder.getLong("customer_id");
		Long orderItemId;
		Long itemId;
		Integer quantity;
		
		Set<OrderItem> orderItems = new HashSet<>();
		while (resultSetOrderItems.next()) {
			orderItemId = resultSetOrderItems.getLong("id");
			itemId = resultSetOrderItems.getLong("item_id");
			orderItems.add(new OrderItem(orderItemId, orderId, itemId));
		}
		
		return new Order(orderId, customerId, orderItems);
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
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an order in the database
	 * 
	 * @param order - takes in a order object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orders(customer_id) values('" + order.getCustomerId() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Reads an order using id from the database, as well as a set of items associated with the order
	 * in the intermediary table
	 * @param id - id of the orderItem
	 */
	@Override
	public Order readById(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statementOne = connection.createStatement();
				Statement statementTwo = connection.createStatement();
				ResultSet resultSetOrder = statementOne.executeQuery
						("SELECT * FROM orders where id = " + id);
				ResultSet resultSetOrderItems = statementTwo.executeQuery
						("SELECT * FROM orders_items where order_id = " + id);) {
			resultSetOrder.next();
			resultSetOrderItems.next();
			return orderFromResultSet(resultSetOrder, resultSetOrderItems);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order in the database
	 * 
	 * @param order - takes in a order object, the id field will be used to
	 *                 update that order in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set customer_id ='" + order.getCustomerId() + "' where id =" + order.getId());
			return readById(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order addTo(long id, long itemId) {
		Order order = readById(id);
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into orders_items(order_id, item_id) "
					+ "values(" + order.getId() + ", " + itemId + ")");
			return readById(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order deleteFrom(long id, long itemId) {
		Order order = readById(id);
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders_items "
					+ "where order_id = " + order.getId() 
					+ " and item_id = " + itemId
					+ " limit 1");
			return readById(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
//	public boolean checkIfItemAlreadyExistsInOrder(Order order, long itemId) {
//		for (OrderItem orderItem : order.getOrderItemSet()) {
//			if (orderItem.getItemId() == itemId) return true;
//		}
//		
//		return false;
//	}

	/**
	 * Deletes a order in the database
	 * 
	 * @param id - id of the order
	 */
	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders_items where order_id = " + id);
			statement.executeUpdate("delete from orders where id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Override
	public double calculateCost(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery
						("SELECT sum(price) as total_cost "
								+ "FROM items JOIN orders_items "
								+ "WHERE items.id = orders_items.item_id AND order_id = " + order.getId());) {
			resultSet.next();
			int totalCost = resultSet.getInt("total_cost");
			return totalCost;
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return -1;
	}

}
