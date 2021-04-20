package statistic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class StatisticMain {

	public static void main(String[] args) throws IOException {
		ReadFileClass read = new ReadFileClass();
		ArrayList<OrderLine> list = read.readAll();	// array list that have file data
		PrintFileClass print = new PrintFileClass(list);
		StatisticClass stc = new StatisticClass(list);
		
		// print console
		// all of list
		print.prtAll();
		// date sales 
		TreeMap<String, Integer> day = stc.dateSales();
		print.prtDaySales(day);
		// ticket type sales
		int[][] ticketType = stc.ticketType();
		print.prtTicketType(ticketType);
		// discount type sales
		int[] discountType = stc.discountType();
		print.prtDiscountType(discountType);
		
		// write file
		print.writeDateFile(day);
		print.writeTicketFile(ticketType);
		print.writeDiscountFile(discountType);
	}

}
