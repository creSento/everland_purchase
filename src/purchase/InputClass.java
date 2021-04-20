package purchase;

import java.util.Scanner;
/**
 * @author kopo19
 * Get data from console
 */
public class InputClass {
	private Scanner sc;
	private int ticket;
	private String idNumber;
	private int orderCount;
	private int discountType;
	
	/**
	 * Initialize Object
	 */
	public InputClass() {
		this.ticket = 0;
		this.idNumber = "";
		this.orderCount = 0;
		this.discountType = 0;
	}

	public int getTicket() {
		return ticket;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public int getDiscountType() {
		return discountType;
	}

	/**
	 * Run process method
	 */
	public void runInput() {
		ticket = inputTicket();
		idNumber = inputCustomerID();
		orderCount = inputOrderCount();
		discountType = inputDiscount();
	}
	
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
			if (ticket == Cons.DAY || ticket == Cons.NIGHT) {
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
			if (idNumber.length() == 13 && 
					((int) idNumber.charAt(6) >= 49 && (int) idNumber.charAt(6) <= 52)) {	// ASCII 1 ~ 4
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
			if (orderCount >= Cons.MIN_COUNT && orderCount <= Cons.MAX_COUNT) {
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
			if (discountType >= Cons.DISCOUNT_NONE && discountType <= Cons.DISCOUNT_PREGNANT) {
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
			if (isContinue == Cons.CONTINUE || isContinue == Cons.CLOSE) {
				break;
			} else {
				OutputClass.errMsg();
			}
		}
		
		return isContinue;
	}
	
	/**
	 * @return 1 = continue program, 2 = end
	 */
	public int programContinue() {
		int isExit = 0;
		System.out.printf("계속 진행(1: 새로운 주문, 2: 프로그램 종료) : ");
		sc = new Scanner(System.in);
		isExit = sc.nextInt();
		
		return isExit;
	}
}