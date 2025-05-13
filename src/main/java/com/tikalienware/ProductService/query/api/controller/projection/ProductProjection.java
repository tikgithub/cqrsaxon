package com.tikalienware.ProductService.query.api.controller.projection;

import com.tikalienware.ProductService.command.api.data.Product;
import com.tikalienware.ProductService.command.api.data.ProductRepository;
import com.tikalienware.ProductService.command.api.model.ProductRestModel;
import com.tikalienware.ProductService.query.api.queries.GetProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private final ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @QueryHandler
    public List<ProductRestModel> handle(GetProductQuery getProductQuery){
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> ProductRestModel
                .builder()
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .name(product.getName())
                .build())
                .toList();
    }
}
