package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	
	private CrudServices<Item> itemService;
	
	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}

	String getInput() {
		return Utils.getInput();
	}
	
	Long getLong() {
		return Utils.getLong();
	}
	
	Double getDouble() {
		return Utils.getDouble();
	}
	
	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> read() {
		List<Item> items = itemService.readAll();
		for(Item item: items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter the title");
		String title = getInput();
		LOGGER.info("Please enter a price");
		Double price = getDouble();
		Item item = itemService.create(new Item(title, price));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = getLong();
		LOGGER.info("Please enter the title");
		String title = getInput();
		LOGGER.info("Please enter a price");
		Double price = getDouble();
		Item item = itemService.update(new Item(id, title, price));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = getLong();
		itemService.delete(id);
	}
}
