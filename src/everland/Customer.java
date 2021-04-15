package everland;

public class Customer {
	private String date;
	private int ticketType;
	private int age;
	private int orderCount;
	private int price;
	private int discountType;
	
	public Customer(String date, int ticketType, int age, int orderCount, int price, int discountType) {
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

	public String getTicketType() {
		String tType = "";
		if (ticketType == 1) {
			tType = "주간권";
		} else {
			tType = "야간권";
		}
		return tType;
	}

	public String getAge() {
		String ageRange = "";
		if (age == 1) {
			ageRange = "유아";
		} else if (age == 2) {
			ageRange = "소인";
		} else if (age == 3) {
			ageRange = "청소년";
		} else if (age == 4) {
			ageRange = "어른";
		} else {
			ageRange = "노인";
		}
		return ageRange;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public int getPrice() {
		return price;
	}

	public String getDiscountType() {
		String dType = "";
		if (discountType == 1) {
			dType = "없음";
		} else if (discountType == 2) {
			dType = "장애인";
		} else if (discountType == 3) {
			dType = "국가유공자";
		} else if (discountType == 4) {
			dType = "다자녀";
		} else {
			dType = "임산부";
		}
		return dType;
	}
}
