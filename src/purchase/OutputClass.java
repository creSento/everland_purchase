package purchase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author kopo19
 * Print out data in console and write file
 */
public class OutputClass {
	private static BufferedWriter bw;
	private static String fileName = "report.csv";	// save order list to this file
	private static Connection conn;
	private static Statement stmt;
	
	public OutputClass() throws Exception {
		Class.forName(Cons.JDBC_DRIVER);
		conn = DriverManager.getConnection(Cons.DB_URL, Cons.DB_ID, Cons.DB_PW);
		stmt = conn.createStatement();
	}
	
	/**
	 * Print wrong input message
	 */
	public static void errMsg() {
		System.out.println("잘못 입력하셨습니다.");
	}
	
	/**
	 * Print price of each order 
	 */
	public static void prtPrice(int totalPrice) {
		System.out.printf("가격은 %d 원 입니다.\n", totalPrice);
		System.out.printf("감사합니다.\n\n");
	}
	
	/**
	 * Print all order list after process completed once
	 * @throws Exception 
	 */
	public static void prtOrderList(int totalPrice, ArrayList<Customer> orderList) throws Exception {
		System.out.printf("티켓 발권을 종료합니다. 감사합니다.\n\n");
		System.out.printf("========================= EverLand =========================\n");
		Customer cus = null;
		for (int i = 0; i < orderList.size(); i++) {
			cus = orderList.get(i);
			cus.getTicketTypeStr();
			System.out.printf("%s\t", cus.getTicketTypeStr());
			System.out.printf("%s\t", cus.getAgeStr());
			System.out.printf("X %3d\t", cus.getOrderCount());
			System.out.printf("%8d원\t", cus.getPrice());
			System.out.printf("%s\n", cus.getDiscountTypeStr());
			writeFile(cus);
			writeDB(cus);
		}
		System.out.printf("\n입장료 총액은 %d원 입니다.\n", totalPrice);
		System.out.printf("============================================================\n\n");
	}
	
	/**
	 * Save order list to file by line
	 */
	private static void writeFile(Customer customer) throws IOException {
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "utf-8"));
		File file = new File(fileName);
		if (file.exists()) {
			bw.append(String.format("%s,%s,%s,%d,%d,%s\n", 
					customer.getDate(), customer.getTicketType(), customer.getAge(), 
					customer.getOrderCount(), customer.getPrice(), customer.getDiscountType()));
			bw.close();
		} else {
			bw.write("날짜,권종,연령구분,수량,가격,우대사항\n");
			bw.close();
		}
	}
	
	/**
	 * Save data to data base directly
	 * @param customer - object that have order data
	 * @throws Exception
	 */
	private static void writeDB(Customer customer) throws Exception {
		stmt.execute("INSERT INTO report VALUES ("
				+ "DATE_FORMAT(now(), '%Y%m%d')" 
				+ customer.getTicketType() + ","
				+ customer.getAge() + ","
				+ customer.getOrderCount() + ","
				+ customer.getPrice() + ","
				+ customer.getDiscountType() + ");"
				);
		stmt.close();
		conn.close();
	}
}
