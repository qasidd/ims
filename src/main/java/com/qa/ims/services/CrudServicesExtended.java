package com.qa.ims.services;

public interface CrudServicesExtended<T> extends CrudServices<T> {
	
	T addTo(Long id, Long idToBeAdded);
	
	T deleteFrom(Long id, Long idToBeDeleted);
	
	double calculateCost(T t);
}
