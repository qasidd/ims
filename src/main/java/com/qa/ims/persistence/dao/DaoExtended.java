package com.qa.ims.persistence.dao;

public interface DaoExtended<T> extends Dao<T> {
	
	T addTo(T t, Long id);
	
	T deleteFrom(T t, Long id);

}
