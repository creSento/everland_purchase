package purchase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kopo19
 * Single Order Object
 */
public class Customer {
	private String date;
	private int ticketType;
	private int age;
	private int orderCount;
	private int price;
	private int discountType;

	/**
	 * Initialize object
	 * @param ticketType
	 * @param age
	 * @param orderCount
	 * @param price
	 * @param discountType
	 */
	public Customer(int ticketType, int age, int orderCount, int price, int discountType) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String orderDate = sdf.format(today);
		this.date = orderDate;
		this.ticketType = ticketType;
		this.age = TicketClass.ageGroup(age);
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
	
	/**
	 * Exchange Integer data to String
	 * @return String ticket type
	 */
	public String getTicketTypeStr() {
		String ticketStr = "";
		switch (ticketType) {
		case Cons.DAY:
			ticketStr = "주간권";
			break;
		case Cons.NIGHT:
			ticketStr = "야간권";
			break;
		default:
			break;
		}
		return ticketStr;
	}

	public int getAge() {
		return age;
	}
	
	/**
	 * Exchange Integer data to String
	 * @return String age group
	 */
	public String getAgeStr() {
		String ageGroup = "";
		switch (age) {
		case Cons.BABY:
			ageGroup = "유아";
			break;
		case Cons.CHILD:
			ageGroup = "소인";
			break;
		case Cons.TEEN:
			ageGroup = "청소년";
			break;
		case Cons.ADULT:
			ageGroup = "어른";
			break;
		case Cons.OLD:
			ageGroup = "노인";
			break;
		default:
			break;
		}
		return ageGroup;
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

	/**
	 * Exchange Integer data to String
	 * @return String discount type
	 */
	public String getDiscountTypeStr() {
		String discountStr = "";
		switch (discountType) {
		case Cons.DISCOUNT_NONE:
			discountStr = "우대적용 없음";
			break;
		case Cons.DISCOUNT_DISABLE:
			discountStr = "장애인 우대적용";
			break;
		case Cons.DISCOUNT_MERIT:
			discountStr = "국가유공자 우대적용";
			break;
		case Cons.DISCOUNT_MULTICHILD:
			discountStr = "다자녀 우대적용";
			break;
		case Cons.DISCOUNT_PREGNANT:
			discountStr = "임산부 우대적용";
			break;
		default:
			break;
		}
		return discountStr;
	}

}
