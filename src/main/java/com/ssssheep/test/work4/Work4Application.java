package com.ssssheep.test.work4;

import com.ssssheep.test.work4.config.LettuceRedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
* created by IDEA
* @author Xinrui Yu
* @date 2022/5/12 20:43
**/
@SpringBootApplication
@EnableCaching
public class Work4Application {
    public static void main(String[] args) {
        SpringApplication.run(Work4Application.class, args);
    }

}
