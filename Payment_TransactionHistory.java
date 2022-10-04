//$Id$
package payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment_TransactionHistory extends Payment_UserRegistration {
	SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

	public void toViewTransactionHistory() throws ParseException {

		int choice;

		System.out.println("********************************************************************");
		System.out.println("1.Toview last 7days transaction");
		System.out.println("2.Toview last 14days transaction");
		System.out.println("3.Toview last 30days transaction");
		System.out.println("4.Toview All transaction");
		System.out.println("5.Go Back");
		System.out.println("Enter your choice");
		choice = input.nextInt();
		switch (choice) {
		case 1:
			toViewdaysTransaction(7);
			break;
		case 2:
			toViewdaysTransaction(14);
		case 3:
			toViewdaysTransaction(30);
			break;
		case 4:
			toViewdaysTransaction(0);
			break;
		case 5:
			userMenu();
			break;
		default:
			System.out.println("Please choose correct option");
			toViewTransactionHistory();

		}
	}

	public void toViewdaysTransaction(long day) throws ParseException {
		String acc_no;

		System.out.println("Enter Account number to check transaction History");
		String date = null;
		acc_no = input.next();
		Date curdate = new Date();

		String curr_date = formatter.format(curdate);
		Date current_date = formatter.parse(curr_date);
		Date trans_date = null;
		long diff;
		long days = 0;
		System.out.println("--------------------------------------------------------------------");
		System.out.println("MAIL_ID     ACC_NUM     AMOUNT      DATE      DESCRIPTION");
		System.out.println("--------------------------------------------------------------------");
		for (int i = 0; i < transaction_log.size(); i++) {
			if (transaction_log.get(i).getAcc_no().equals(acc_no) && transaction_log.get(i).getMailid().equals(mailid)) {
				date = transaction_log.get(i).getDate();
				trans_date = formatter.parse(date);
				diff = current_date.getTime() - trans_date.getTime();
				days = (diff / (60 * 60 * 1000)) / 24;
				// System.out.println(days);
				if (days <= day || day == 0) {
					System.out.println(transaction_log.get(i).getMailid() + "     " + transaction_log.get(i).getAcc_no() + "    " + transaction_log.get(i).getAmt() + "      " + transaction_log.get(i).getDate() + "     " + transaction_log.get(i).getTransaction_type());
				}
			}

		}
		userMenu();

	}

}
