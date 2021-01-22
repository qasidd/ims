
package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private ItemServices itemServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the item
	 * controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> items = new ArrayList<>();
		items.add(new Item("iPhone 12", 899.0));
		items.add(new Item("MacBook Air", 999.0));
		items.add(new Item("Mac mini", 699.0));
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.read());
	}

	@Test
	public void createTest() {
		String title = "iPhone 12";
		Double price = 899.0;
		Mockito.doReturn(title).when(itemController).getInput();
		Mockito.doReturn(price).when(itemController).getDouble();
		
		Item item = new Item(title, price);
		Item savedItem = new Item(1L, title, price);
		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}

	@Test
	public void updateTest() {
		Long id = 1L;
		String title = "MacBook Air";
		Double price = 999.0;
		Mockito.doReturn(id).when(itemController).getLong();
		Mockito.doReturn(title).when(itemController).getInput();
		Mockito.doReturn(price).when(itemController).getDouble();
		
		Item item = new Item(Long.valueOf(id), title, Double.valueOf(price));
		Mockito.when(itemServices.update(item)).thenReturn(item);
		assertEquals(item, itemController.update());
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		Long id = 1L;
		Mockito.doReturn(id).when(itemController).getLong();
		
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}

}
