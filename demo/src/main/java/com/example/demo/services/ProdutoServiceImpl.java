package com.example.demo.services;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.dto.ProdutoFormDTO;
import com.example.demo.entity.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProdutoDTO salvar(ProdutoFormDTO body) {
        Produto produto = mapper.map(body, Produto.class);
        Produto produtoResponse = this.repository.save(produto);
        return mapper.map(produtoResponse, ProdutoDTO.class);
    }


    @Override
    public List<ProdutoDTO> listar(String category, Float preco, String description) {
        List<ProdutoDTO> produtoDTOList;
        if (category != null){
            produtoDTOList = this.repository.findByCargo(category).stream().map(st -> mapper.map(st, ProdutoDTO.class)).collect(Collectors.toList());
        }else if (preco != null) {
            produtoDTOList = this.repository.findByPrice(preco).stream().map(st -> mapper.map(st, ProdutoDTO.class)).collect(Collectors.toList());
        }else if (description != null) {
            produtoDTOList = this.repository.findByDescription(description).stream().map(st -> mapper.map(st, ProdutoDTO.class)).collect(Collectors.toList());
        }else
            produtoDTOList = this.repository.findAll().stream().map(st -> mapper.map(st, ProdutoDTO.class)).collect(Collectors.toList());
        return produtoDTOList;
    }


    @Override
    public ProdutoDTO procurar(String id) {
        Optional<Produto> state = this.repository.findById(id);
        if (state.isPresent() == true) {
            return mapper.map(state.get(), ProdutoDTO.class);
        }
        throw new RuntimeException("Associado não encontrado");
    }

    @Override
    public ProdutoDTO atualizar(String id, ProdutoFormDTO body) {
        Optional<Produto> produto = this.repository.findById(id);
        if (produto.isPresent() == true) {
            if (body.getCategory().isEmpty() != true)
            produto.get().setCategory(body.getCategory());
            if (body.getPrice().isNaN() != true)
            produto.get().setPrice(body.getPrice());
            if (body.getDescription().isEmpty() != true)
            produto.get().setDescription(body.getDescription());
            Produto st = this.repository.save(produto.get());
            return mapper.map(st, ProdutoDTO.class);
        }
        throw new RuntimeException("Associado não encontrado");
    }

    @Override
    public void remover(String id) {
        Produto produto = this.repository.findById(id).get();
        this.repository.delete(produto);
    }

}
