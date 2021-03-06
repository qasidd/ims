package com.qa.ims;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.qa.action.EntityAction;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.ItemDaoMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.ItemServices;
import com.qa.ims.services.OrderServices;
import com.qa.ims.utils.Utils;

public class Ims {

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	
	public static final String IP_ADDRESS = "35.189.108.210";

	String getInput() {
		return Utils.getInput();
	}
	
	Domain getDomain() {
		return Domain.getDomain();
	}
	
	EntityAction getAction() {
		return EntityAction.getAction();
	}
	
	public void imsSystem() {
		LOGGER.info("What is your username");
		String username = getInput();
		LOGGER.info("What is your password");
		String password = getInput();

		if (init(username, password)) {
			while (entityMenu(username, password));
		}
	}
	
	public boolean entityMenu(String username, String password) {
		System.out.print("\n");
		LOGGER.info("Which entity would you like to use?");
		Domain.printDomains();

		Domain domain = getDomain();
		if (domain == Domain.STOP) { 
			LOGGER.info("Bye!");
			return false;
		}
		LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");

		EntityAction.printActions();
		EntityAction action = getAction();

		switch (domain) {
		case CUSTOMER:
			CustomerController customerController = new CustomerController(
					new CustomerServices(new CustomerDaoMysql(username, password)));
			doAction(customerController, action);
			break;
		case ITEM:
			ItemController itemController = new ItemController(
					new ItemServices(new ItemDaoMysql(username, password)));
			doAction(itemController, action);
			break;
		case ORDER:
			OrderController orderController = new OrderController(
					new OrderServices(new OrderDaoMysql(username, password)));
			doAction(orderController, action);
			break;
		default:
			break;
		}
		
		return true;
	}

	public void doAction(CrudController<?> crudController, EntityAction action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.read();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}

	/**
	 * To initialise the database schema. DatabaseConnectionUrl will default to
	 * localhost.
	 * 
	 * @param username
	 * @param password
	 */
	public boolean init(String username, String password) {
		return init("jdbc:mysql://" + IP_ADDRESS + ":3306/", username, password, "sql-schema.sql");
	}

	public String readFile(String fileLocation) {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(fileLocation);
		
		try {
			if (stream == null) {
				throw new Exception("Cannot find file " + fileLocation);
			}
			return IOUtils.toString(stream, StandardCharsets.UTF_8);
		} catch (Exception e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
		
		return "";
	}

	/**
	 * To initialise the database with the schema needed to run the application
	 */
	public boolean init(String jdbcConnectionUrl, String username, String password, String fileLocation) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				BufferedReader br = new BufferedReader(new StringReader(readFile(fileLocation)));) {
			String string;
			while ((string = br.readLine()) != null) {
				try (Statement statement = connection.createStatement();) {
					statement.executeUpdate(string);
				}
			}
		} catch (SQLException | IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
			return false;
		}
		
		return true;
	}

}
