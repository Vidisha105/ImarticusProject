package com.citi.util;

import java.util.ArrayList;
import java.util.List;

import com.citi.json.OrderJson;
import com.citi.model.*;

public class OrderUtil {

	public static OrderJson convertOrderIntoOrderJson(Order order) {
		return new OrderJson(order.getId(),order.getBuyOrSell(), order.getOrderTime(), order.getQuantity(), order.getOrderType(),order.getPrice(),order.getOrderStatus(),order.getAllOrNone(), order.getMinFill());
	}
	
	public static List<OrderJson> convertOrderListIntoOrderJsonList(List<Order> orders) {
		List<OrderJson> orderjsons = new ArrayList<OrderJson>();
		for(Order order: orders) {
			orderjsons.add(convertOrderIntoOrderJson(order));
		}
		
		return orderjsons;
	}
	
	
}