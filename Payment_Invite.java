//$Id$
package payment;

import java.text.ParseException;

public class Payment_Invite extends Payment_UserRegistration {
	public void toInvite() {
		System.out.println("**************************************************************");
		System.out.println("1.Show invitecode");
		System.out.println("2.view Rewards amount");
		System.out.println("3.Go Back");
		System.out.println("Enter your choice");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			toShowInviteCode();
			break;
		case 2:
			toViewRewardAmount();
			break;
		case 3:
			try {
				userMenu();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Please Check your input");
			toInvite();
		}
	}

	public void toShowInviteCode() {

		System.out.println("********************************************************************");
		for (int i = 0; i < user_registration.size(); i++) {
			if (user_registration.get(i).getMail_id().equals(mailid)) {
				System.out.println("Your Referrel code is " + user_registration.get(i).getInvite_code());
			}

		}
		try {
			userMenu();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void toViewRewardAmount() {

		System.out.println("********************************************************************");
		int reward_amt = 0;
		for (int i = 0; i < bank_details.size(); i++) {
			if (bank_details.get(i).getMailid().equals(mailid)) {
				reward_amt = reward_amt + bank_details.get(i).getReward();
			}

		}
		System.out.println("Your total Reward amount is" + reward_amt);
		try {
			userMenu();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
