package statistic;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import purchase.Cons;

public class DBWriteClasas {
	private String dateFile = "dateSales";
	private String ticketTypeFile = "ticketSales";
	private String discountFile = "discountSales";
	private BufferedWriter bw;
	private static Connection conn;
	private static Statement stmt;
	
	public DBWriteClasas() throws Exception {
		Class.forName(Cons.JDBC_DRIVER);
		conn = DriverManager.getConnection(Cons.DB_URL, Cons.DB_ID, Cons.DB_PW);
		stmt = conn.createStatement();
	}
	
	/**
	 * Save the result to file : sales by day
	 * @param result set from data base
	 * @throws Exception 
	 */
	public void writeDateFile() throws Exception {
		ResultSet byDate = stmt.executeQuery("SELECT date, SUM(amount), SUM(price) "
				+ "FROM report GROUP BY date ORDER BY date");
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateFile + processDate(), true), "utf-8"));
		bw.write("날짜,수량,매출액\n"); // head line
		while (byDate.next()) {
			bw.append(String.format("%s,%d,%d\n", byDate.getString(1), byDate.getInt(2), byDate.getInt(3)));
		}
		bw.close();
		System.out.println("Write DateFile" + processDate());
	}

	/**
	 * Save the result to file : sales by ticket type
	 * @param result set from data base
	 * @throws Exception
	 */
	public void writeTicketFile() throws Exception {
		ResultSet byTypeAge = stmt.executeQuery("SELECT age_group, "
				+ "SUM(CASE WHEN type = 1 THEN amount ELSE 0 END), "
				+ "SUM(CASE WHEN type = 2 THEN amount ELSE 0 END) "
				+ "FROM report GROUP BY age_group ORDER BY age_group");
		bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ticketTypeFile + processDate(), true), "utf-8"));
		bw.write("구분,주간권,야간권\n"); // head line
		String ageGroup = "";
		while (byTypeAge.next()) {
			if (byTypeAge.getInt(1) == Cons.BABY) {
				ageGroup = "유아";
			} else if (byTypeAge.getInt(1) == Cons.CHILD) {
				ageGroup = "어린이";
			} else if (byTypeAge.getInt(1) == Cons.TEEN) {
				ageGroup = "청소년";
			} else if (byTypeAge.getInt(1) == Cons.ADULT) {
				ageGroup = "어른";
			} else {
				ageGroup = "노인";
			}
			bw.append(String.format("%s,%d,%d\n", ageGroup, byTypeAge.getInt(2), byTypeAge.getInt(3)));
		}
		bw.flush();
		bw.close();
	}
	
	public void writeTicketTotal() throws Exception {
		ResultSet byType = stmt.executeQuery("SELECT type, SUM(amount), SUM(price)"
				+ "FROM report GROUP BY type ORDER BY type");
		bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ticketTypeFile + processDate(), true), "utf-8"));
		int dayCount = 0;
		int nightCount = 0; 
		int daySales = 0;
		int nightSales = 0;
		while (byType.next()) {
			if (byType.getInt(1) == 1) {
				dayCount += byType.getInt(2);
				daySales += byType.getInt(3);
			} else {
				nightCount += byType.getInt(2);
				nightSales += byType.getInt(3);
			}
		}
		bw.append(String.format("합계,%d,%d\n", dayCount, nightCount));
		bw.append(String.format("매출,%d,%d\n", daySales, nightSales));
		System.out.println("Write TicketFile" + processDate());
		bw.flush();
		bw.close();
	}

	/**
	 * Save the result to file : sales by discount type
	 * QUERY : SELECT discount, count(*), SUM(amount) FROM report GROUP BY discount ORDER BY discount
	 * @param result set from data base - list and total count
	 * @throws Exception
	 */
	public void writeDiscountFile() throws Exception {
		ResultSet byDis = stmt.executeQuery("SELECT discount, SUM(amount) FROM report GROUP BY discount ORDER BY discount");
		String disType = "";
		int total = 0;
		bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(discountFile + processDate(), true), "utf-8"));
		while (byDis.next()) {
			if (byDis.getInt(1) == Cons.DISCOUNT_NONE) {
				disType = "우대 없음";
			} else if (byDis.getInt(1) == Cons.DISCOUNT_DISABLE) {
				disType = "장애인";
			} else if (byDis.getInt(1) == Cons.DISCOUNT_MERIT) {
				disType = "국가유공자";
			} else if (byDis.getInt(1) == Cons.DISCOUNT_MULTICHILD) {
				disType = "다자녀";
			} else {
				disType = "임산부";
			}
			bw.append(String.format("%s,%d\n", disType, byDis.getInt(2)));
			total += byDis.getInt(2);
		}
		bw.append(String.format("합계,%d\n", total));
		bw.close();
		System.out.println("Write DiscountFile" + processDate());
	}

	/**
	 * Make date string attach to file name
	 * @return file write date and filename extension
	 */
	private static String processDate() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("_yy_MM_dd");
		return sdf.format(today) + "_db.csv";
	}
}
