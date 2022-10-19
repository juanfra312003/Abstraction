package com.abstraction.persistence;

import com.abstraction.entities.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDAO extends CrudRepository<Factura, Long> {
}
