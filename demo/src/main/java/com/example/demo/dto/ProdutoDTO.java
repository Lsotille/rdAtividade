package com.example.demo.dto;

import lombok.Data;

@Data
public class ProdutoDTO {

    private Long id;

    private String category;

    private Float price;

    private String description;
}
