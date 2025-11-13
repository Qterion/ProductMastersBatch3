package org.example;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "org.example")
public class DependencyInjectorConfig {

    // GreetingService beans for EASY task
    @Bean
    public GreetingService englishGreetingService() {
        return new EnglishGreetingService();
    }

    @Bean
    public GreetingService russianGreetingService() {
        return new RussianGreetingService();
    }

    // PaymentProcessor beans are defined via @Component annotations
    // Default payment processor (VisaCard is set as default via @Qualifier in OrderService)
    // OrderService is defined via @Service annotation (singleton by default)
}
