/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.ui;

import java.util.List;

/**
 * UI factory template
 * @param <E> The entity
 * @param <T> The UI type
 */
public interface FactoryUI<E, T> {

    /**
     * Create
     * @param e The entity
     * @return The UI
     */
    T create(E e);

    /**
     * Create a list
     * @param es The entities
     * @return The UIs
     */
    List<T> createList(List<E> es);
}
