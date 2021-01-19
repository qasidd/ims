package com.qa.ims.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDaoMysql;

public class Utils {
	
	public static final Logger LOGGER = Logger.getLogger(Utils.class);
	
	private Utils () {
		
	}

	public static String getInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	public static int getInt() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				LOGGER.error(e.getMessage());
				LOGGER.info("Please enter only a number!");
			}
		}
	}
	
	public static double getDouble() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				return scanner.nextDouble();
			} catch (InputMismatchException e) {
				LOGGER.error(e.getMessage());
				LOGGER.info("Please enter only a decimal!");
			}
		}
	}
	
	public static long getLong() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				return scanner.nextLong();
			} catch (InputMismatchException e) {
				LOGGER.error(e.getMessage());
				LOGGER.info("Please enter only a number!");
			}
		}
	}
}
