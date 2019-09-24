package com.cellar.wine.services;

import com.cellar.wine.models.Closure;

import javax.persistence.OrderBy;
import java.util.List;

public interface ClosureService extends CrudService<Closure, Long> {

    @OrderBy
    List<Closure> findAll();
}
