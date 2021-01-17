package com.qa.ims.persistence.dao;

public interface DaoExtended<T> extends Dao<T> {
	
	T addTo(T t, long id);
	
	T deleteFrom(T t, long id);

}
