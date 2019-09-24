package com.cellar.wine.services;

import com.cellar.wine.models.Closure;

import java.util.List;

public interface ClosureService extends CrudService<Closure, Long> {

    List<Closure> findAll();
}
