package stastic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFileClass {
	private String fileName = "report.csv";
	
	public ArrayList<CustomerOrder> readAll() throws IOException {
		ArrayList<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
		CustomerOrder order = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
		String line = "";
		String date;
		br.readLine();	// read first line
		int ticketType, age, orderCount, price, discountType;
		while ((line = br.readLine()) != null) {
			date = line.split(",")[ConstS.DATE];
			ticketType = Integer.parseInt(line.split(",")[ConstS.DAY_NIGHT]);
			age = Integer.parseInt(line.split(",")[ConstS.AGE]);
			orderCount = Integer.parseInt(line.split(",")[ConstS.COUNT]);
			price = Integer.parseInt(line.split(",")[ConstS.PRICE]);
			discountType = Integer.parseInt(line.split(",")[ConstS.DISCOUNT]);
			order = new CustomerOrder(date, ticketType, age, orderCount, price, discountType);
			orderList.add(order);
		}
		br.close();
		return orderList;
	}
}
