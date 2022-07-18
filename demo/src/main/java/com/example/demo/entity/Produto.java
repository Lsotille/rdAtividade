package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Produto {
    @Id
    private String id;

    private String category;

    private Float price;

    private String description;


}