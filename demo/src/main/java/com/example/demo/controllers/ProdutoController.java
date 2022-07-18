package com.example.demo.controllers;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.dto.ProdutoFormDTO;
import com.example.demo.services.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associado")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoFormDTO body) {
        ProdutoDTO state = this.service.salvar(body);
        return ResponseEntity.ok(state);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar(@RequestParam(value = "category", required = false) String category,
                                                   @RequestParam(value = "price",required = false)Float price,
                                                   @RequestParam(value = "description", required = false)String description){
        return ResponseEntity.ok(this.service.listar(category, price, description));
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<ProdutoDTO> procurar (@RequestParam(value = "id", required = true) String id){
        return ResponseEntity.ok(this.service.procurar(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProdutoDTO> atualizar (@RequestParam(value = "id", required = true) String id, @RequestBody ProdutoFormDTO body){
        return ResponseEntity.ok(this.service.atualizar(id,body));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProdutoDTO> remover (@RequestParam(value = "id", required = true) String id){
        this.service.remover(id);
        return ResponseEntity.ok().build();
    }

}