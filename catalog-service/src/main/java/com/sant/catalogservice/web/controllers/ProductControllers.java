package com.sant.catalogservice.web.controllers;

import com.sant.catalogservice.domain.PageResult;
import com.sant.catalogservice.domain.Product;
import com.sant.catalogservice.domain.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
 class ProductControllers {
    @Autowired
private  ProductService productService;

    @GetMapping
    PageResult<Product> getProducts(@RequestParam(name = "page",defaultValue = "1")int pageNo){
        return  productService.getProducts(pageNo);
    }




}
