package com.abstraction.persistence;

import com.abstraction.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoDAO extends JpaRepository<Producto, Long> {
}
