package com.newproject.marketplace.service;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DatabaseMigration {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void doPatchForSchema() {
        Flyway.configure()
                .dataSource(jdbcTemplate.
                        getDataSource()).locations("classpath:db/migration").load().repair();


        Flyway.configure()
                .dataSource(jdbcTemplate.
                        getDataSource()).locations("classpath:db/migration").load().migrate();
    }

}
