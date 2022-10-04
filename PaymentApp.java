//$Id$
package payment;

import java.text.ParseException;

public class PaymentApp {
	public static void main(String args[]) throws ParseException
	{
		Payment_UserRegistration payment=new Payment_UserRegistration();
		payment.addUser();
	}

}
