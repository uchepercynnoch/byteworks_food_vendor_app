package com.byteworks.devops.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FoodInformationDataPersistClass {
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean populateFoodMenuRepository() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("data.json")});
        return factory;
    }
}
