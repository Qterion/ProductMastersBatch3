package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingController {
    
    @Autowired
    @Qualifier("englishGreetingService") // Inject EnglishGreetingService via @Qualifier
    private GreetingService greetingService;
    
    public void greet() {
        System.out.println(greetingService.sayHello());
    }
}

