package statistic;

import java.util.ArrayList;
import java.util.TreeMap;

import purchase.Cons;

/**
 * @author kopo19
 * Do statistic work with report data
 */
public class StatisticClass {
	// list to temporary save the file by line
	private ArrayList<OrderLine> datalist;
	
	/**
	 * Initialize Object
	 * @param ArrayList that created in main witch have data from file
	 */
	public StatisticClass(ArrayList<OrderLine> datalist) {
		this.datalist = datalist;
	}
	
	/**
	 * Calculate sales data by day
	 * @return hash map of each day's sales
	 */
	public TreeMap<String, Integer> dateSales() {
		TreeMap<String, Integer> daySales = new TreeMap<String, Integer>();
		String date = null;
		for(OrderLine cus : datalist) {
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
	 * Calculate sales data by ticket type
	 * @return array of sales : total and by age group
	 */
	public int[][] ticketType() {
		int[][] sales = new int[3][7];
		int type = 0;
		for(OrderLine cus : datalist) {
			if (cus.getTicketType() == Cons.DAY) {
				type = Cons.DAY;
			} else {
				type = Cons.NIGHT;
			}
			sales[type][Cons.TOTAL_COUNT] += cus.getOrderCount();
			sales[type][Cons.TOTAL_SALES] += cus.getPrice();
			switch (cus.getAge()) {
			case Cons.BABY:
				sales[type][Cons.BABY] += cus.getOrderCount();
				break;
			case Cons.CHILD:
				sales[type][Cons.CHILD] += cus.getOrderCount();
				break;
			case Cons.TEEN:
				sales[type][Cons.TEEN] += cus.getOrderCount();
				break;
			case Cons.ADULT:
				sales[type][Cons.ADULT] += cus.getOrderCount();
				break;
			case Cons.OLD:
				sales[type][Cons.OLD] += cus.getOrderCount();
				break;
			default:
				break;
			}
		}
		return sales;
	}
	
	/**
	 * Calculate sales data by discount type
	 * @return array of sales : by discount type
	 */
	public int[] discountType() {
		int[] sales = new int[6];
		for (OrderLine cus : datalist) {
			sales[Cons.TOTAL_COUNT] += cus.getOrderCount();
			switch (cus.getDiscountType()) {
			case Cons.DISCOUNT_NONE:
				sales[Cons.DISCOUNT_NONE] += cus.getOrderCount();
				break;
			case Cons.DISCOUNT_DISABLE:
				sales[Cons.DISCOUNT_DISABLE] += cus.getOrderCount();
				break;
			case Cons.DISCOUNT_MERIT:
				sales[Cons.DISCOUNT_MERIT] += cus.getOrderCount();
				break;
			case Cons.DISCOUNT_MULTICHILD:
				sales[Cons.DISCOUNT_MULTICHILD] += cus.getOrderCount();
				break;
			case Cons.DISCOUNT_PREGNANT:
				sales[Cons.DISCOUNT_PREGNANT] += cus.getOrderCount();
				break;
			default:
				break;
			}
		}
		return sales;
	}
}
