
package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.action.OrderReadAction;
import com.qa.action.OrderUpdateAction;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private OrderServices orderServices;

	/**
	 * Spy is used because i want to mock some methods inside the order I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the order
	 * controller
	 */
	@Spy
	@InjectMocks
	private OrderController orderController;
	
	@Test
	public void readAllTest() {
		Mockito.doReturn(OrderReadAction.ALL).when(orderController).getReadAction();
		
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L));
		orders.add(new Order(2L));
		orders.add(new Order(3L));
		
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		
		assertEquals(orders, orderController.read());
	}
	
	@Test
	public void readOneTest() {
		String id = "1";
		Mockito.doReturn(OrderReadAction.ONE).when(orderController).getReadAction();
		Mockito.doReturn(id).when(orderController).getInput();
		
		List<Order> orders = new ArrayList<>();
		Order order = new Order(Long.valueOf(id));
		orders.add(order);
		Mockito.when(orderServices.readById(Long.valueOf(id))).thenReturn(order);
		assertEquals(orders, orderController.read());
	}

	@Test
	public void createTest() {
		String customerId = "1";
		Mockito.doReturn(customerId).when(orderController).getInput();
		
		Order order = new Order(Long.valueOf(customerId));
		Order savedOrder = new Order(1L, 1L);
		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
		assertEquals(savedOrder, orderController.create());
	}

	@Test
	public void updateAddToTest() {
		String id = "1";
		OrderUpdateAction action = OrderUpdateAction.ADD;
		String itemId = "2";
		
		Mockito.doReturn(id, itemId).when(orderController).getInput();
		Mockito.doReturn(action).when(orderController).getUpdateAction();
		
		List<OrderItem> list = new ArrayList<>();
		list.add(new OrderItem(Long.valueOf(id), Long.valueOf(itemId)));
		Order order = new Order(Long.valueOf(id), 1L, list);
		Mockito.when(orderServices.addTo(Long.valueOf(id), Long.valueOf(itemId))).thenReturn(order);
		assertEquals(order, orderController.update());
	}
	
	@Test
	public void updateDeleteFromTest() {
		String id = "1";
		OrderUpdateAction action = OrderUpdateAction.DELETE;
		String itemId = "2";
		
		Mockito.doReturn(id, itemId).when(orderController).getInput();
		Mockito.doReturn(action).when(orderController).getUpdateAction();
		
		List<OrderItem> list = new ArrayList<>();
		Order order = new Order(Long.valueOf(id), 1L, list);
		Mockito.when(orderServices.deleteFrom(Long.valueOf(id), Long.valueOf(itemId))).thenReturn(order);
		assertEquals(order, orderController.update());
	}


	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}
	
//	@Test
//	public void readAllTest() {
//		List<Order> orders = new ArrayList<>();
//		orders.add(new Order(1L));
//		orders.add(new Order(2L));
//		orders.add(new Order(3L));
//		
//		Mockito.when(orderServices.readAll()).thenReturn(orders);
//		
//		assertEquals(orders, orderController.readAll());
//	}
	
//	@Test
//	public void readOneTest() {
//		String id = "1";
//		Mockito.doReturn(id).when(orderController).getInput();
//		
//		List<Order> orders = new ArrayList<>();
//		Order order = new Order(Long.valueOf(id));
//		orders.add(order);
//		Mockito.when(orderServices.readById(Long.valueOf(id))).thenReturn(order);
//		assertEquals(orders, orderController.readOne());
//	}
	
//	@Test
//	public void addToTest() {
//		String id = "1";
//		String itemId = "2";
//		Mockito.doReturn(itemId).when(orderController).getInput();
//		
//		List<OrderItem> list = new ArrayList<>();
//		list.add(new OrderItem(Long.valueOf(id), Long.valueOf(itemId)));
//		Order order = new Order(Long.valueOf(id), 1L, list);
//		Mockito.when(orderServices.addTo(Long.valueOf(id), Long.valueOf(itemId))).thenReturn(order);
//		assertEquals(order, orderController.addTo(Long.valueOf(id)));
//	}
	
//	@Test
//	public void deleteFromTest() {
//		String id = "1";
//		String itemId = "2";
//		Mockito.doReturn(itemId).when(orderController).getInput();
//		
//		Order order = new Order(Long.valueOf(id), 1L);
//		Mockito.when(orderServices.deleteFrom(Long.valueOf(id), Long.valueOf(itemId))).thenReturn(order);
//		assertEquals(order, orderController.deleteFrom(Long.valueOf(id)));
//	}
	
}
