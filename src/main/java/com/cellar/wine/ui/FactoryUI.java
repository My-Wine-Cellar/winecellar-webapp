package com.cellar.wine.ui;

import java.util.List;

public interface FactoryUI<E, T> {

    T create(E e);

    List<T> createList(List<E> es);

}
