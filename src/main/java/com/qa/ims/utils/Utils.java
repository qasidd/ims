package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.log4j.Logger;

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
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
				LOGGER.info("Please enter only a number!");
				scanner.nextLine();
			}
		}
	}
	
	public static double getDouble() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				return scanner.nextDouble();
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
				LOGGER.info("Please enter only a decimal!");
				scanner.nextLine();
			}
		}
	}
	
	public static long getLong() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				return scanner.nextLong();
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
				LOGGER.info("Please enter only a number!");
				scanner.nextLine();
			}
		}
	}
}
