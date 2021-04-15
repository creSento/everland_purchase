package stastic;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PrintFileClass {
	private ArrayList<CustomerOrder> datalist;
	private String dateFile = "dateSales.csv";
	private String ticketTypeFile = "ticketSales.csv";
	private String discountFile = "discountSales.csv";
	
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
	
	public void prtDaySales(HashMap<String, Integer> dateSales) throws IOException {
		Iterator<String> key = dateSales.keySet().iterator();
		System.out.printf("\n========================= 일자별 매출현황 =========================\n");
		while (key.hasNext()) {
			String keyval = key.next();
			int val = dateSales.get(keyval);
			System.out.println(keyval + " : " + val);
		}
		System.out.printf("====================================================================\n");
	}
	
	public void prtTicketType(int[][] sales) {
		System.out.printf("\n========================= 권종 별 판매현황 =========================\n");
		for (int i = 1; i < sales.length; i++) {
			if (i == ConstS.DAY) {
				System.out.printf("주간권 총 %d매\n", sales[i][0]);
			} else {
				System.out.printf("야간권 총 %d매\n", sales[i][0]);
			}
			System.out.printf("유아 %d매, 어린이 %d매, 청소년 %d매, 어른 %d매, 노인 %d매\n",
					sales[i][ConstS.BABY], sales[i][ConstS.CHILD], sales[i][ConstS.TEEN],
					sales[i][ConstS.ADULT], sales[i][ConstS.OLD]);
			if (i == ConstS.DAY) {
				System.out.printf("주간권 매출 : %d원\n", sales[i][6]);
			} else {
				System.out.printf("야간권 매출 : %d원\n", sales[i][6]);
			}
		}
		System.out.printf("====================================================================\n");
	}
	
	public void prtDiscountType(int[] sales) {
		System.out.printf("\n========================= 우대권 판매현황 =========================\n");
		System.out.printf("총 판매 티켓수\t:%8d매\n", sales[0]);
		System.out.printf("우대 없음\t:%8d매\n", sales[ConstS.NONE]);
		System.out.printf("장애인\t\t:%8d매\n", sales[ConstS.DISABLE]);
		System.out.printf("국가유공자\t:%8d매\n", sales[ConstS.MERIT]);
		System.out.printf("다자녀\t\t:%8d매\n", sales[ConstS.MULTICHILD]);
		System.out.printf("임산부\t\t:%8d매\n", sales[ConstS.PREGNANT]);
		System.out.printf("====================================================================\n");
	}
	
}





























