package com.cellar.wine.hibernate;

import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

public class CustomDialect extends PostgreSQL95Dialect {

    public CustomDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
