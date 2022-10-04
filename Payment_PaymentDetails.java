//$Id$
package payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Payment_PaymentDetails extends Payment_UserRegistration {
	SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
	Scanner input = new Scanner(System.in);

	public void toPay() throws ParseException {
		System.out.println("************************************");
		System.out.println("Select your Payment method");
		System.out.println("1.Pay via UPI");
		System.out.println("2.Pay via BankAccount");
		System.out.println("3.Go back");
		System.out.println("Enter your choice");
		int choice = input.nextInt();

		switch (choice) {
		case 1:
			payViaUPI();
			break;
		case 2:
			payViaBankAccount();
			break;
		case 3:
			userMenu();
			break;
		case 4:
			System.out.println("Please check your option");
			toPay();

		}
	}

	public void payViaUPI() throws ParseException {
		String acc_no, upi_id, bank_acc, ifsc_code;
		double amt, balance = 0;
		System.out.println("*********************************************");
		System.out.println("Enter your account number");
		acc_no = input.next();
		System.out.println("Enter the amount to pay");
		amt = input.nextDouble();
		boolean flag = false;
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getAcc_number().equals(acc_no) && bank_details.get(i).getMailid().equals(mailid)) {
				flag = true;
				if (bank_details.get(i).getBalance() < amt) {
					System.out.println("You are not having sufficient amount to pay");
					try {
						userMenu();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					balance = bank_details.get(i).getBalance();
					balance = balance - amt;
					bank_details.get(i).setBalance(balance);
				}
			}
		}
		Date date = new Date();

		String datestr = formatter.format(date);
		if (flag) {
			System.out.println("Enter UPI_ID");
			upi_id = input.next();
			boolean upi = true;
			for (int i = 0; i < bank_details.size(); i++) {
				if (bank_details.get(i).getUpi_id().equals(upi_id)) {
					upi = false;
					balance = bank_details.get(i).getBalance();
					balance = balance + amt;
					bank_details.get(i).setBalance(balance);
					System.out.println("Successfully paid");

					transaction_log.add(new TransactionLogs(mailid, acc_no, "pay via upi", amt, datestr));
					toAddBankAccount();
				}
			}

			if (upi) {
				System.out.println("Successfully paid");
				transaction_log.add(new TransactionLogs(mailid, acc_no, "pay via upi", amt, datestr));
				toAddBankAccount();
			}

		}
		toPay();

	}

	public void payViaBankAccount() throws ParseException {
		// String mailid=PaymentApp_User.mailid;
		// PaymentApp_BankDetails bank=new PaymentApp_BankDetails();
		String acc_no, bank_acc, ifsc_code;
		double amt, balance = 0;

		System.out.println("*********************************************");
		System.out.println("Enter your account number");
		acc_no = input.next();
		System.out.println("Enter the amount to pay");
		amt = input.nextDouble();
		boolean flag = false;
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getAcc_number().equals(acc_no) && bank_details.get(i).getMailid().equals(mailid)) {
				flag = true;
				if (bank_details.get(i).getBalance() < amt) {
					System.out.println("You are not having sufficient amount to pay");
					try {
						userMenu();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					balance = bank_details.get(i).getBalance();
					balance = balance - amt;
					bank_details.get(i).setBalance(balance);
				}
			}
		}
		Date date = new Date();
		String datestr = formatter.format(date);
		System.out.println("Enter the account number");
		bank_acc = input.next();
		System.out.println("Enter the IFSC_code");
		ifsc_code = input.next();
		boolean acc_flag = true;
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getAcc_number().equals(bank_acc) && bank_details.get(i).getIfsc_code().equals(ifsc_code)) {
				acc_flag = false;
				balance = bank_details.get(i).getBalance();
				balance = balance + amt;
				bank_details.get(i).setBalance(balance);
				System.out.println("Successfully paid");

				transaction_log.add(new TransactionLogs(mailid, acc_no, "pay via BankAccount number", amt, datestr));
			}
		}

		if (acc_flag) {
			System.out.println("Successfully paid");
			transaction_log.add(new TransactionLogs(mailid, acc_no, "pay via BankAccount number", amt, datestr));
		}
		userMenu();

	}

}
