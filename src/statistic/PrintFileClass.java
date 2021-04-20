package statistic;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import purchase.Cons;

public class PrintFileClass {
	// list to temporary save the file by line
	private ArrayList<OrderLine> datalist;
	// file names
	private String dateFile = "dateSales";
	private String ticketTypeFile = "ticketSales";
	private String discountFile = "discountSales";
	private BufferedWriter bw;
	
	public PrintFileClass(ArrayList<OrderLine> datalist) {
		this.datalist = datalist;
	}
	
	/**
	 * print report file in console
	 */
	public void prtAll() {
		System.out.printf("%8s\t%s\t%s\t%s\t%s\t%s\n", 
				"날짜", "권종", "연령대", "수량", "가격", "우대사항");
		for(OrderLine c : datalist) {
			System.out.printf("%s\t%s\t%s\t%d\t%d\t%s\n",
					c.getDate(), c.getTicketTypeStr(), c.getAgeStr(), 
					c.getOrderCount(), c.getPrice(), c.getDiscountTypeStr());
		}
	}
	
	/**
	 * print sales by day in console
	 */
	public void prtDaySales(TreeMap<String, Integer> daySales) throws IOException {
		Iterator<String> key = daySales.keySet().iterator();
		System.out.printf("\n========================= 일자별 매출현황 =========================\n");
		while (key.hasNext()) {
			String keyval = key.next();
			int val = daySales.get(keyval);
			System.out.println(keyval + " : " + val);
		}
		System.out.printf("===================================================================\n");
	}
	
	/**
	 * print sales by ticket type in console
	 */
	public void prtTicketType(int[][] sales) {
		System.out.printf("\n========================= 권종 별 판매현황 ========================\n");
		for (int i = Cons.DAY; i < sales.length; i++) {
			if (i == Cons.DAY) {
				System.out.printf("주간권 총 %d매\n", sales[i][Cons.TOTAL_COUNT]);
			} else {
				System.out.printf("야간권 총 %d매\n", sales[i][Cons.TOTAL_COUNT]);
			}
			System.out.printf("유아 %d매, 어린이 %d매, 청소년 %d매, 어른 %d매, 노인 %d매\n",
					sales[i][Cons.BABY], sales[i][Cons.CHILD], sales[i][Cons.TEEN],
					sales[i][Cons.ADULT], sales[i][Cons.OLD]);
			if (i == Cons.DAY) {
				System.out.printf("주간권 매출 : %d원\n", sales[i][Cons.TOTAL_SALES]);
			} else {
				System.out.printf("야간권 매출 : %d원\n", sales[i][Cons.TOTAL_SALES]);
			}
		}
		System.out.printf("===================================================================\n");
	}
	
	/**
	 * print sales by discount type in console
	 */
	public void prtDiscountType(int[] sales) {
		System.out.printf("\n========================= 우대권 판매현황 =========================\n");
		System.out.printf("총 판매 티켓수\t\t:%10d매\n", sales[Cons.TOTAL_COUNT]);
		System.out.printf("우대 없음\t\t:%10d매\n", sales[Cons.DISCOUNT_NONE]);
		System.out.printf("장애인\t\t\t:%10d매\n", sales[Cons.DISCOUNT_DISABLE]);
		System.out.printf("국가유공자\t\t:%10d매\n", sales[Cons.DISCOUNT_MERIT]);
		System.out.printf("다자녀\t\t\t:%10d매\n", sales[Cons.DISCOUNT_MULTICHILD]);
		System.out.printf("임산부\t\t\t:%10d매\n", sales[Cons.DISCOUNT_PREGNANT]);
		System.out.printf("===================================================================\n");
	}
	
	/**
	 * save the result to file  : sales by day
	 */
	public void writeDateFile(TreeMap<String, Integer> daySales) throws IOException {
		bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(dateFile + processDate(), true), "utf-8"));
		Iterator<String> key = daySales.keySet().iterator();
		bw.write("날짜,매출");	// head line
		while (key.hasNext()) {
			String keyval = key.next();
			int val = daySales.get(keyval);
			bw.append(String.format("%s,%s\n", keyval, val));
		}
		bw.close();
		System.out.println("Write DateFile"+processDate());
	}
	
	/**
	 * save the result to file : sales by ticket type
	 */
	public void writeTicketFile(int[][] sales) throws IOException {
		bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ticketTypeFile + processDate(), true), "utf-8"));
		bw.write("구분,주간권,야간권\n");	// head line
		bw.append(String.format("유아,%d,%d\n", sales[Cons.DAY][Cons.BABY], sales[Cons.NIGHT][Cons.BABY]));
		bw.append(String.format("어린이,%d,%d\n", sales[Cons.DAY][Cons.CHILD], sales[Cons.NIGHT][Cons.CHILD]));
		bw.append(String.format("청소년,%d,%d\n", sales[Cons.DAY][Cons.TEEN], sales[Cons.NIGHT][Cons.TEEN]));
		bw.append(String.format("어른,%d,%d\n", sales[Cons.DAY][Cons.ADULT], sales[Cons.NIGHT][Cons.ADULT]));
		bw.append(String.format("노인,%d,%d\n", sales[Cons.DAY][Cons.OLD], sales[Cons.NIGHT][Cons.OLD]));
		bw.append(String.format("합계,%d,%d\n", sales[Cons.DAY][Cons.TOTAL_COUNT], sales[Cons.NIGHT][Cons.TOTAL_COUNT]));
		bw.append(String.format("매출,%d,%d\n", sales[Cons.DAY][Cons.TOTAL_SALES], sales[Cons.NIGHT][Cons.TOTAL_SALES]));
		bw.close();
		System.out.println("Write TicketFile"+processDate());
	}
	
	/**
	 * save the result to file : sales by discount type
	 */
	public void writeDiscountFile(int[] sales) throws IOException {
		bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(discountFile + processDate(), true), "utf-8"));
		bw.write(String.format("합계,%d\n", sales[Cons.TOTAL_COUNT]));
		bw.append(String.format("우대없음,%d\n", sales[Cons.DISCOUNT_NONE]));
		bw.append(String.format("장애인,%d\n", sales[Cons.DISCOUNT_DISABLE]));
		bw.append(String.format("국가유공자,%d\n", sales[Cons.DISCOUNT_MERIT]));
		bw.append(String.format("다자녀,%d\n", sales[Cons.DISCOUNT_MULTICHILD]));
		bw.append(String.format("임산부,%d\n", sales[Cons.DISCOUNT_PREGNANT]));
		bw.close();
		System.out.println("Write DiscountFile"+processDate());
	}
	
	/**
	 * @return file write date and filename extension
	 */
	private static String processDate() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("_yy_MM_dd");
		return sdf.format(today) + ".csv";
	}
}





























