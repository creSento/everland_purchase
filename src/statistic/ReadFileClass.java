package statistic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import purchase.Cons;

/**
 * @author kopo19
 * Read data from report file
 */
public class ReadFileClass {
	private String fileName = "report.csv";	// file to read
	
	/**
	 * @return Array list that have file data
	 */
	public ArrayList<OrderLine> readAll() throws IOException {
		ArrayList<OrderLine> orderList = new ArrayList<OrderLine>();
		OrderLine order = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
		String line = "";
		String date;
		br.readLine();	// read first line
		int ticketType, age, orderCount, price, discountType;
		while ((line = br.readLine()) != null) {
			date = line.split(",")[Cons.DATE];
			ticketType = Integer.parseInt(line.split(",")[Cons.DAY_NIGHT]);
			age = Integer.parseInt(line.split(",")[Cons.AGE]);
			orderCount = Integer.parseInt(line.split(",")[Cons.COUNT]);
			price = Integer.parseInt(line.split(",")[Cons.PRICE]);
			discountType = Integer.parseInt(line.split(",")[Cons.DISCOUNT]);
			order = new OrderLine(date, ticketType, age, orderCount, price, discountType);
			orderList.add(order);
		}
		br.close();
		return orderList;
	}
}
