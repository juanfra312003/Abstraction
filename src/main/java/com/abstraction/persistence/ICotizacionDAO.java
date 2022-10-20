package com.abstraction.persistence;

import com.abstraction.entities.Cotizacion;
import org.springframework.data.repository.CrudRepository;

public interface ICotizacionDAO extends CrudRepository<Cotizacion, Long> {
}
