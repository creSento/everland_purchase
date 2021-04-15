package stastic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) throws IOException {
		ArrayList<CustomerOrder> list = new ArrayList<CustomerOrder>();
		ReadFileClass read = new ReadFileClass();
		list = read.readAll();

		PrintFileClass print = new PrintFileClass(list);
		StasticsClass stc = new StasticsClass(list);
		HashMap<String, Integer> date = stc.dateSales();

		print.prtAll();
		print.prtDaySales(date);
		int[][] ticketType = stc.ticketType();
		print.prtTicketType(ticketType);
		int[] discountType = stc.discountType();
		print.prtDiscountType(discountType);
		
	}

}
