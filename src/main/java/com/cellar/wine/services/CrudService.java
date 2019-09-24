package com.cellar.wine.services;

public interface CrudService<T, ID> {

    T findById(ID id);

    T save(T object);

    void delete(T object);

}
