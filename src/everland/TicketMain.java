package everland;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketMain {
	
	public static void main(String[] args) throws IOException {
		int cusNo = 0;
		int totalPrice = 0;
		int isExit = 0;
		InputClass in = new InputClass();
		TicketClass tic = new TicketClass();
		Scanner sc;
		do {
			ArrayList<String> orderList = new ArrayList<String>();
			int ticket = 0;
			String idNumber = "";
			int orderCount = 0;
			int discountType = 0;
			int age = 0;
			int rawPrice = 0;
			int disPrice = 0;
			int resultPrice = 0;
			int isContinue = 0;
			while (true) {
				// customer input
				ticket = in.inputTicket();
				idNumber = in.inputCustomerID();
				orderCount = in.inputOrderCount();
				discountType = in.inputDiscount();
				// calculate price
				age = tic.customerAge(idNumber);
				rawPrice = tic.calRawPrice(age, ticket);
				disPrice = tic.calDiscount(rawPrice, discountType);
				resultPrice = tic.calTotalPrice(disPrice, orderCount);
				// save order
				tic.saveOrderList(ticket, age, orderCount, resultPrice, discountType, cusNo, orderList);
				// add total price
				totalPrice += resultPrice;
				// print price
				OutputClass.prtPrice(resultPrice);
				// continue or not
				isContinue = in.orderContinue();
				if (isContinue == 2) {
					break;
				}
				cusNo++;
			}
			// print order
			OutputClass.prtOrderList(totalPrice, cusNo, orderList);
			// exit or not
			System.out.printf("계속 진행(1: 새로운 주문, 2: 프로그램 종료) : ");
			sc = new Scanner(System.in);
			isExit = sc.nextInt();
		} while (isExit == 1);
		sc.close();
	}

}
