package purchase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TicketClass {
	
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
		if (idNumber.charAt(6) == '1' || idNumber.charAt(6) == '2') {	// 1900
			cusYear += 1900;
		} else if (idNumber.charAt(6) == '3' || idNumber.charAt(6) == '4') {	// 2000
			cusYear += 2000;
		}
		age = thisYear - cusYear;
		if (cusMonth > thisMonth || (cusMonth == thisMonth && cusDay > thisDay)) {
			// before birthday
			age--;
		}
		return age;
	}
	
	public int ageGroup(int age) {
		if (age < ConstP.MIN_CHILD) {
			return ConstP.BABY;
		} else if (age >= ConstP.MIN_CHILD && age <= ConstP.MAX_CHILD) {
			return ConstP.CHILD;
		} else if (age >= ConstP.MIN_TEEN && age <= ConstP.MAX_TEEN) {
			return ConstP.TEEN;
		} else if (age >= ConstP.MIN_ADULT && age <= ConstP.MAX_ADULT) {
			return ConstP.ADULT;
		} else {
			return ConstP.OLD;
		}
	}
	
	public int calRawPrice(int age, int ticket) {
		int rawPrice = 0;
		if (ageGroup(age) == ConstP.BABY) {
			rawPrice = ConstP.BABY_PRICE;
		} else if (ageGroup(age) == ConstP.CHILD) {
			if (ticket == 1) {
				rawPrice = ConstP.CHILD_DAY_PRICE;
			} else {
				rawPrice = ConstP.CHILD_NIGHT_PRICE;
			}
		} else if (ageGroup(age) == ConstP.TEEN) {
			if (ticket == 1) {
				rawPrice = ConstP.TEEN_DAY_PRICE;
			} else {
				rawPrice = ConstP.TEEN_NIGHT_PRICE;
			}
		} else if (ageGroup(age) == ConstP.ADULT) {
			if (ticket == 1) {
				rawPrice = ConstP.ADULT_DAY_PRICE;
			} else {
				rawPrice = ConstP.ADULT_NIGHT_PRICE;
			}
		} else {
			if (ticket == 1) {
				rawPrice = ConstP.OLD_DAY_PRICE;
			} else {
				rawPrice = ConstP.OLD_NIGHT_PRICE;
			}
		}
		return rawPrice;
	}
	
	public int calDiscount(int rawPrice, int discountType) {
		double disPrice = 0;
		switch (discountType) {
		case 2:
			disPrice = rawPrice * ConstP.DISABLE_DISCOUNT_RATE;
			break;
		case 3:
			disPrice = rawPrice * ConstP.MERIT_DISCOUNT_RATE;
			break;
		case 4:
			disPrice = rawPrice * ConstP.MULTICHILD_DISCOUNT_RATE;
			break;
		case 5:
			disPrice = rawPrice * ConstP.PREGNANT_DISCOUNT_RATE;
			break;
		default:
			disPrice = rawPrice;
			break;
		}
		return (int) disPrice;
	}
	
	public int calTotalPrice(int disPrice, int orderCount) {
		return disPrice * orderCount;
	}
	
	public void saveOrderList(int ticket, int age, int orderCount, int resultPrice, 
			int discountType, ArrayList<Customer> orderList) {
		Date today = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(today);
		Customer cus = new Customer(date, ticket, ageGroup(age), orderCount, resultPrice, discountType);
		orderList.add(cus);
	}
}