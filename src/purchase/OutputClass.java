package purchase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class OutputClass {
	private static BufferedWriter bw;
	private static String fileName = "report.csv";	// save order list to this file
	
	/**
	 * print wrong input message
	 */
	public static void errMsg() {
		System.out.println("잘못 입력하셨습니다.");
	}
	
	/**
	 * print price of each order 
	 */
	public static void prtPrice(int totalPrice) {
		System.out.printf("가격은 %d 원 입니다.\n", totalPrice);
		System.out.printf("감사합니다.\n\n");
	}
	
	/**
	 * print all order list after process completed once
	 */
	public static void prtOrderList(int totalPrice, ArrayList<Customer> orderList) throws IOException {
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
		}
		System.out.printf("\n입장료 총액은 %d원 입니다.\n", totalPrice);
		System.out.printf("============================================================\n\n");
	}
	
	/**
	 * save order list to file
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
}
