package com.example.demo.repository;

import com.example.demo.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findByCargo(String cargo);
    List<Produto> findByPrice(Float price);
    List<Produto> findByDescription(String description);
}
