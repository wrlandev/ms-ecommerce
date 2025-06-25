package com.msecommerce.produtoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProdutoMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoMsApplication.class, args);
    }

}