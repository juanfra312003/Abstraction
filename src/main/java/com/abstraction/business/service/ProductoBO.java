package com.abstraction.business.service;

import com.abstraction.entities.Producto;
import com.abstraction.persistence.IProductoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class ProductoBO {

    private final IProductoDAO repo;

    @Autowired
    public ProductoBO(IProductoDAO repo) {
        Assert.notNull(repo, "This must not be null!");
        this.repo = repo;
    }


    public ArrayList<Producto> listarProductos() {
        return (ArrayList<Producto>) repo.findAll();
    }

    public void save(Producto producto) {
        repo.save(producto);
    }
}
