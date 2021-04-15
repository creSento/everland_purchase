package everland;

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
		if (age < Const.MIN_CHILD) {
			return Const.BABY;
		} else if (age >= Const.MIN_CHILD && age <= Const.MAX_CHILD) {
			return Const.CHILD;
		} else if (age >= Const.MIN_TEEN && age <= Const.MAX_TEEN) {
			return Const.TEEN;
		} else if (age >= Const.MIN_ADULT && age <= Const.MAX_ADULT) {
			return Const.ADULT;
		} else {
			return Const.OLD;
		}
	}
	
	public int calRawPrice(int age, int ticket) {
		int rawPrice = 0;
		if (ageGroup(age) == Const.BABY) {
			rawPrice = Const.BABY_PRICE;
		} else if (ageGroup(age) == Const.CHILD) {
			if (ticket == 1) {
				rawPrice = Const.CHILD_DAY_PRICE;
			} else {
				rawPrice = Const.CHILD_NIGHT_PRICE;
			}
		} else if (ageGroup(age) == Const.TEEN) {
			if (ticket == 1) {
				rawPrice = Const.TEEN_DAY_PRICE;
			} else {
				rawPrice = Const.TEEN_NIGHT_PRICE;
			}
		} else if (ageGroup(age) == Const.ADULT) {
			if (ticket == 1) {
				rawPrice = Const.ADULT_DAY_PRICE;
			} else {
				rawPrice = Const.ADULT_NIGHT_PRICE;
			}
		} else {
			if (ticket == 1) {
				rawPrice = Const.OLD_DAY_PRICE;
			} else {
				rawPrice = Const.OLD_NIGHT_PRICE;
			}
		}
		return rawPrice;
	}
	
	public int calDiscount(int rawPrice, int discountType) {
		double disPrice = 0;
		switch (discountType) {
		case 2:
			disPrice = rawPrice * Const.DISABLE_DISCOUNT_RATE;
			break;
		case 3:
			disPrice = rawPrice * Const.MERIT_DISCOUNT_RATE;
			break;
		case 4:
			disPrice = rawPrice * Const.MULTICHILD_DISCOUNT_RATE;
			break;
		case 5:
			disPrice = rawPrice * Const.PREGNANT_DISCOUNT_RATE;
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
	
	public void saveOrderList(int ticket, int age, int orderCount, int totalPrice, 
			int discountType, int cusNo, ArrayList<String> orderList) {
		String data = String.format("%d,%d,%d,%d,%d", ticket, ageGroup(age), orderCount, totalPrice, discountType);
		orderList.add(cusNo, data);
	}
}



























