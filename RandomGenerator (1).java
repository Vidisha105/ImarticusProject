package src;




public class RandomGenerator {
	
	private Long id;
	private String buyOrSell;
	private String orderTime;
	private Long quantity;
	private String orderType;
	private Long price;
	private String orderStatus;
	private String allorNone;
	private String minFill;
	
	public RandomGenerator() {
		super();
	}

	public RandomGenerator(Long id, String buyOrSell, String orderTime, Long quantity, String orderType, Long price,
			String orderStatus, String allorNone, String minFill) {
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
	
	/*ArrayList<Integer> list10 = new ArrayList<Integer>();
	ArrayList<Integer> list11 = new ArrayList<Integer>();
	ArrayList<Integer> list12 = new ArrayList<Integer>();
	ArrayList<Integer> list13 = new ArrayList<Integer>();
	ArrayList<Integer> list14 = new ArrayList<Integer>();
	ArrayList<Integer> list15 = new ArrayList<Integer>();
	*/
	

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

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String string) {
		this.orderTime = string;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(double d) {
		this.quantity = (long) d;
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

	public void setPrice(double d) {
		this.price = (long) d;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAllorNone() {
		return allorNone;
	}

	public void setAllorNone(String string) {
		this.allorNone = string;
	}

	public String getMinFill() {
		return minFill;
	}

	public void setMinFill(String string) {
		this.minFill = string;
	}

	@Override
	public String toString() {
		return "buyOrSell=" + buyOrSell + ", orderTime=" + orderTime + ", quantity="
				+ quantity + ", orderType=" + orderType + ", price=" + price + ", orderStatus=" + orderStatus
				+ ", allorNone=" + allorNone + ", minFill=" + minFill;
	}
	
	
	
	

}