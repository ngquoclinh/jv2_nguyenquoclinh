package com.myweb.bai6.models;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Product {
    @Size(min = 5, max = 50, message = "Product ID must be betweeb 5 and 50 characters")
    private String productID;

    @Size(min = 50 , message = "Name must be at least 50 characters")
    private String name;

    @DecimalMin(value = "0.1", message = "Price must be >= 0.1")
    @DecimalMax(value = "100", message = "Price must be <= 100>")
    private Double price;

    @NotBlank(message = "Description not blank")
    private String description;

    @Size(min = 1, message = "Must have at least 1 tag")
    private List<String> tags;
}