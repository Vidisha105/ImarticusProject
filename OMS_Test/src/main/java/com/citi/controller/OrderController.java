package com.citi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.citi.json.OrderJson;
import com.citi.model.*;
import com.citi.repo.OrderRepository;
import com.citi.service.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	static Logger logger = LogManager.getLogger(OrderController.class.getName());
	
	@GetMapping(value = "/order", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OrderJson> getAllOrders() {

		return orderService.getAllOrders();
	}
	
	@PostMapping(value="/order", consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public OrderJson sendOrder(@RequestBody Order order) {
		logger.error("to service layer");
		return orderService.sendOrder(order);
	}
	
}