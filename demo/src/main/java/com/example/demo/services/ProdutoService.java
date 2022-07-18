package com.example.demo.services;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.dto.ProdutoFormDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoService {


    ProdutoDTO salvar(ProdutoFormDTO body);

    List<ProdutoDTO> listar(String category, Float preco, String description);

    ProdutoDTO procurar(String id);

    ProdutoDTO atualizar(String id, ProdutoFormDTO body);

    void remover(String id);

}
