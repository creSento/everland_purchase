package purchase;

public class ConstP {
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
	
	// discount rate
	public static final double DISABLE_DISCOUNT_RATE = 0.6;
	public static final double MERIT_DISCOUNT_RATE = 0.5;
	public static final double MULTICHILD_DISCOUNT_RATE = 0.8;
	public static final double PREGNANT_DISCOUNT_RATE = 0.85;
	
	// age range
	public static final int MIN_BABY = 1;
	public static final int MIN_CHILD = 3;
	public static final int MAX_CHILD = 12;
	public static final int MIN_TEEN = 13;
	public static final int MAX_TEEN = 18;
	public static final int MIN_ADULT = 9;
	public static final int MAX_ADULT = 64;
	
	// age group
	public static final int BABY = 1;	// 유아
	public static final int CHILD = 2;	// 소인
	public static final int TEEN = 3;	//청소년
	public static final int ADULT = 4;	// 대인
	public static final int OLD = 5;	// 경로
	
	// order amount
	public static final int MAX_COUNT = 10;
	public static final int MIN_COUNT = 1;
}
