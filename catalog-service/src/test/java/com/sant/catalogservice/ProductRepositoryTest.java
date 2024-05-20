package com.sant.catalogservice;

import com.sant.catalogservice.domain.ProductEntity;
import com.sant.catalogservice.domain.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest(
        properties ={ "spring.test.database.replace=none",

                "spring.datasource.url=jdbc:tc:postgresql:16.1:///db",
        }
)
//@Import(ContainerConfig.class)
@Sql("/test-data.sql")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

@Test
public void shouldGetAllProduct() {
    List<ProductEntity> products = repository.findAll();
    assertThat(products).hasSize(15);
}
    @Test
    void shouldGetProductByCode() {
        ProductEntity product = repository.findByCode("P100").orElseThrow();
        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getName()).isEqualTo("The Hunger Games");
        assertThat(product.getDescription()).isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        assertThat(repository.findByCode("invalid_product_code")).isEmpty();
    }
}
