package statistic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import purchase.Cons;

/**
 * @author kopo19
 * Read data from report file
 */
public class ReadFileClass {
	private String fileName = "report.csv";	// file to read
	private static Connection conn;
	private static Statement stmt;
	
	public ReadFileClass() throws Exception {
		Class.forName(Cons.JDBC_DRIVER);
		conn = DriverManager.getConnection(Cons.DB_URL, Cons.DB_ID, Cons.DB_PW);
		stmt = conn.createStatement();
	}
	
	/**
	 * Read data from file and save to Array list
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
	
	/**
	 * Read data from data base and save to Array list
	 * @return Array list that have file data
	 * @throws Exception
	 */
	public ArrayList<OrderLine> readDB() throws Exception {
		ArrayList<OrderLine> orderList = new ArrayList<OrderLine>();
		ResultSet list = stmt.executeQuery("select * from report");
		OrderLine od;
		while (list.next()) {
			od = new OrderLine(list.getString(1), list.getInt(2), list.getInt(3), list.getInt(4), list.getInt(5), list.getInt(6));
			orderList.add(od);
		}
		return orderList;
	}
}
