package com.qa.ims.services;

public interface CrudServicesExtended<T> extends CrudServices<T> {
	
	T addTo(T t, Long id);
	
	T deleteFrom(T t, Long id);
}
