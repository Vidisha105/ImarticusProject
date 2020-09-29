package com.citi.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.citi.util.OrderUtil;
import com.citi.controller.OrderController;
import com.citi.json.OrderJson;
import com.citi.model.Order;
import com.citi.repo.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	static Logger logger1 = LogManager.getLogger(OrderServiceImpl.class.getName());

	@Override
	public List<OrderJson> getAllOrders() {

		List<Order> orders = orderRepository.findAll();
		for( Order o : orders ) {
			o.getId();
			o.getOrderType();
		}
		return OrderUtil.convertOrderListIntoOrderJsonList(orders);
	}

	@Override
	public OrderJson sendOrder(Order order) {
		Order orders = orderRepository.save(order);
		logger1.error("to repository");
		return OrderUtil.convertOrderIntoOrderJson(orders);
	}


	  



}
