package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.dao.DaoExtended;
import com.qa.ims.persistence.domain.Order;

public class OrderServices implements CrudServicesExtended<Order> {

	private DaoExtended<Order> orderDao;
	
	public OrderServices(DaoExtended<Order> orderDao) {
		this.orderDao = orderDao;
	}
	
	public List<Order> readAll() {
		return orderDao.readAll();
	}
	
	public Order readById(Long id) {
		return orderDao.readById(id);
	}

	public Order create(Order order) {
		return orderDao.create(order);
	}

	public Order update(Order order) {
		return orderDao.update(order);
	}

	public Order addTo(Long orderId, Long itemId) {
		return orderDao.addTo(orderId, itemId);
	}

	public Order deleteFrom(Long orderId, Long itemId) {
		return orderDao.deleteFrom(orderId, itemId);
	}

	public void delete(Long id) {
		orderDao.delete(id);
	}
	
	public double calculateCost(Order order) {
		return orderDao.calculateCost(order);
	}

}
