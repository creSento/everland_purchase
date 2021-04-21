package statistic;

public class DBMain {

	public static void main(String[] args) throws Exception {
		DBStatisticClass db = new DBStatisticClass();
		DBWriteClasas write = new DBWriteClasas();
		db.salesAll();
		db.dateSales();
		db.typeSales();
		db.disSales();
		write.writeDateFile();
		write.writeTicketFile();
		write.writeTicketTotal();
		write.writeDiscountFile();
	}

}
