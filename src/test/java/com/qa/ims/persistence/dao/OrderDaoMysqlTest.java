package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;

public class OrderDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);
	
	private static String jdbcConnectionUrl = "jdbc:mysql://localhost:3306/";
	private static String jdbcConnectionUrlTest = jdbcConnectionUrl + "ims_test";
	private static String username = "root";
	private static String password = "root";
	
	@Spy
	OrderDaoMysql orderDaoMysql = new OrderDaoMysql(jdbcConnectionUrlTest, username, password);

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "src/test/resources/sql-schema.sql");
		
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrlTest, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into customers(first_name, surname) "
					+ "values(\"Jim\", \"Bob\")");
			statement.executeUpdate("insert into customers(first_name, surname) "
					+ "values(\"Jane\", \"Doe\")");
			statement.executeUpdate("insert into customers(first_name, surname) "
					+ "values(\"Diego\", \"Maradona\")");
			
			statement.executeUpdate("insert into items(title, price) "
					+ "values(\"Macbook Pro\", 1300.0)");
			statement.executeUpdate("insert into items(title, price) "
					+ "values(\"Mac mini\", 700.0)");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Before
	public void setup() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrlTest, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orders_items");
			statement.executeUpdate("delete from orders;");
			statement.executeUpdate("alter table orders AUTO_INCREMENT=1");
			statement.executeUpdate("alter table orders_items AUTO_INCREMENT=1");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Test
	public void createTest() {
		long customerId = 1L;
		Order order = new Order(customerId);
		Order savedOrder = new Order(1L, customerId);
		
		order = orderDaoMysql.create(order);
		assertEquals(savedOrder, order);
	}
	
	@Test
	public void readAllTest() {
		List<Order> orderList = List.of(new Order(1L, 1L), new Order(2L, 2L), new Order(3L, 3L));
		for (Order o : orderList) {
			orderDaoMysql.create(o);
		}
		assertEquals(orderList, orderDaoMysql.readAll());
	}
	

	@Test
	public void readByIdTest() {
		long customerId = 1L;
		Order order = new Order(customerId);
		Order savedOrder = new Order(1L, customerId);
		
		orderDaoMysql.create(order);
		assertEquals(savedOrder, orderDaoMysql.readById(1L));
	}
	
	@Test
	public void readLatestTest() {
		long customerId1 = 1L;
		long customerId2 = 2L;
		Order order1 = new Order(customerId1);
		Order order2 = new Order(customerId2);
		Order savedOrder2 = new Order(2L, customerId2);
		
		orderDaoMysql.create(order1);
		orderDaoMysql.create(order2);
		assertEquals(savedOrder2, orderDaoMysql.readLatest());
	}
	
	@Test
	public void updateTest() {
		long customerId = 1L;
		long customerIdUpdate = 2L;
		Order order = new Order(customerId);
		Order orderUpdate = new Order(1L, customerIdUpdate);
		Order savedOrder = new Order(1L, customerIdUpdate);
		
		orderDaoMysql.create(order);
		orderUpdate = orderDaoMysql.update(orderUpdate);
		assertEquals(savedOrder, orderUpdate);
	}
	
	@Test
	public void deleteTest() {
		long customerId1 = 1L;
		long customerId2 = 2L;
		Order order1 = new Order(customerId1);
		Order order2 = new Order(customerId2);
		
		orderDaoMysql.create(order1);
		orderDaoMysql.create(order2);
		orderDaoMysql.delete(1L);
		assertNull(orderDaoMysql.readById(1L));
	}
	
	@Test
	public void addToTest() {
		long customerId = 1L;
		long itemId = 2L;
		Order order = new Order(customerId);
		Order savedOrder = new Order(1L, customerId, List.of(new OrderItem(1L, customerId, itemId)));
		
		orderDaoMysql.create(order);
		order = orderDaoMysql.addTo(1L, itemId);
		assertEquals(savedOrder, order);
	}
	
	@Test
	public void deleteFromTest() {
		long customerId = 1L;
		long itemId1 = 2L;
		long itemId2 = 3L;
		Order order = new Order(customerId);
		Order savedOrder = new Order(1L, customerId, List.of(new OrderItem(1L, customerId, itemId1)));
		
		orderDaoMysql.create(order);
		orderDaoMysql.addTo(1L, itemId1);
		orderDaoMysql.addTo(1L, itemId2);
		order = orderDaoMysql.deleteFrom(1L, itemId2);
		assertEquals(savedOrder, order);
	}
	
	@Test
	public void calculateCostTest() {
		long customerId = 1L;
		long itemId1 = 1L;
		long itemId2 = 2L;
		Order order = new Order(1L, customerId);
		
		orderDaoMysql.create(order);
		orderDaoMysql.addTo(1L, itemId1);
		orderDaoMysql.addTo(1L, itemId2);
		assertEquals(2000.0, orderDaoMysql.calculateCost(order), 0);
	}
}
