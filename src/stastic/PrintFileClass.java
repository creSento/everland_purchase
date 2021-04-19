package stastic;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class PrintFileClass {
	private ArrayList<CustomerOrder> datalist;
	private String dateFile = "dateSales";
	private String ticketTypeFile = "ticketSales";
	private String discountFile = "discountSales";
	
	public PrintFileClass(ArrayList<CustomerOrder> datalist) {
		this.datalist = datalist;
	}
	
	public void prtAll() {
		String ticket = null, ageGroup = null, discount = null;
		System.out.printf("%8s\t%s\t%s\t%s\t%s\t%s\n", 
				"날짜", "권종", "연령대", "수량", "가격", "우대사항");
		for(CustomerOrder c : datalist) {
			switch (c.getTicketType()) {
			case ConstS.DAY:
				ticket = "주간권";
				break;
			case ConstS.NIGHT:
				ticket = "야간권";
				break;
			default:
				break;
			}
			switch (c.getAge()) {
			case ConstS.BABY:
				ageGroup = "유아";
				break;
			case ConstS.CHILD:
				ageGroup = "소인";
				break;
			case ConstS.TEEN:
				ageGroup = "청소년";
				break;
			case ConstS.ADULT:
				ageGroup = "어른";
				break;
			case ConstS.OLD:
				ageGroup = "노인";
				break;
			default:
				break;
			}
			switch (c.getDiscountType()) {
			case ConstS.NONE:
				discount = "우대적용 없음";
				break;
			case ConstS.DISABLE:
				discount = "장애인 우대적용";
				break;
			case ConstS.MERIT:
				discount = "국가유공자 우대적용";
				break;
			case ConstS.MULTICHILD:
				discount = "다자녀 우대적용";
				break;
			case ConstS.PREGNANT:
				discount = "임산부 우대적용";
				break;
			default:
				break;
			}
			System.out.printf("%s\t%s\t%s\t%d\t%d\t%s\n",
					c.getDate(), ticket, ageGroup, 
					c.getOrderCount(), c.getPrice(), discount);
		}
	}
	
	public void prtDaySales(HashMap<String, Integer> daySales) throws IOException {
		Iterator<String> key = daySales.keySet().iterator();
		System.out.printf("\n========================= 일자별 매출현황 =========================\n");
		while (key.hasNext()) {
			String keyval = key.next();
			int val = daySales.get(keyval);
			System.out.println(keyval + " : " + val);
		}
		System.out.printf("===================================================================\n");
	}
	
	public void prtTicketType(int[][] sales) {
		System.out.printf("\n========================= 권종 별 판매현황 ========================\n");
		for (int i = 1; i < sales.length; i++) {
			if (i == ConstS.DAY) {
				System.out.printf("주간권 총 %d매\n", sales[i][ConstS.TOTAL_COUNT]);
			} else {
				System.out.printf("야간권 총 %d매\n", sales[i][ConstS.TOTAL_COUNT]);
			}
			System.out.printf("유아 %d매, 어린이 %d매, 청소년 %d매, 어른 %d매, 노인 %d매\n",
					sales[i][ConstS.BABY], sales[i][ConstS.CHILD], sales[i][ConstS.TEEN],
					sales[i][ConstS.ADULT], sales[i][ConstS.OLD]);
			if (i == ConstS.DAY) {
				System.out.printf("주간권 매출 : %d원\n", sales[i][ConstS.TOTAL_SALES]);
			} else {
				System.out.printf("야간권 매출 : %d원\n", sales[i][ConstS.TOTAL_SALES]);
			}
		}
		System.out.printf("===================================================================\n");
	}
	
	public void prtDiscountType(int[] sales) {
		System.out.printf("\n========================= 우대권 판매현황 =========================\n");
		System.out.printf("총 판매 티켓수\t\t:%10d매\n", sales[ConstS.TOTAL_COUNT]);
		System.out.printf("우대 없음\t\t:%10d매\n", sales[ConstS.NONE]);
		System.out.printf("장애인\t\t\t:%10d매\n", sales[ConstS.DISABLE]);
		System.out.printf("국가유공자\t\t:%10d매\n", sales[ConstS.MERIT]);
		System.out.printf("다자녀\t\t\t:%10d매\n", sales[ConstS.MULTICHILD]);
		System.out.printf("임산부\t\t\t:%10d매\n", sales[ConstS.PREGNANT]);
		System.out.printf("===================================================================\n");
	}
	
	public void writeDateFile(HashMap<String, Integer> daySales) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateFile + processDate(), true), "utf-8"));
		Iterator<String> key = daySales.keySet().iterator();
		while (key.hasNext()) {
			String keyval = key.next();
			int val = daySales.get(keyval);
			bw.append(String.format("%s,%s\n", keyval, val));
		}
		bw.close();
		System.out.println("Write DateFile"+processDate());
	}
	
	public void writeTicketFile(int[][] sales) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ticketTypeFile + processDate(), true), "utf-8"));
		bw.append("구분,주간권,야간권\n");
		bw.append(String.format("유아,%d,%d\n", sales[ConstS.DAY][ConstS.BABY], sales[ConstS.NIGHT][ConstS.BABY]));
		bw.append(String.format("어린이,%d,%d\n", sales[ConstS.DAY][ConstS.CHILD], sales[ConstS.NIGHT][ConstS.CHILD]));
		bw.append(String.format("청소년,%d,%d\n", sales[ConstS.DAY][ConstS.TEEN], sales[ConstS.NIGHT][ConstS.TEEN]));
		bw.append(String.format("어른,%d,%d\n", sales[ConstS.DAY][ConstS.ADULT], sales[ConstS.NIGHT][ConstS.ADULT]));
		bw.append(String.format("노인,%d,%d\n", sales[ConstS.DAY][ConstS.OLD], sales[ConstS.NIGHT][ConstS.OLD]));
		bw.append(String.format("합계,%d,%d\n", sales[ConstS.DAY][ConstS.TOTAL_COUNT], sales[ConstS.NIGHT][ConstS.TOTAL_COUNT]));
		bw.append(String.format("매출,%d,%d\n", sales[ConstS.DAY][ConstS.TOTAL_SALES], sales[ConstS.NIGHT][ConstS.TOTAL_SALES]));
		bw.close();
		System.out.println("Write TicketFile"+processDate());
	}
	
	public void writeDiscountFile(int[] sales) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(discountFile + processDate(), true), "utf-8"));
		bw.append(String.format("합계,%d\n", sales[ConstS.TOTAL_COUNT]));
		bw.append(String.format("우대없음,%d\n", sales[ConstS.NONE]));
		bw.append(String.format("장애인,%d\n", sales[ConstS.DISABLE]));
		bw.append(String.format("국가유공자,%d\n", sales[ConstS.MERIT]));
		bw.append(String.format("다자녀,%d\n", sales[ConstS.MULTICHILD]));
		bw.append(String.format("임산부,%d\n", sales[ConstS.PREGNANT]));
		bw.close();
		System.out.println("Write DiscountFile"+processDate());
	}
	
	private static String processDate() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("_yy_MM_dd");
		return sdf.format(today) + ".csv";
	}
}





























