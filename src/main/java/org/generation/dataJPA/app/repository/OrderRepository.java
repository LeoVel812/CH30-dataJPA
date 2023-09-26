package org.generation.dataJPA.app.repository;

import org.generation.dataJPA.app.entity.OrderProduct;
import org.springframework.data.repository.CrudRepository;



public interface OrderRepository extends CrudRepository<OrderProduct, Long> {

}
