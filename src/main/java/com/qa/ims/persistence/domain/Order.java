package com.qa.ims.persistence.domain;

import java.util.HashSet;
import java.util.Set;

public class Order {
	
	private Long id;
	private Long customerId;
	private Set<OrderItem> orderItemSet;
	
	{
		orderItemSet = new HashSet<>();
	}
	
	public Order(Long id, Long customerId) {
		this.id = id;
		this.customerId = customerId;
	}
	
	public Order(Long customerId) {
		this.customerId = customerId;
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

	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}
	
	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderItemSet == null) ? 0 : orderItemSet.hashCode());
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
		if (orderItemSet == null) {
			if (other.orderItemSet != null)
				return false;
		} else if (!orderItemSet.equals(other.orderItemSet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", orderItemSet=" + orderItemSet + "]";
	}
}
