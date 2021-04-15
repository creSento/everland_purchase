package everland;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	public static void prtOrderList(int totalPrice, int cusNo, ArrayList<String> orderList) throws IOException {
		System.out.printf("티켓 발권을 종료합니다. 감사합니다.\n\n");
		System.out.printf("========================= EverLand =========================\n");
		String[] datas = new String[5];
		String line = "";
		String inputData = "";
		for (int i = 0; i < orderList.size(); i++) {
			line = orderList.get(i);
			datas = line.split(",");
			if (datas[0].equals("1")) {
				System.out.printf("주간권	");
				datas[0] = "주간권";
			} else if (datas[0].equals("2")) {
				System.out.printf("야간권	");
				datas[0] = "야간권";
			}
			
			if (datas[1].equals("1")) {
				System.out.printf("유아	");
				datas[1] = "유아";
			} else if (datas[1].equals("2")) {
				System.out.printf("소인	");
				datas[1] = "소인";
			} else if (datas[1].equals("3")) {
				System.out.printf("청소년	");
				datas[1] = "청소년";
			} else if (datas[1].equals("4")) {
				System.out.printf("어른	");
				datas[1] = "어른";
			} else {
				System.out.printf("노인	");
				datas[1] = "노인";
			}
			
			System.out.printf("X %3d	", Integer.parseInt(datas[2]));
			System.out.printf("%8d원	", Integer.parseInt(datas[3]));
			
			if (datas[4].equals("1")) {
				System.out.printf("우대적용 없음\n");
				datas[4] = "없음";
			} else if (datas[4].equals("2")) {
				System.out.printf("장애인 우대적용\n");
				datas[4] = "장애인";
			} else if (datas[4].equals("3")) {
				System.out.printf("국가유공자 우대적용\n");
				datas[4] = "국가유공자";
			} else if (datas[4].equals("4")) {
				System.out.printf("다자녀 우대적용\n");
				datas[4] = "다자녀";
			} else if (datas[4].equals("5")) {
				System.out.printf("임산부 우대적용\n");
				datas[4] = "임산부";
			}
			inputData = String.format("%s,%s,%s,%s,%s", datas[0], datas[1], datas[2], datas[3], datas[4]);
			writeFile(inputData);
		}
		System.out.printf("\n입장료 총액은 %d원 입니다.\n", totalPrice);
		System.out.printf("============================================================\n\n");
	}
	
	public static void writeFile(String line) throws IOException {
		Date today = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(today);
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "utf-8"));
		bw.append(String.format("%s,%s\n", date, line));
		bw.close();
	}
}
