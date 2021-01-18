package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderItem;

public class OrderItemServices implements CrudServices<OrderItem> {

	private Dao<OrderItem> orderItemDao;
	
	public OrderItemServices(Dao<OrderItem> orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	
	public List<OrderItem> readAll() {
		return orderItemDao.readAll();
	}
	
	public OrderItem readById(Long id) {
		return orderItemDao.readById(id);
	}

	public OrderItem create(OrderItem orderItem) {
		return orderItemDao.create(orderItem);
	}

	public OrderItem update(OrderItem orderItem) {
		return orderItemDao.update(orderItem);
	}

	public void delete(Long id) {
		orderItemDao.delete(id);
	}

}
