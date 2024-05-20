package com.sant.catalogservice.web;

import com.sant.catalogservice.domain.PageResult;
import com.sant.catalogservice.domain.Product;
import com.sant.catalogservice.domain.ProductNotFoundException;
import com.sant.catalogservice.domain.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductControllers {

    private static final Logger log = LoggerFactory.getLogger(ProductControllers.class);

    @Autowired
    private ProductService productService;

    @GetMapping
    PageResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
