package org.gfg.junitmock.demo.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSource {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public javax.sql.DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }
}
