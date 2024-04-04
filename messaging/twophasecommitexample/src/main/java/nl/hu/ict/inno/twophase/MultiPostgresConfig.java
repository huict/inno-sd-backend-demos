package nl.hu.ict.inno.twophase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class MultiPostgresConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.pg1")
    public DataSourceProperties pg1() {
        return new DataSourceProperties();
    }


    @Bean
    @Qualifier("pg1")
    public DataSource pg1DataSource() {
        return pg1()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.pg2")
    public DataSourceProperties pg2() {
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("pg2")
    public DataSource pg2DataSource() {
        return pg2()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Qualifier("pg1")
    public JdbcTemplate pg1Template(
            @Qualifier("pg1") DataSource ds){
        return new JdbcTemplate(ds);
    }

    @Bean
    @Qualifier("pg2")
    public JdbcTemplate pg2Template(
            @Qualifier("pg2") DataSource ds){
        return new JdbcTemplate(ds);
    }

}
