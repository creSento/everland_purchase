package purchase;
/**
 * @author kopo19
 * final variables
 */
public class Cons {
	
	// ticket type
	public static final int DAY = 1;
	public static final int NIGHT = 2;

	// age range
	public static final int MIN_BABY = 1;
	public static final int MIN_CHILD = 3;
	public static final int MAX_CHILD = 12;
	public static final int MIN_TEEN = 13;
	public static final int MAX_TEEN = 18;
	public static final int MIN_ADULT = 9;
	public static final int MAX_ADULT = 64;

	// age group
	public static final int BABY = 1; // 유아
	public static final int CHILD = 2; // 소인
	public static final int TEEN = 3; // 청소년
	public static final int ADULT = 4; // 대인
	public static final int OLD = 5; // 경로

	// order amount
	public static final int MAX_COUNT = 10;
	public static final int MIN_COUNT = 1;

	// price
	public static final int BABY_PRICE = 0;
	public static final int CHILD_DAY_PRICE = 44000;
	public static final int CHILD_NIGHT_PRICE = 37000;
	public static final int TEEN_DAY_PRICE = 47000;
	public static final int TEEN_NIGHT_PRICE = 40000;
	public static final int ADULT_DAY_PRICE = 56000;
	public static final int ADULT_NIGHT_PRICE = 46000;
	public static final int OLD_DAY_PRICE = 44000;
	public static final int OLD_NIGHT_PRICE = 37000;

	// discount
	public static final int DISCOUNT_NONE = 1;
	public static final int DISCOUNT_DISABLE = 2;
	public static final int DISCOUNT_MERIT = 3;
	public static final int DISCOUNT_MULTICHILD = 4;
	public static final int DISCOUNT_PREGNANT = 5;
	
	// discount rate
	public static final double DISABLE_DISCOUNT_RATE = 0.6;
	public static final double MERIT_DISCOUNT_RATE = 0.5;
	public static final double MULTICHILD_DISCOUNT_RATE = 0.8;
	public static final double PREGNANT_DISCOUNT_RATE = 0.85;

	// continue
	public static final int CONTINUE = 1;
	public static final int CLOSE = 2;

	// columns of report file
	public static final int DATE = 0;
	public static final int DAY_NIGHT = 1;
	public static final int AGE = 2;
	public static final int COUNT = 3;
	public static final int PRICE = 4;
	public static final int DISCOUNT = 5;

	// array index
	public static final int TOTAL_COUNT = 0;
	public static final int TOTAL_SALES = 6;
	
	// data base
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
	public static final String DB_URL = "jdbc:mysql://localhost:3306/test"; 
	public static final String DB_ID = "root"; 
	public static final String DB_PW = "qwerty"; 
}
