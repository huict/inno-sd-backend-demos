package nl.hu.ict.inno.twophase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.Properties;

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

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("pg1");
        ds.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties p = new Properties();
        p.setProperty ( "user" , "postgres" );
        p.setProperty ( "password" , "1q2w3e!" );
        p.setProperty ( "serverName" , "localhost" );
        p.setProperty ( "portNumber" , "15432" );
        p.setProperty ( "databaseName" , "twophase" );
        ds.setXaProperties ( p );
        return ds;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.pg2")
    public DataSourceProperties pg2() {
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("pg2")
    public DataSource pg2DataSource() {

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("pg2");
        ds.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
        Properties p = new Properties();
        p.setProperty ( "user" , "postgres" );
        p.setProperty ( "password" , "1q2w3e!" );
        p.setProperty ( "serverName" , "localhost" );
        p.setProperty ( "portNumber" , "15433" );
        p.setProperty ( "databaseName" , "twophase" );
        ds.setXaProperties ( p );
        return ds;
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
