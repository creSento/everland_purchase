package purchase;

import java.util.Scanner;

public class InputClass {
	private Scanner sc;
	
	/**
	 * @return ticket type
	 */
	public int inputTicket() {
		int ticket = 0;
		System.out.printf("\n권종을 선택하세요.\n");
		System.out.printf("1. 주간권\n");
		System.out.printf("2. 야간권\n");
		while (true) {
			sc = new Scanner(System.in);
			try {
				ticket = sc.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}
			if (ticket == 1 || ticket == 2) {
				break;
			} else {
				OutputClass.errMsg();
			}
		}
		
		return ticket;
	}

	/**
	 * @return idNumber : to use find age group and ticket price
	 */
	public String inputCustomerID() {
		String idNumber = "";
		System.out.printf("주민등록번호를 입력하세요('-'제외)\n");
		while (true) {
			sc = new Scanner(System.in);
			idNumber = sc.nextLine();
			if (idNumber.length() == 13 && ((int) idNumber.charAt(6) >= 49 && (int) idNumber.charAt(6) <= 52)) {
				break;
			} else {
				OutputClass.errMsg();
			}
		}
		return idNumber;
	}
	
	/**
	 * @return amount of ticket
	 */
	public int inputOrderCount() {
		int orderCount = 0;
		System.out.printf("몇장을 주문하시겠습니까?(최대 10개)\n");
		while (true) {
			sc = new Scanner(System.in);
			orderCount = sc.nextInt();
			if (orderCount >= ConstP.MIN_COUNT && orderCount <= ConstP.MAX_COUNT) {
				break;
			} else {
				OutputClass.errMsg();
			}
		}
		
		return orderCount;
	}
	
	/**
	 * @return discount type
	 */
	public int inputDiscount() {
		int discountType = 0;
		System.out.printf("우대사항을 선택하세요.\n");
		System.out.printf("1. 없음(나이 우대는 자동처리)\n");
		System.out.printf("2. 장애인\n");
		System.out.printf("3. 국가유공자\n");
		System.out.printf("4. 다자녀\n");
		System.out.printf("5. 임산부\n");
		
		while (true) {
			sc = new Scanner(System.in);
			discountType = sc.nextInt();
			if (discountType >= 1 && discountType <= 5) {
				break;
			} else {
				OutputClass.errMsg();
			}
		}
		
		return discountType;
	}
	
	/**
	 * @return 1 = continue to purchase, 2 = end
	 */
	public int orderContinue() {
		int isContinue = 0;
		System.out.printf("계속 발권 하시겠습니까?\n");
		System.out.printf("1. 티켓발권\n");
		System.out.printf("2. 종료\n");
		while (true) {
			sc = new Scanner(System.in);
			isContinue = sc.nextInt();
			if (isContinue == 1 || isContinue == 2) {
				break;
			} else {
				OutputClass.errMsg();
			}
		}
		
		return isContinue;
	}
}