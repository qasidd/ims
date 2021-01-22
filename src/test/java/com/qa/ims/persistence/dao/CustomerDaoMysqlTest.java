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
import com.qa.ims.persistence.domain.Customer;

public class CustomerDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);
	
	private static String jdbcConnectionUrl = "jdbc:mysql://" + Ims.IP_ADDRESS + ":3306/";
	private static String jdbcConnectionUrlTest = jdbcConnectionUrl + "ims_test";
	private static String username = "root";
	private static String password = "root";
	
	CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(jdbcConnectionUrlTest, username, password);

	@BeforeClass
	public static void init() {
		Ims ims = new Ims();
		ims.init(jdbcConnectionUrl, username, password, "sql-schema.sql");
	}
	
	@Before
	public void setup() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrlTest, username, password);
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
		String firstName = "Jim";
		String surname = "Bob";
		
		Customer customer = new Customer(firstName, surname);
		Customer savedCustomer = new Customer(1L, firstName, surname);
		
		customer = customerDaoMysql.create(customer);
		assertEquals(savedCustomer, customer);
	}
	
	@Test
	public void readTest() {
		List<Customer> customerList = List.of(
				new Customer(1L, "Jim", "Bob"), 
				new Customer(2L, "Jane", "Doe"), 
				new Customer(3L, "John", "Cena"));
		for (Customer c : customerList) {
			customerDaoMysql.create(c);
		}
		assertEquals(customerList, customerDaoMysql.readAll());
	}
	
	@Test
	public void updateTest() {
		String firstName = "Jim";
		String surname = "Bob";
		String firstNameUpdate = "Jane";
		String surnameUpdate = "Doe";
		Customer customer = new Customer(firstName, surname);
		Customer customerUpdate = new Customer(1L, firstNameUpdate, surnameUpdate);
		Customer savedCustomer = new Customer(1L, firstNameUpdate, surnameUpdate);
		
		customerDaoMysql.create(customer);
		customerUpdate = customerDaoMysql.update(customerUpdate);
		assertEquals(savedCustomer, customerUpdate);
	}
	
	@Test
	public void deleteTest() {
		String firstName1 = "Jim";
		String surname1 = "Bob";
		String firstName2 = "Jane";
		String surname2 = "Doe";
		Customer customer1 = new Customer(firstName1, surname1);
		Customer customer2 = new Customer(firstName2, surname2);
		
		customerDaoMysql.create(customer1);
		customerDaoMysql.create(customer2);
		customerDaoMysql.delete(1L);
		assertNull(customerDaoMysql.readById(1L));
	}
}
