/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.hibernate;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL95Dialect;

public class CustomDialect extends PostgreSQL95Dialect {

    /**
     * CustomDialect constructor
     */
    public CustomDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
