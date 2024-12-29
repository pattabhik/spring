package com.pattabhi.rest_api;

import com.pattabhi.rest_api.product.entity.Product;
import com.pattabhi.rest_api.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        /*productRepository.deleteAll();
        productRepository.save(new Product(1, "Product A", 100.0));
        productRepository.save(new Product(2, "Product B", 200.0));*/
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void testGetProducts() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity(getBaseUrl() + "/products", Product[].class);

        List<Product> products = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, products.size());
    }

    @Test
    void testGetProductById() {
        ResponseEntity<Product> response = restTemplate.getForEntity(getBaseUrl() + "/product/1", Product.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product A", response.getBody().getName());
    }

    /*@Test
    void testSaveProduct() {
        Product newProduct = new Product(3, "Product C", 300.0);

        ResponseEntity<Product> response = restTemplate.postForEntity(getBaseUrl() + "/product", newProduct, Product.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product C", response.getBody().getName());
    }*/

    /*@Test
    void testUpdateProduct() {
        Product updatedProduct = new Product(1, "Updated Product A", 150.0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> request = new HttpEntity<>(updatedProduct, headers);

        ResponseEntity<Product> response = restTemplate.exchange(getBaseUrl() + "/product", HttpMethod.PUT, request, Product.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Product A", response.getBody().getName());
    }*/

    @Test
    void testDeleteProduct() {
        ResponseEntity<Void> response = restTemplate.exchange(getBaseUrl() + "/product/1", HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, productRepository.findAll().size()); // Ensure one less product in the repository
    }
}