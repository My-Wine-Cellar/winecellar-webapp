/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.model;

import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newValue) {
        if (id != null && !id.equals(newValue))
            throw new RuntimeException("Class: " + this.getClass().getName() + ", Id: " + id + ", NewValue" + newValue);

        id = newValue;
    }

    public boolean isNew() {
        return id == null;
    }
}
