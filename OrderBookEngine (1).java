package src;

import java.util.*;

public class OrderBookEngine {

		private Map<Double, Integer> bidOffers = new TreeMap<>();
		private Map<Double, Integer> askOffers = new TreeMap<>();


	
	public void receiveOrder(double price, int quantity, boolean buy) {
		if (buy) {
			// BUY
			Set<Double> ask_prices = askOffers.keySet();
			List<Double> ask_prices_list = new ArrayList<>(ask_prices);
			for (double ask_price : ask_prices_list) {
				if (quantity > 0 && price >= ask_price) {
					int ask_quantity = askOffers.get(ask_price);
					if (quantity >= ask_quantity) {
						quantity = quantity - ask_quantity;
						/*Pair<Double, Integer> pair = new Pair<Double, Integer>(
								ask_price, ask_quantity);
						executedOrders.add(pair);*/
						
					} else {
						// quantity < ask_quantity
						/*Pair<Double, Integer> pair = new Pair<Double, Integer>(
								ask_price, quantity);
						executedOrders.add(pair);*/
						
						quantity = 0;
					}
				}
			}
			

		} else {
			Set<Double> bid_prices = bidOffers.keySet();
			List<Double> bid_prices_list = new ArrayList<>(bid_prices);
			for (double bid_price : bid_prices_list) {
				if (quantity > 0 && price <= bid_price) {
					int bid_quantity = bidOffers.get(bid_price);
					if (quantity >= bid_quantity) {
						quantity = quantity - bid_quantity;
						/*Pair<Double, Integer> pair = new Pair<Double, Integer>(
								bid_price, bid_quantity);
						executedOrders.add(pair);*/
						
					} else {
						// quantity < bid_quantity
						/*Pair<Double, Integer> pair = new Pair<Double, Integer>(
								bid_price, quantity);
						executedOrders.add(pair);*/
						
						quantity = 0;
					}
				}

			}

			
		}
	}


}
