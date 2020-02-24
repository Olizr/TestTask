package com.godel.olizarovich.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:database/application.properties", "classpath:database/sql_requests.properties"})
@ComponentScan(basePackages = {"com.godel.olizarovich.config", "com.godel.olizarovich.dao.access",
        "com.godel.olizarovich.services", "com.godel.olizarovich.models"})
public class SpringJdbcConfig {

    @Value("classpath:database/defineSchemaPostgresql.sql")
    private Resource createTableScript;
    @Value("classpath:database/fillDatabasePostgre.sql")
    private Resource fillTableScript;

    @Autowired
    private Environment environment;

    @Bean
    public DataSource postgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.user"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(createTableScript);
        populator.addScript(fillTableScript);
        return populator;
    }
}
