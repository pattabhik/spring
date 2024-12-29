package com.pattabhi.rest_api.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pattabhi.rest_api.product.entity.Product;
import com.pattabhi.rest_api.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductRepository productRepository;

    @Test
    void getProducts() throws Exception {
        Product product = getProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        String productsJson = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(products);
        when(productRepository.findAll()).thenReturn(products);
        mockMvc.perform(get("/apis/products").contextPath("/apis"))
                .andExpect(status().isOk()).andExpect(content().json(productsJson));
    }

    private static Product getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Product A");
        product.setDescription("Product A Description");
        product.setPrice(100.0);
        return product;
    }

    @Test
    void getProductById() throws Exception {
        when(productRepository.findById(anyInt())).thenReturn(java.util.Optional.of(getProduct()));
        String productJson = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(getProduct());
        mockMvc.perform(get("/apis/product/1").contextPath("/apis")).andExpect(status().isOk()).
                andExpect(content().json(productJson));
    }

    @Test
    void updateProduct() throws Exception {
        when(productRepository.save(any(Product.class))).thenReturn(getProduct());
        String productJson = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(getProduct());
        mockMvc.perform(put("/apis/product").contextPath("/apis").contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isOk()).andExpect(content().json(productJson));
    }

    @Test
    void saveProduct() throws Exception {
        when(productRepository.save(any(Product.class))).thenReturn(getProduct());
        String productJson = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(getProduct());
        mockMvc.perform(post("/apis/product").contextPath("/apis").contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isOk()).andExpect(content().json(productJson));
    }

    @Test
    void deleteProduct() throws Exception {
        doNothing().when(productRepository).deleteById(anyInt());
        mockMvc.perform(delete("/apis/product/").contextPath("/apis")).andExpect(status().isNotFound());
    }
}