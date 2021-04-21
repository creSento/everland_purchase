package purchase;

import java.util.ArrayList;

public class TicketMain {
	
	public static void main(String[] args) throws Exception {
		int totalPrice = 0;
		int isContinue = 0;
		int isExit = 0;
		ArrayList<Customer> orderList;
		Customer customer;
		InputClass inputData;
		TicketClass tic;
		
		do {
			orderList = new ArrayList<Customer>();
			while (true) {
				// customer input
				inputData = new InputClass();
				inputData.runInput();
				// calculate price
				tic = new TicketClass(inputData);
				tic.runTicket();
				// save one order to order list
				customer = new Customer(inputData.getTicket(), tic.getAge(), inputData.getOrderCount(), 
								tic.getResultPrice(), inputData.getDiscountType());
				tic.saveOrderList(customer, orderList);
				// add total price
				totalPrice += tic.getResultPrice();
				// print price
				OutputClass.prtPrice(tic.getResultPrice());
				// continue or not
				isContinue = inputData.orderContinue();
				if (isContinue == Cons.CLOSE) {
					break;
				}
			}
			// print order
			OutputClass.prtOrderList(totalPrice, orderList);
			// exit program or not
			isExit = inputData.programContinue();
		} while (isExit == Cons.CONTINUE);
	}

}
