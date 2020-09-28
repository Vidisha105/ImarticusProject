package com.citi.oms.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.oms.model.Order;
import com.citi.oms.repository.OrderRepository;

@RestController
@RequestMapping("/")
public class OrderController {

	@Autowired
	OrderRepository repository;
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		
		System.out.println("In get mapping ... ");
		List<Order> orders = new ArrayList<>();
		System.out.println("Printing orders : ");
		for(Order o : orders)
		{
			System.out.println("Id : "+o.getId());
			System.out.println("Type : "+o.getOrderType());
		}
		try {
			repository.findAll().forEach(orders::add);
			
			if (orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

