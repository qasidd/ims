package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.Customer;

public class CustomerDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);
	
	private static String jdbcConnectionUrl = "jdbc:mysql://localhost:3306/";
	private static String jdbcConnectionUrlTest = jdbcConnectionUrl + "ims_test";
	private static String username = "root";
	private static String password = "root";

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "src/test/resources/sql-schema.sql");
	}
	
	@Before
	public void setup() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrlTest, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from customers;");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
	
	@Test
	public void createTest() {
		// create an instance of the class
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrlTest, username, password);
		
		// setting values
		String firstName = "Jim";
		String surname = "Bob";
		
		// create a new customer
		Customer customer = new Customer(firstName, surname);
		// customer to compare to
		Customer savedCustomer = new Customer(1L, firstName, surname);
		
		customer = customerDaoMysql.create(customer);
		assertEquals(savedCustomer, customer);
	}
}
