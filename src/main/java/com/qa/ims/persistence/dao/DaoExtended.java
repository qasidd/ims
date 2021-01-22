package com.qa.ims.persistence.dao;

public interface DaoExtended<T> extends Dao<T> {
	
	T addTo(long id, long idToBeAdded);
	
	T deleteFrom(long id, long idToBeDeleted);

	double calculateCost(T t);
}
