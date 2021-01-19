package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.DaoExtended;
import com.qa.ims.persistence.domain.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderServicesTest {
	
	@Mock
	private DaoExtended<Order> orderDao;
	
	@InjectMocks
	private OrderServices orderServices;
	
	@Test
	public void readAllTest() {
		orderServices.readAll();
		Mockito.verify(orderDao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void readByIdTest() {
		orderServices.readById(1L);
		Mockito.verify(orderDao, Mockito.times(1)).readById(1L);
	}
	
	@Test
	public void createTest() {
		Order order = new Order(1L);
		orderServices.create(order);
		Mockito.verify(orderDao, Mockito.times(1)).create(order);
	}
	
	@Test
	public void updateTest() {
		Order order = new Order(1L);
		orderServices.update(order);
		Mockito.verify(orderDao, Mockito.times(1)).update(order);
	}
	
	@Test
	public void addToTest() {
		orderServices.addTo(1L, 2L);
		Mockito.verify(orderDao, Mockito.times(1)).addTo(1L, 2L);
	}
	
	@Test
	public void deleteFromTest() {
		orderServices.deleteFrom(1L, 2L);
		Mockito.verify(orderDao, Mockito.times(1)).deleteFrom(1L, 2L);
	}
	
	@Test
	public void deleteTest() {
		orderServices.delete(1L);
		Mockito.verify(orderDao, Mockito.times(1)).delete(1L);
	}
	
	@Test
	public void calculateCostTest() {
		Order order = new Order(1L);
		orderServices.calculateCost(order);
		Mockito.verify(orderDao, Mockito.times(1)).calculateCost(order);
	}
}
