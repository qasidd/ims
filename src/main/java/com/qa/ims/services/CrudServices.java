package com.qa.ims.services;

import java.util.List;

public interface CrudServices<T> {

    public List<T> readAll();
     
    T readById(Long id);
    
    T create(T t);
     
    T update(T t);
 
    void delete(Long id);

}
