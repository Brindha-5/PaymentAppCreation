//$Id$
package payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Payment_BankDetails {

	SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

	public static ArrayList<BankDetails> bank_details = new ArrayList<>();
	public static ArrayList<TransactionLogs> transaction_log = new ArrayList<>();

	Scanner input = new Scanner(System.in);

	public void userMenu() throws ParseException {
		System.out.println("**********************************");
		System.out.println("1.Bank Accounts");
		System.out.println("2.Pay");
		System.out.println("3.Invites");
		System.out.println("4.Transaction");
		System.out.println("5.Display all Details");
		System.out.println("6.Logout");
		System.out.println("Enter your choice");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			toAddBankAccount();
			break;
		case 2:
			new Payment_PaymentDetails().toPay();
			break;
		case 3:
			new Payment_Invite().toInvite();
			break;
		case 4:
			new Payment_TransactionHistory().toViewTransactionHistory();
			break;
		case 5:
			new Payment_DisplayDetails().toDisplayDetails();
			break;

		case 6:
			System.out.println("Thanks for Choosing and using our App");
			break;
		default:
			System.out.println("Please check your input");
			userMenu();

		}

	}

	public void toAddBankAccount() throws ParseException {
		System.out.println("***************************************");
		System.out.println("1.Add Account");
		System.out.println("2.Check Balance");
		System.out.println("3.Add Money");
		System.out.println("4.Remove Account");
		System.out.println("5.Go Back");
		System.out.println("Enter your choice");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			toAddAccountDetails();
			break;
		case 2:
			toCheckBalance();
			break;
		case 3:
			toAddMoney();
			break;
		case 4:
			toRemoveAccount();
			break;
		case 5:
			try {
				userMenu();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Please check your choice");
			toAddBankAccount();

		}
	}

	public void toAddAccountDetails() {

		String mailid = Payment_UserRegistration.mailid;
		String acc_no, IFSC_code, display_name, bank_name, upi_id;
		double balance;
		boolean acc_valid = true;
		System.out.println("*********************************************");
		System.out.println("Enter your account number");
		acc_no = input.next();
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getAcc_number().equals(acc_no)) {
				System.out.println("This account number is already registered");
				acc_valid = false;
				toAddAccountDetails();
			}
		}
		if (acc_valid) {
			System.out.println("Enter your IFSC Code");
			IFSC_code = input.next();
			System.out.println("Enter your Display name");
			display_name = input.next();

			for (int i = 0; i < bank_details.size(); i++) {
				if (bank_details.get(i).getDisplay_name().equals(display_name)) {
					System.out.println("Please try with different name");
					toAddAccountDetails();

				}
			}
			System.out.println("Enter your Bank name");
			bank_name = input.next();
			System.out.println("Enter your account balance");
			balance = input.nextDouble();
			upi_id = display_name + "@" + bank_name;
			System.out.println("Your UPI_ID is" + upi_id);
			bank_details.add(new BankDetails(mailid, acc_no, IFSC_code, bank_name, display_name, upi_id, balance, 0));
			System.out.println("Your Account is Added Successfully");
			System.out.println("Do you want to continue yes-1/no-0");
			int con = input.nextInt();
			if (con == 1) {
				try {
					userMenu();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Thanks for using our App");
			}

		}
	}

	public void toCheckBalance() throws ParseException {
		String mailid = Payment_UserRegistration.mailid;
		System.out.println(mailid);
		String acc_no;
		System.out.println("Enter your Account number to check balance");
		acc_no = input.next();
		boolean flag = true;
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getAcc_number().equals(acc_no) && bank_details.get(i).getMailid().equals(mailid)) {
				System.out.println("Your available balance is :" + bank_details.get(i).getBalance());
				flag = false;
				toAddBankAccount();

			}
		}
		if (flag) {
			System.out.println("Please check your account number");

			toAddBankAccount();
		}

	}

	public void toAddMoney() throws ParseException {
		String mailid = Payment_UserRegistration.mailid;
		String acc_no;
		double amount, balance = 0;

		System.out.println("Enter the Account number");
		acc_no = input.next();
		boolean flag = false;
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getAcc_number().equals(acc_no) && bank_details.get(i).getMailid().equals(mailid)) {
				flag = true;
				balance = bank_details.get(i).getBalance();

			}
		}
		if (flag) {
			System.out.println("Enter the amount to add in your account");
			amount = input.nextDouble();
			balance = balance + amount;

			for (int i = 0; i < bank_details.size(); i++) {
				if (bank_details.get(i).getAcc_number().equals(acc_no) && bank_details.get(i).getMailid().equals(mailid)) {
					bank_details.get(i).setBalance(balance);

				}
			}
			System.out.println("The amount is successfully added to your account");
			System.out.println("The current balance in your account is" + balance);
			Date date = new Date();
			// SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
			String datestr = formatter.format(date);
			transaction_log.add(new TransactionLogs(mailid, acc_no, "Deposit", amount, datestr));
			toAddBankAccount();

		} else {
			System.out.println("Please check your account number");
			try {
				userMenu();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void toRemoveAccount() throws ParseException {
		String acc_no;
		String mailid = Payment_UserRegistration.mailid;

		System.out.println("Enter the Account number");
		acc_no = input.next();
		boolean flag = false;
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getAcc_number().equals(acc_no) && bank_details.get(i).getMailid().equals(mailid)) {
				bank_details.remove(i);
				flag = true;

			}
		}
		if (flag) {
			System.out.println("The account is removed successfully");
		} else {
			System.out.println("Account doesnt exist");
		}
		userMenu();

	}

	public void display() throws ParseException {
		for (int i = 0; i < bank_details.size(); i++) {
			System.out.println(bank_details.get(i).getMailid() + " " + bank_details.get(i).getAcc_number() + "   " + bank_details.get(i).getBalance() + "    " + bank_details.get(i).getReward());
		}
		for (int i = 0; i < transaction_log.size(); i++) {
			System.out.println(transaction_log.get(i).getMailid() + " " + transaction_log.get(i).getAcc_no() + "   " + transaction_log.get(i).getDate() + " " + transaction_log.get(i).getAmt() + "  " + transaction_log.get(i).getTransaction_type());
		}
		userMenu();
	}

}
