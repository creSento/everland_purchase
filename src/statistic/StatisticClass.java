package statistic;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticClass {
	// list to temporary save the file by line
	private ArrayList<CustomerOrder> datalist;
	
	public StatisticClass(ArrayList<CustomerOrder> datalist) {
		this.datalist = datalist;
	}
	
	/**
	 * calculate sales data by day
	 * @return hash map of each day's sales
	 */
	public HashMap<String, Integer> dateSales() {
		HashMap<String, Integer> daySales = new HashMap<String, Integer>();
		String date = null;
		for(CustomerOrder cus : datalist) {
			date = String.format("%s-%s-%s", cus.getDate().substring(0, 4),
					cus.getDate().substring(4, 6), cus.getDate().substring(6, 8));
			if (daySales.containsKey(date)) {
				daySales.put(date, daySales.get(date) + cus.getPrice());
			} else {
				daySales.put(date, cus.getPrice());
			}
		}
		return daySales;
	}
	
	/**
	 * calculate sales data by ticket type
	 * @return array of sales : total and by age group
	 */
	public int[][] ticketType() {
		int[][] sales = new int[3][7];
		int type = 0;
		for(CustomerOrder cus : datalist) {
			if (cus.getTicketType() == ConstS.DAY) {
				type = ConstS.DAY;
			} else {
				type = ConstS.NIGHT;
			}
			sales[type][ConstS.TOTAL_COUNT] += cus.getOrderCount();
			sales[type][ConstS.TOTAL_SALES] += cus.getPrice();
			switch (cus.getAge()) {
			case ConstS.BABY:
				sales[type][ConstS.BABY] += cus.getOrderCount();
				break;
			case ConstS.CHILD:
				sales[type][ConstS.CHILD] += cus.getOrderCount();
				break;
			case ConstS.TEEN:
				sales[type][ConstS.TEEN] += cus.getOrderCount();
				break;
			case ConstS.ADULT:
				sales[type][ConstS.ADULT] += cus.getOrderCount();
				break;
			case ConstS.OLD:
				sales[type][ConstS.OLD] += cus.getOrderCount();
				break;
			default:
				break;
			}
		}
		return sales;
	}
	
	/**
	 * calculate sales data by discount type
	 * @return array of sales : by discount type
	 */
	public int[] discountType() {
		int[] sales = new int[6];
		for (CustomerOrder cus : datalist) {
			sales[ConstS.TOTAL_COUNT] += cus.getOrderCount();
			switch (cus.getDiscountType()) {
			case ConstS.NONE:
				sales[ConstS.NONE] += cus.getOrderCount();
				break;
			case ConstS.DISABLE:
				sales[ConstS.DISABLE] += cus.getOrderCount();
				break;
			case ConstS.MERIT:
				sales[ConstS.MERIT] += cus.getOrderCount();
				break;
			case ConstS.MULTICHILD:
				sales[ConstS.MULTICHILD] += cus.getOrderCount();
				break;
			case ConstS.PREGNANT:
				sales[ConstS.PREGNANT] += cus.getOrderCount();
				break;
			default:
				break;
			}
		}
		return sales;
	}
}






























