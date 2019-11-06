package com.steamedfish.spring.aspect.p6sql.demo;

import com.steamedfish.spring.aspect.p6sql.demo.model.Coffee;
import com.steamedfish.spring.aspect.p6sql.demo.model.CoffeeOrder;
import com.steamedfish.spring.aspect.p6sql.demo.model.OrderState;
import com.steamedfish.spring.aspect.p6sql.demo.repository.CoffeeRepository;
import com.steamedfish.spring.aspect.p6sql.demo.service.CoffeeOrderService;
import com.steamedfish.spring.aspect.p6sql.demo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Slf4j
public class AspectP6sqlDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(AspectP6sqlDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("All Coffee: {}", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = orderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
        }
    }
}
