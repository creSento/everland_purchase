package everland;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class OutputClass {
	private static BufferedWriter bw;
	private static String fileName = "report.csv";
	
	public static void errMsg() {
		System.out.println("잘못 입력하셨습니다.");
	}
	
	public static void prtPrice(int totalPrice) {
		System.out.printf("가격은 %d 원 입니다.\n", totalPrice);
		System.out.printf("감사합니다.\n\n");
	}
	
	public static void prtOrderList(int totalPrice, int cusNo, ArrayList<Customer> orderList) throws IOException {
		System.out.printf("티켓 발권을 종료합니다. 감사합니다.\n\n");
		System.out.printf("========================= EverLand =========================\n");
		Customer cus = null;
		for (int i = 0; i < orderList.size(); i++) {
			cus = orderList.get(i);
			System.out.printf("%s\t", cus.getTicketType());
			System.out.printf("%s\t", cus.getAge());
			System.out.printf("X %3d\t", cus.getOrderCount());
			System.out.printf("%8d원\t", cus.getPrice());
			if (cus.getDiscountType().equals("없음")) {
				System.out.printf("우대적용 %s\n", cus.getDiscountType());
			} else {
				System.out.printf("%s 우대적용\n", cus.getDiscountType());
			}
			writeFile(cus);
		}
		System.out.printf("\n입장료 총액은 %d원 입니다.\n", totalPrice);
		System.out.printf("============================================================\n\n");
	}
	
	private static void writeFile(Customer customer) throws IOException {
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "utf-8"));
		bw.append(String.format("%s,%s,%s,%d,%d,%s\n", 
				customer.getDate(), customer.getTicketType(), customer.getAge(), 
				customer.getOrderCount(), customer.getPrice(), customer.getDiscountType()));
		bw.close();
	}
}
