package com.citi.oms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="order_time")
	private Date orderTime;
	
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="order_type", length = 10)
	private String orderType;
	
	@Column(name="price")
	private Long price;
	
	@Column(name="order_status", length = 10)
	private String orderStatus;
	
//	@Column(name="allOrNone",nullable = true)
//	private Long allOrNone;
	
	@Column(name="min_fill")
	private Long minFill;
	
	public Order() {
	}

	public long getId() {
		return id;
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

	public Long getMinFill() {
		return minFill;
	}

	public void setMinFill(Long minFill) {
		this.minFill = minFill;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", quantity=" + quantity + ", orderType=" + orderType
				+ ", price=" + price + ", orderStatus=" + orderStatus + ", minFill=" + minFill + "]";
	}
}
