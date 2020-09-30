package com.citi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.citi.json.*;
public class OrderBusinessLogic{

	List<OrderJson> orders;
	List<TradeJson> tradedOrders; 
	
	static Logger logger1 = LogManager.getLogger(OrderBusinessLogic.class.getName());
	public OrderBusinessLogic()
	{
		logger1.info("In ctor of OrderBusinessLogic");
	}
	// Function to perform actual order matching
	public void startMatching(List<OrderJson> ord)
	{
		List<OrderJson> buyList = new ArrayList<OrderJson>();       // List to store buy orders
		List<OrderJson> sellList = new ArrayList<OrderJson>();      // List to store sell orders
		tradedOrders = new ArrayList<TradeJson>();
		logger1.info("In start Matching function");
		orders = ord;												// Orders fetched from the database
		
		// STEP 1 : Running a loop to segregate the fetched list into buyList and sellList lists
		for(OrderJson o : orders)
		{
			if(o.getBuyOrSell().equalsIgnoreCase("BUY"))
				buyList.add(o);
			else
				sellList.add(o);
		}
		
		System.out.println("Buy List :");
		for(OrderJson buy : buyList)
		{
			System.out.println(buy.toString());
		}
		
		System.out.println("\nSell List : ");
		for(OrderJson sell : sellList)
		{
			System.out.println(sell.toString());
		}		
		
		
		// STEP 2 : Sorting both buyList and sellList on the basis of time
		Comparator<OrderJson> compareByTime = (OrderJson o1, OrderJson o2) -> o1.getOrderTime().compareTo(o2.getOrderTime());
		 
		Collections.sort(buyList, compareByTime);
		Collections.sort(sellList,compareByTime);
		
		
		List<OrderJson> lessSellTime = new ArrayList<OrderJson>();
		List<OrderJson> lessBuyTime = new ArrayList<OrderJson>();
		
		// STEP 3 : Comparing each buy order with every sell order for order matching
		for(OrderJson buy : buyList) {
			if(buy.getOrderType().equalsIgnoreCase("LIMIT"))
			{
				Date buyDate = buy.getOrderTime();
				long buyTime = buyDate.getTime();
				
				for(OrderJson sell : sellList)
				{
					Date sellDate = sell.getOrderTime();     
					long sellTime = sellDate.getTime();
					
					
					// Checking if one buy order has been placed before any sell order ... by comparing orderTime
					if(sellTime <= buyTime)
					{
						// If sell order is placed before buy order then comparing the prices of buy and sell order
						if(buy.getPrice() >= sell.getPrice() && sell.getOrderStatus().equalsIgnoreCase("PENDING") && buy.getOrderStatus().equalsIgnoreCase("PENDING") )
						{
							lessSellTime.add(sell);    // adding all the sell orders whose prices are less than buy order
						}
					}				
				}
				if(!lessSellTime.isEmpty())    
				{
					// Sorting the sell orders that have less price than the buy order on the basis of price
					Comparator<OrderJson> compareByPrice = (OrderJson o1, OrderJson o2) -> o1.getPrice().compareTo(o2.getPrice());
					Collections.sort(lessSellTime,compareByPrice);
					
					long buyQty = buy.getQuantity();   
					 
					for(OrderJson lst : lessSellTime) {
						long sellQty = lst.getQuantity();  
						buyQty = buy.getQuantity();    
						if(buyQty == sellQty)
						{
							buy.setOrderStatus("EXECUTED");
							lst.setOrderStatus("EXECUTED");
							
							tradedOrders.add(new TradeJson(buy.getOrderTime(),buyQty,lst.getPrice(),buy.getId(),lst.getId()) );
							
							buy.setQuantity(0L);
							lst.setQuantity(0L);
							
						}
						else if(buyQty < sellQty)
						{
							buy.setOrderStatus("EXECUTED");
							tradedOrders.add(new TradeJson(buy.getOrderTime(),buyQty,lst.getPrice(),buy.getId(),lst.getId()));
							
							lst.setQuantity(sellQty-buyQty);
							buy.setQuantity(0L);
						}
						else    // Sell Qty is less
						{
							tradedOrders.add(new TradeJson(buy.getOrderTime(),lst.getQuantity(),lst.getPrice(),buy.getId(),lst.getId()));
							lst.setOrderStatus("EXECUTED");
							
							buy.setQuantity(buyQty-lst.getQuantity());
							lst.setQuantity(0L);
						}
					}
					
				lessSellTime.clear();
			   }
			}
			else if(buy.getOrderType().equalsIgnoreCase("MARKET") )
			{
				Date buyDate = buy.getOrderTime();
				long buyTime = buyDate.getTime();
				List<OrderJson> lessTime = new ArrayList<OrderJson>();
				for(OrderJson sell:sellList)    // 3 ====> 1,2
				{
					Date sellDate = sell.getOrderTime();     
					long sellTime = sellDate.getTime();
					if(sellTime <= buyTime && !sell.getOrderType().equalsIgnoreCase("MARKET") )
					{
						lessTime.add(sell);
					}
				}
				if(!lessTime.isEmpty())
				{
				Comparator<OrderJson> compareByPrice = (OrderJson o1, OrderJson o2) -> o1.getPrice().compareTo(o2.getPrice());
				Collections.sort(lessTime,compareByPrice);   //1,2
				long bQty;
				
				for(OrderJson ltime : lessTime) {
					long sellQty = ltime.getQuantity();  
					bQty = buy.getQuantity(); 
					if(buy.getOrderStatus().equalsIgnoreCase("PENDING") && ltime.getOrderStatus().equalsIgnoreCase("PENDING"))
					{
					if(bQty == sellQty)
					{
						buy.setOrderStatus("EXECUTED");
						ltime.setOrderStatus("EXECUTED");
						
						tradedOrders.add(new TradeJson(buy.getOrderTime(),bQty,ltime.getPrice(),buy.getId(),ltime.getId()) );
						
						buy.setQuantity(0L);
						ltime.setQuantity(0L);
						
					}
					else if(bQty < sellQty)
					{
						buy.setOrderStatus("EXECUTED");
						tradedOrders.add(new TradeJson(buy.getOrderTime(),bQty,ltime.getPrice(),buy.getId(),ltime.getId()));
						
						ltime.setQuantity(sellQty-bQty);
						buy.setQuantity(0L);
					}
					else    // Sell Qty is more
					{
						tradedOrders.add(new TradeJson(buy.getOrderTime(),ltime.getQuantity(),ltime.getPrice(),buy.getId(),ltime.getId()));
						ltime.setOrderStatus("EXECUTED");
						
						buy.setQuantity(bQty-ltime.getQuantity());
						ltime.setQuantity(0L);
						buy.setOrderStatus("REJECTED");
					}
					}
				}
				}
				else
				{
					buy.setOrderStatus("REJECTED");
				}
				lessTime.clear();
				
			}
		
		
	}


		
		
		for(OrderJson sell : sellList) {
			if(sell.getOrderType().equalsIgnoreCase("LIMIT"))
			{
				Date sellDate = sell.getOrderTime();
				long sellTime = sellDate.getTime();
				
				for(OrderJson buy : buyList)
				{
					Date buyDate = buy.getOrderTime();     
					long buyTime = buyDate.getTime();
					
					
					// Checking if one buy order has been placed before any sell order ... by comparing orderTime
					if(buyTime <= sellTime)
					{
						// If sell order is placed before buy order then comparing the prices of buy and sell order
						if(sell.getPrice() <= buy.getPrice() && sell.getOrderStatus().equalsIgnoreCase("PENDING") && buy.getOrderStatus().equalsIgnoreCase("PENDING") )
						{
							lessBuyTime.add(buy);    // adding all the sell orders whose prices are less than buy order
						}
					}				
				}
				if(!lessBuyTime.isEmpty())    
				{
					// Sorting the sell orders that have less price than the buy order on the basis of price
					Comparator<OrderJson> compareByPrice = (OrderJson o1, OrderJson o2) -> o1.getPrice().compareTo(o2.getPrice());
					compareByPrice = Collections.reverseOrder();
					Collections.sort(lessBuyTime,compareByPrice); 
					
					long sellQty = sell.getQuantity();   
					 
					for(OrderJson lbt : lessBuyTime) { 
						long buyQty = lbt.getQuantity();  
						sellQty = sell.getQuantity();    
						if(buyQty == sellQty)
						{
							sell.setOrderStatus("EXECUTED");
							lbt.setOrderStatus("EXECUTED");
							
							tradedOrders.add(new TradeJson(sell.getOrderTime(),buyQty,lbt.getPrice(),lbt.getId(),sell.getId()) );
							System.out.println("Trade executed for equal qty between : "+lbt.getId() + "and" + sell.getId());
							lbt.setQuantity(0L);
							sell.setQuantity(0L);
							
						}
						else if(buyQty < sellQty)
						{
							lbt.setOrderStatus("EXECUTED");
							tradedOrders.add(new TradeJson(sell.getOrderTime(),buyQty,lbt.getPrice(),lbt.getId(),sell.getId()));
							System.out.println("Trade executed for less buy qty between : "+lbt.getId() + "and" + sell.getId());
							sell.setQuantity(sellQty-buyQty);
							lbt.setQuantity(0L);
							
						}
						else    
						{
							tradedOrders.add(new TradeJson(sell.getOrderTime(),sell.getQuantity(),lbt.getPrice(),lbt.getId(),sell.getId()));
							sell.setOrderStatus("EXECUTED");
							System.out.println("Trade executed for less sell qty between : "+lbt.getId() + "and" + sell.getId());
							lbt.setQuantity(buyQty-lbt.getQuantity());
							sell.setQuantity(0L);
						}
					}
					
				lessBuyTime.clear();
			}
		}
			else if(sell.getOrderType().equalsIgnoreCase("MARKET"))
			{
				Date sDate = sell.getOrderTime();
				long sTime = sDate.getTime();
				List<OrderJson> lessBTime = new ArrayList<OrderJson>();
				
				for(OrderJson b : buyList)
				{
					Date bDate = b.getOrderTime();     
					long bTime = bDate.getTime();
					// Checking if one buy order has been placed before any sell order ... by comparing orderTime
					if(bTime <= sTime)
					{
						// If sell order is placed before buy order then comparing the prices of buy and sell order
						if(sell.getPrice() <= b.getPrice() && sell.getOrderStatus().equalsIgnoreCase("PENDING") && b.getOrderStatus().equalsIgnoreCase("PENDING") )
						{
							lessBTime.add(b);    // adding all the sell orders whose prices are less than buy order
						}
					}		
				}
				if(!lessBTime.isEmpty())    
				{
					// Sorting the sell orders that have less price than the buy order on the basis of price
					Comparator<OrderJson> compareByPrice = (OrderJson o1, OrderJson o2) -> o1.getPrice().compareTo(o2.getPrice());
					compareByPrice = Collections.reverseOrder();
					Collections.sort(lessBTime,compareByPrice); 
					
					long sellQty = sell.getQuantity();   
					 
					for(OrderJson lbt : lessBTime) { 
						long buyQty = lbt.getQuantity();  
						sellQty = sell.getQuantity();  
						if(lbt.getOrderStatus().equalsIgnoreCase("PENDING") && sell.getOrderStatus().equalsIgnoreCase("PENDING"))
						{
						if(buyQty == sellQty)
						{
							sell.setOrderStatus("EXECUTED");
							lbt.setOrderStatus("EXECUTED");
							
							tradedOrders.add(new TradeJson(sell.getOrderTime(),buyQty,lbt.getPrice(),lbt.getId(),sell.getId()) );
							System.out.println("Trade executed for equal qty between : "+lbt.getId() + "and" + sell.getId());
							lbt.setQuantity(0L);
							sell.setQuantity(0L);
							
						}
						else if(buyQty < sellQty)
						{
							lbt.setOrderStatus("EXECUTED");
							tradedOrders.add(new TradeJson(sell.getOrderTime(),buyQty,lbt.getPrice(),lbt.getId(),sell.getId()));
							System.out.println("Trade executed for less buy qty between : "+lbt.getId() + "and" + sell.getId());
							sell.setQuantity(sellQty-buyQty);
							lbt.setQuantity(0L);
							sell.setOrderStatus("REJECTED");
							
						}
						else    
						{
							tradedOrders.add(new TradeJson(sell.getOrderTime(),sell.getQuantity(),lbt.getPrice(),lbt.getId(),sell.getId()));
							sell.setOrderStatus("EXECUTED");
							System.out.println("Trade executed for less sell qty between : "+lbt.getId() + "and" + sell.getId());
							lbt.setQuantity(buyQty-lbt.getQuantity());
							sell.setQuantity(0L);
						}
						}
					}
					
				lessBTime.clear();
			}
				else
				{
					sell.setOrderStatus("REJECTED");
				}
				
			}		
			
	}
		System.out.println(" **** TRADE EXECUTED **** ");
		for(TradeJson trade : tradedOrders)
		{
			System.out.println(trade.toString());
		}
		
		System.out.println("Buy List :");
		for(OrderJson buy : buyList)
		{
			System.out.println(buy.toString());
		}
		
		System.out.println("\nSell List : ");
		for(OrderJson sell : sellList)
		{
			System.out.println(sell.toString());
		}

	}

}
