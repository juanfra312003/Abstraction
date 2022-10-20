package com.abstraction.persistence;

import com.abstraction.entities.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface IPedidoDAO extends CrudRepository<Pedido, Long> {
}
