package stastic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StasticMain {

	public static void main(String[] args) throws IOException {
		ReadFileClass read = new ReadFileClass();
		ArrayList<CustomerOrder> list = read.readAll();
		PrintFileClass print = new PrintFileClass(list);
		StasticsClass stc = new StasticsClass(list);
		
		// print console
		// all of list
		print.prtAll();
		// date sales 
		HashMap<String, Integer> day = stc.dateSales();
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
