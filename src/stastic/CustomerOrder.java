package stastic;

public class CustomerOrder {
	private String date;
	private int ticketType;
	private int age;
	private int orderCount;
	private int price;
	private int discountType;
	
	public CustomerOrder(String date, int ticketType, int age, int orderCount, int price, int discountType) {
		this.date = date;
		this.ticketType = ticketType;
		this.age = age;
		this.orderCount = orderCount;
		this.price = price;
		this.discountType = discountType;
	}

	public String getDate() {
		return date;
	}

	public int getTicketType() {
		return ticketType;
	}

	public int getAge() {
		return age;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public int getPrice() {
		return price;
	}

	public int getDiscountType() {
		return discountType;
	}
	
	
}
