package purchase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author kopo19
 * Calculate variables that required in process
 */
public class TicketClass {
	private InputClass data;
	private int age;
	private int rawPrice;
	private int disPrice;
	private int resultPrice;
	
	public TicketClass(InputClass data) {
		this.data = data;
		this.rawPrice = 0;
		this.disPrice = 0;
		this.resultPrice = 0;
	}

	public int getAge() {
		return age;
	}

	public int getResultPrice() {
		return resultPrice;
	}
	
	/**
	 * Run process method
	 */
	public void runTicket() {
		age = customerAge(data.getIdNumber());
		rawPrice = calRawPrice(age, data.getTicket());
		disPrice = calDiscount(rawPrice, data.getDiscountType());
		resultPrice = calTotalPrice(disPrice, data.getOrderCount());
	}
	
	/**
	 * Check customer's age
	 * @return age
	 */
	public int customerAge(String idNumber) {
		int age = 0;
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int thisYear = Integer.parseInt(sdf.format(today).substring(0, 4));
		int thisMonth = Integer.parseInt(sdf.format(today).substring(4, 6));
		int thisDay = Integer.parseInt(sdf.format(today).substring(6, 8));
		int cusYear = Integer.parseInt(idNumber.substring(0, 2));
		int cusMonth = Integer.parseInt(idNumber.substring(2, 4));
		int cusDay = Integer.parseInt(idNumber.substring(4, 6));
		if (idNumber.charAt(6) == '1' || idNumber.charAt(6) == '2') {	// 20th century
			cusYear += 1900;
		} else if (idNumber.charAt(6) == '3' || idNumber.charAt(6) == '4') {	// 21th century
			cusYear += 2000;
		}
		age = thisYear - cusYear;
		if (cusMonth > thisMonth || (cusMonth == thisMonth && cusDay > thisDay)) {
			// before birthday
			age--;
		}
		return age;
	}
	
	/**
	 * Find age group
	 * @return ageGroup in Cons value
	 */
	public static int ageGroup(int age) {
		if (age < Cons.MIN_CHILD) {
			return Cons.BABY;
		} else if (age >= Cons.MIN_CHILD && age <= Cons.MAX_CHILD) {
			return Cons.CHILD;
		} else if (age >= Cons.MIN_TEEN && age <= Cons.MAX_TEEN) {
			return Cons.TEEN;
		} else if (age >= Cons.MIN_ADULT && age <= Cons.MAX_ADULT) {
			return Cons.ADULT;
		} else {
			return Cons.OLD;
		}
	}
	
	/**
	 * Get price by age group 
	 * @return raw price before discount 
	 */
	public int calRawPrice(int age, int ticket) {
		int rawPrice = 0;
		if (ageGroup(age) == Cons.BABY) {
			rawPrice = Cons.BABY_PRICE;
		} else if (ageGroup(age) == Cons.CHILD) {
			if (ticket == Cons.DAY) {
				rawPrice = Cons.CHILD_DAY_PRICE;
			} else {
				rawPrice = Cons.CHILD_NIGHT_PRICE;
			}
		} else if (ageGroup(age) == Cons.TEEN) {
			if (ticket == Cons.DAY) {
				rawPrice = Cons.TEEN_DAY_PRICE;
			} else {
				rawPrice = Cons.TEEN_NIGHT_PRICE;
			}
		} else if (ageGroup(age) == Cons.ADULT) {
			if (ticket == Cons.DAY) {
				rawPrice = Cons.ADULT_DAY_PRICE;
			} else {
				rawPrice = Cons.ADULT_NIGHT_PRICE;
			}
		} else {
			if (ticket == Cons.DAY) {
				rawPrice = Cons.OLD_DAY_PRICE;
			} else {
				rawPrice = Cons.OLD_NIGHT_PRICE;
			}
		}
		return rawPrice;
	}
	
	/**
	 * Calculate discounted price
	 * @return discounted price
	 */
	public int calDiscount(int rawPrice, int discountType) {
		double disPrice = 0;
		switch (discountType) {
		case Cons.DISCOUNT_DISABLE:
			disPrice = rawPrice * Cons.DISABLE_DISCOUNT_RATE;
			break;
		case Cons.DISCOUNT_MERIT:
			disPrice = rawPrice * Cons.MERIT_DISCOUNT_RATE;
			break;
		case Cons.DISCOUNT_MULTICHILD:
			disPrice = rawPrice * Cons.MULTICHILD_DISCOUNT_RATE;
			break;
		case Cons.DISCOUNT_PREGNANT:
			disPrice = rawPrice * Cons.PREGNANT_DISCOUNT_RATE;
			break;
		default:
			disPrice = rawPrice;
			break;
		}
		return (int) disPrice;
	}
	
	/**
	 * Calculate total price
	 * @return total price 
	 */
	public int calTotalPrice(int disPrice, int orderCount) {
		return disPrice * orderCount;
	}
	
	/**
	 * Save one order data to array list
	 */
	public void saveOrderList(Customer customer, ArrayList<Customer> orderList) {
		orderList.add(customer);
	}
}