package com.ssssheep.test.work4.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created By Intellij IDEA
 *
 * @author Xinrui Yu
 * @date 2022/4/20 19:10 星期三
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
