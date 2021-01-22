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

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Item;

public class ItemDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(ItemDaoMysql.class);
	
	private static String jdbcConnectionUrl = "jdbc:mysql://" + Ims.IP_ADDRESS + ":3306/";
	private static String jdbcConnectionUrlTest = jdbcConnectionUrl + "ims_test";
	private static String username = "root";
	private static String password = "root";
	
	ItemDaoMysql itemDaoMysql = new ItemDaoMysql(jdbcConnectionUrlTest, username, password);

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "sql-schema.sql");
	}
	
	@Before
	public void setup() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrlTest, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from items;");
			statement.executeUpdate("alter table items AUTO_INCREMENT=1");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Test
	public void createTest() {
		String title = "Pen";
		Double price = 100.0;
		
		Item item = new Item(title, price);
		Item savedItem = new Item(1L, title, price);
		
		item = itemDaoMysql.create(item);
		assertEquals(savedItem, item);
	}
	
	@Test
	public void readTest() {
		List<Item> itemList = List.of(new Item(1L, "Pen", 100.0), new Item(2L, "Bottle", 50.0), new Item(3L, "Pencil", 160.0));
		for (Item c : itemList) {
			itemDaoMysql.create(c);
		}
		assertEquals(itemList, itemDaoMysql.readAll());
	}
	
	@Test
	public void updateTest() {
		String title = "Pen";
		Double price = 100.0;
		String titleUpdate = "Bottle";
		Double priceUpdate = 50.0;
		Item item = new Item(title, price);
		Item itemUpdate = new Item(1L, titleUpdate, priceUpdate);
		Item savedItem = new Item(1L, titleUpdate, priceUpdate);
		
		itemDaoMysql.create(item);
		itemUpdate = itemDaoMysql.update(itemUpdate);
		assertEquals(savedItem, itemUpdate);
	}
	
	@Test
	public void deleteTest() {
		String title1 = "Pen";
		Double price1 = 100.0;
		String title2 = "Bottle";
		Double price2 = 50.0;
		Item item1 = new Item(title1, price1);
		Item item2 = new Item(title2, price2);
		
		itemDaoMysql.create(item1);
		itemDaoMysql.create(item2);
		itemDaoMysql.delete(1L);
		assertNull(itemDaoMysql.readById(1L));
	}
}
