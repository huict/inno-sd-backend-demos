package nl.hu.ict.inno.twophase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    @Qualifier("pg1")
    public LocalContainerEntityManagerFactoryBean pg1Factory(
            EntityManagerFactoryBuilder builder
    ) {
        return builder.dataSource(pg1DataSource())
                .packages("nl.hu.ict.inno.twophase")
                .build();
    }

    @Bean
    @Qualifier("pg1")
    public EntityManager pg1Manager(@Qualifier("pg1") EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.pg2")
    public DataSourceProperties pg2() {
        return new DataSourceProperties();
    }

//    @Bean
//    @Qualifier("pg2")
//    public DataSource pg2DataSource() {
//        return pg2()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//
//
//    @Bean
//    @Qualifier("pg2")
//    public LocalContainerEntityManagerFactoryBean pg2Factory(
//            @Qualifier("pg2") DataSource ds,
//            EntityManagerFactoryBuilder builder
//    ) {
//        return builder.dataSource(ds)
//                .packages("nl.hu.ict.inno.twophase")
//                .build();
//    }
//
//    @Bean
//    @Qualifier("pg2")
//    public EntityManager pg2Manager(@Qualifier("pg2") EntityManagerFactory factory) {
//        return factory.createEntityManager();
//    }


}
