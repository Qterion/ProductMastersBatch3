package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(DependencyInjectorConfig.class);

        // EASY task: Test GreetingService with @Autowired injection
        System.out.println("=== EASY TASK: GreetingService ===");
        // Demonstrate @Autowired by using a component that has @Autowired
        GreetingController greetingController = context.getBean(GreetingController.class);
        System.out.print("Greeting via @Autowired: ");
        greetingController.greet();
        
        // Also show both implementations
        GreetingService englishGreeting = context.getBean("englishGreetingService", GreetingService.class);
        GreetingService russianGreeting = context.getBean("russianGreetingService", GreetingService.class);
        System.out.println("English: " + englishGreeting.sayHello());
        System.out.println("Russian: " + russianGreeting.sayHello());
        System.out.println();

        // MEDIUM task: Test new payment processors
        System.out.println("=== MEDIUM TASK: New Payment Processors ===");
        PaymentProcessor bitcoinProcessor = context.getBean("bitcoinPaymentProcessor", PaymentProcessor.class);
        PaymentProcessor plovCoinProcessor = context.getBean("plovCoinPaymentProcessor", PaymentProcessor.class);
        
        System.out.println("Testing Bitcoin payment:");
        bitcoinProcessor.processPayment(BigDecimal.valueOf(100));
        
        System.out.println("Testing PlovCoin payment:");
        plovCoinProcessor.processPayment(BigDecimal.valueOf(50));
        System.out.println();

        // HARD task: Test OrderService with @Qualifier and compare bean references
        System.out.println("=== HARD TASK: OrderService with @Qualifier ===");
        OrderService orderService = context.getBean(OrderService.class);
        orderService.makeOrder(BigDecimal.valueOf(15));

        OrderService orderServiceSecond = context.getBean(OrderService.class);
        orderServiceSecond.makeOrder(BigDecimal.valueOf(15));
        
        // Compare references (should be true for singleton scope)
        System.out.println("orderService == orderServiceSecond: " + (orderService == orderServiceSecond));
    }
}