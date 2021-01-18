package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private Long id;
	private Long customerId;
	private List<OrderItem> orderItemList;
	
	public Order(Long id, Long customerId, List<OrderItem> orderItemList) {
		this.id = id;
		this.customerId = customerId;
		this.orderItemList = orderItemList;
	}
	
	public Order(Long id, Long customerId) {
		this.id = id;
		this.customerId = customerId;
		orderItemList = new ArrayList<>();
	}
	
	public Order(Long customerId) {
		this.customerId = customerId;
		orderItemList = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderItemList == null) ? 0 : orderItemList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderItemList == null) {
			if (other.orderItemList != null)
				return false;
		} else if (!orderItemList.equals(other.orderItemList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", orderItemList=" + orderItemList + "]";
	}
}
