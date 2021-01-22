package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesTest {
	
	@Mock
	private Dao<Customer> customerDao;
	
	@InjectMocks
	private CustomerServices customerServices;
	
	@Test
	public void createTest() {
		Customer customer = new Customer("chris", "perrins");
		customerServices.create(customer);
		Mockito.verify(customerDao, Mockito.times(1)).create(customer);
	}
	
	@Test
	public void readAllTest() {
		customerServices.readAll();
		Mockito.verify(customerDao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void readByIdTest() {
		customerServices.readById(1L);
		Mockito.verify(customerDao, Mockito.times(1)).readById(1L);
	}
	
	@Test
	public void updateTest() {
		Customer customer = new Customer("chris", "perrins");
		customerServices.update(customer);
		Mockito.verify(customerDao, Mockito.times(1)).update(customer);
	}
	
	@Test
	public void deleteTest() {
		customerServices.delete(1L);;
		Mockito.verify(customerDao, Mockito.times(1)).delete(1L);
	}
}
