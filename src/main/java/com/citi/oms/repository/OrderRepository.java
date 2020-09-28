package com.citi.oms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citi.oms.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	List<Order> findById(int id);
}

