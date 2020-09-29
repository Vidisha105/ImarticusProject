package com.citi.json;

import java.sql.Date;

public class OrderJson {
	
	private Long id;
	private String buyOrSell;
	private Date orderTime;
	private Long quantity;
	private String orderType;
	private Long price;
	private String orderStatus;
	private Long allorNone;
	private Long minFill;
	
	public OrderJson() {
		super();
	}

	public OrderJson(Long id, String buyOrSell, Date orderTime, Long quantity, String orderType, Long price,
			String orderStatus, Long allorNone, Long minFill) {
		super();
		this.id = id;
		this.buyOrSell = buyOrSell;
		this.orderTime = orderTime;
		this.quantity = quantity;
		this.orderType = orderType;
		this.price = price;
		this.orderStatus = orderStatus;
		this.allorNone = allorNone;
		this.minFill = minFill;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getAllorNone() {
		return allorNone;
	}

	public void setAllorNone(Long allorNone) {
		this.allorNone = allorNone;
	}

	public Long getMinFill() {
		return minFill;
	}

	public void setMinFill(Long minFill) {
		this.minFill = minFill;
	}

	@Override
	public String toString() {
		return "OrderJson [id=" + id + ", buyOrSell=" + buyOrSell + ", orderTime=" + orderTime + ", quantity="
				+ quantity + ", orderType=" + orderType + ", price=" + price + ", orderStatus=" + orderStatus
				+ ", allorNone=" + allorNone + ", minFill=" + minFill + "]";
	}
	
	
	
	

}
