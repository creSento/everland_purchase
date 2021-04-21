package statistic;

public class DBMain {

	public static void main(String[] args) throws Exception {
		DBStatisticClass db = new DBStatisticClass();
		DBWriteClasas write = new DBWriteClasas();
		
		// read data from DB table
		db.salesAll();
		db.dateSales();
		db.typeSales();
		db.disSales();
		
		// write csv file use DB table
		write.writeDateFile();
		write.writeTicketFile();
		write.writeDiscountFile();
	}

}
