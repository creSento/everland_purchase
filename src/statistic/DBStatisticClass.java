package statistic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import purchase.Cons;

/**
 * @author kopo19
 * Do statistic work with DB table 
 */
public class DBStatisticClass {
	private static Connection conn;
	private static Statement stmt;
	private static DecimalFormat df;
	
	/**
	 * Initialize Object - Set JDBC
	 * @throws Exception
	 */
	public DBStatisticClass() throws Exception {
		Class.forName(Cons.JDBC_DRIVER);
		conn = DriverManager.getConnection(Cons.DB_URL, Cons.DB_ID, Cons.DB_PW);
		stmt = conn.createStatement();
		df = new DecimalFormat("###,###,###,###");
	}
	
	/**
	 * Get All data from DB
	 * @throws Exception
	 */
	public void salesAll() throws Exception {
		ResultSet list = stmt.executeQuery("select * from report");
		System.out.printf("%6s\t%s\t%s\t%s\t%s\t%s\n", 
				"날짜", "권종", "연령대", "수량", "가격", "우대사항");
		while (list.next()) {
			System.out.printf("%s\t%d\t%d\t%d\t%s\t%8d\n",
					list.getString(1),
					list.getInt(2),
					list.getInt(3),
					list.getInt(4),
					df.format(list.getInt(5)),
					list.getInt(6));
		}
	}
	
	/**
	 * Get data Group by date
	 * @throws Exception
	 */
	public void dateSales() throws Exception {
		stmt.clearBatch();
		ResultSet byDate = stmt.executeQuery("SELECT date, SUM(amount), SUM(price) "
				+ "FROM report GROUP BY date ORDER BY date");
		System.out.printf("\n==================== 일자별 매출현황 ====================\n");
		System.out.printf("날짜\t\t수량\t매출액\n");
		while (byDate.next()) {
			System.out.printf(byDate.getString(1) + " :\t");
			System.out.printf(byDate.getInt(2) + "\t");
			System.out.printf(df.format(byDate.getInt(3)) + "\n");
		}
		System.out.printf("=========================================================\n");
	}
	
	/**
	 * Get data Group by type
	 * @throws Exception
	 */
	public void typeSales() throws Exception {
		stmt.clearBatch();
		ResultSet byTypeAge = stmt.executeQuery("SELECT age_group, "
				+ "SUM(CASE WHEN type = 1 THEN amount ELSE 0 END), "
				+ "SUM(CASE WHEN type = 2 THEN amount ELSE 0 END),"
				+ "SUM(CASE WHEN type = 1 THEN price ELSE 0 END),"
				+ "SUM(CASE WHEN type = 2 THEN price ELSE 0 END)"
				+ "FROM report GROUP BY age_group ORDER BY age_group");
		System.out.printf("\n==================== 권종 별 판매현황 ===================\n");
		System.out.printf("구분\t\t주간권\t야간권\n");
		prtType(byTypeAge);
		System.out.printf("\n---------------------------------------------------------\n");
		stmt.clearBatch();
		ResultSet byType = stmt.executeQuery("SELECT type, SUM(amount), SUM(price)"
				+ "FROM report GROUP BY type ORDER BY type");
		String typeName = "";
		while (byType.next()) {
			if (byType.getInt(1) == 1) {
				typeName = "주간권";
			} else {
				typeName = "야간권";
			}
			System.out.printf("%s : 총 %d매\n", typeName, byType.getInt(2));
			System.out.printf("%s 매출 : %s원\n", typeName, df.format(byType.getInt(3)));
		}
		System.out.printf("=========================================================\n");
	}
	
	/**
	 * Print data in console
	 * @param ResultSet - made by Statement
	 * @throws Exception
	 */
	private void prtType(ResultSet result) throws Exception {
		String ageGroup = "";
		int dayCount = 0;
		int nightCount = 0; 
		int daySales = 0;
		int nightSales = 0;
		while (result.next()) {
			if (result.getInt(1) == Cons.BABY) {
				ageGroup = "유아";
			} else if (result.getInt(1) == Cons.CHILD) {
				ageGroup = "어린이";
			} else if (result.getInt(1) == Cons.TEEN) {
				ageGroup = "청소년";
			} else if (result.getInt(1) == Cons.ADULT) {
				ageGroup = "어른";
			} else {
				ageGroup = "노인";
			}
			dayCount += result.getInt(2);
			daySales += result.getInt(4);
			nightCount += result.getInt(3);
			nightSales += result.getInt(5);
			System.out.printf("%-4s\t\t%4d매\t%4d매\n", ageGroup, result.getInt(2), result.getInt(3));
		}
		System.out.printf("주간권 : 총 %d매\t%s원\n", dayCount, df.format(daySales));
		System.out.printf("야간권 : 총 %d매\t%s원\n", nightCount, df.format(nightSales));
	}
	
	/**
	 * Get data Group by discount type
	 * @throws Exception
	 */
	public void disSales() throws Exception {
		stmt.clearBatch();
		ResultSet byDis = stmt.executeQuery("SELECT discount, SUM(amount) FROM report GROUP BY discount ORDER BY discount");
		System.out.printf("\n==================== 우대권 판매현황 ====================\n");
		int total = prtDiscount(byDis);
		System.out.printf("총 판매 티켓수\t\t:%10d매\n", total);
		System.out.printf("=========================================================\n");
	}
	
	/**
	 * Print data in console and calculate total amount
	 * @param ResultSet - made by Statement
	 * @return total amount
	 * @throws Exception
	 */
	private int prtDiscount(ResultSet result) throws Exception {
		String disType = "";
		int total = 0;
		while (result.next()) {
			if (result.getInt(1) == Cons.DISCOUNT_NONE) {
				disType = "우대 없음";
			} else if (result.getInt(1) == Cons.DISCOUNT_DISABLE) {
				disType = "장애인";
			} else if (result.getInt(1) == Cons.DISCOUNT_MERIT) {
				disType = "국가유공자";
			} else if (result.getInt(1) == Cons.DISCOUNT_MULTICHILD) {
				disType = "다자녀";
			} else {
				disType = "임산부";
			}
			System.out.printf("%-8s\t\t:%10d매\n", disType, result.getInt(2));
			total += result.getInt(2);
		}
		return total;
	}
	
}
