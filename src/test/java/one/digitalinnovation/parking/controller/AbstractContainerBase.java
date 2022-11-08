package one.digitalinnovation.parking.controller;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerBase {

    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static{
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:10-alpine");
        POSTGRE_SQL_CONTAINER.start();
    }

}
