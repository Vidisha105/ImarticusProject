package com.citi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

 
import com.citi.model.*;


public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
