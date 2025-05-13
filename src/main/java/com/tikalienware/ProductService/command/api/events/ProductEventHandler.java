package com.tikalienware.ProductService.command.api.events;

import com.tikalienware.ProductService.command.api.data.Product;
import com.tikalienware.ProductService.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @EventHandler
    public void on(ProductCreatedEvent event){
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
    }
}
