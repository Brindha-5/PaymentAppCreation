//$Id$
package payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Payment_DisplayDetails extends Payment_UserRegistration {
	SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

	public void toDisplayDetails() throws ParseException {
		System.out.println("*******************************************************************");
		System.out.println("1.To view your Profile");
		System.out.println("2.To view your account details");
		System.out.println("3.To view your Transaction");
		System.out.println("To view your invite earnings");
		System.out.println("Please enter your choice");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			userProfile();
			break;
		case 2:
			accountDetails();
			break;
		case 3:
			new Payment_TransactionHistory().toViewdaysTransaction(0);
			break;
		case 4:
			new Payment_Invite().toViewRewardAmount();
			break;
		default:
			System.out.println("Please check your input");
			toDisplayDetails();
		}
	}

	public void userProfile() throws ParseException {

		System.out.println("****************************************************");
		System.out.println("---------------------------------------------------------------");
		System.out.println("USER NAME" + "           " + "MAIL ID" + "            " + "INVITE CODE");
		System.out.println("---------------------------------------------------------------");
		for (int i = 0; i < user_registration.size(); i++) {
			if (user_registration.get(i).getMail_id().equals(mailid)) {
				System.out.println(user_registration.get(i).getUser_name() + "      " + user_registration.get(i).getMail_id() + "         " + user_registration.get(i).getInvite_code());

			}
		}
		System.out.println("---------------------------------------------------------------");
		userMenu();

	}

	public void accountDetails() {
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("ACC_NUM" + "           " + "BANK NAME" + "            " + "IFSC CODE" + "      " + "DISPLAY NAME" + "        " + "UPI ID" + "          " + "BALANCE");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getMailid().equals(mailid)) {
				System.out.println(bank_details.get(i).getAcc_number() + "        " + bank_details.get(i).getBank_name() + "    " + bank_details.get(i).getIfsc_code() + "      " + bank_details.get(i).getDisplay_name() + "           " + bank_details.get(i).getUpi_id() + "        " + bank_details.get(i).getBalance());
			}
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

	}

}
