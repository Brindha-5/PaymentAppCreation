package payment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Payment_UserRegistration extends Payment_BankDetails{
	public static String mailid=null;
	Scanner input=new Scanner(System.in);
	public static ArrayList<UserDetails> user_registration=new ArrayList<>();
	int codenum=1;
	
	public void welcomePage() throws ParseException
	{
		
	 System.out.println("******* Welcome to ZOHO Payment App  ********");
		System.out.println("1.Register");
		System.out.println("2.Login");
		System.out.println("Enter your choice");
		int choice=input.nextInt();
		switch(choice)
		{
		case 1:
			userRegistration();
			
			break;
		case 2:
			userLogin();
			break;
		default:
			System.out.println("Enter valid choice");
			}
	}
	
	 boolean passWordVerification(String password) {
		if (password == null) {
	        return false;
	    }
		int count=0;
		String testPassword = password.toLowerCase();
		for(int i=0;i<testPassword.length();i++) {
			count=0;
			char index = testPassword.charAt(i);
			for(int j=0;j<testPassword.length();j++) {
				if(i==j) {
					continue;
				}
				char indexNext = testPassword.charAt(j);
				if(index==indexNext) {
					count++;
				}
				if(count >3) {
					return false;
				}
			}
		}
		String regex = "^(?=.*[0-9])"
	            + "(?=.*[A-Z])"
	            + "(?=.*[@#$%^&+=])"
	            + ".{8,}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		return m.matches();
	}
	 
	 public void addUser() throws ParseException
	 {
		 user_registration.add(new UserDetails("brindha","br@gmail.com","kio*7Yjkl","br01"));
		 user_registration.add(new UserDetails("akila","ak@gmail.com","pio*7Yjkl","ak02"));
		 user_registration.add(new UserDetails("pooja","po@gmail.com","uio*7Yjyj","po02"));
		 user_registration.add(new UserDetails("elakkiya","el@gmail.com","uio*7uytkl","el05"));
		 user_registration.add(new UserDetails("sreeja","sr@gmail.com","uio*7Yjklop","sr08"));
		 bank_details.add(new BankDetails("br@gmail.com","abc123","abc","canarabank","bindhu","bindhu@canarabank",5000,0));
		bank_details.add(new BankDetails("br@gmail.com","xyz123","xyz","canarabank","saba","saba@canarabank",5000,0));
		bank_details.add(new BankDetails("ak@gmail.com","ghj567","ghj","icicibank","aki","aki@icicibank",10000,0));
		bank_details.add(new BankDetails("po@gmail.com","ads555","ads","Indianbank","poo","poo@Indianbank",6000,0));
		 bank_details.add(new BankDetails("el@gmail.com","uyw243","uyj","LVbank","ela","ela@LVbank",2000,0));
		 bank_details.add(new BankDetails("sr@gmail.com","jkl999","jkl","Mahindrabank","sree","sree@Mahindrabank",7000,0));
		 transaction_log.add(new TransactionLogs("br@gmail.com","abc123","pay via BankAccount number",200,"22/08/27 09:29:58"));
		transaction_log.add(new TransactionLogs("ak@gmail.com","ghj567","pay via BankAccount number",500,"22/09/23 09:29:58"));
		 transaction_log.add(new TransactionLogs("br@gmail.com","abc123","Deposit",700,"22/09/25 09:29:58"));
		transaction_log.add(new TransactionLogs("br@gmail.com","abc123","pay via UPI_ID",200,"22/08/25 09:29:58"));
		
	     welcomePage();
		 
	 }
	 public void userRegistration()
	 {
		
 String name,mail_id,referral_code,invite_code,password,ref_mailid=null;
		
			int ref_option;
		boolean reg=true;
		 System.out.println("Enter Your Name");
			name=input.next();
			System.out.println("Enter your mail id");
			mail_id=input.next();
			for(int i=0;i<user_registration.size();i++)
			{
				if(user_registration.get(i).getMail_id().equals(mail_id))
				{
					System.out.println("Your account is already Registered..Please login");
					reg=false;
				}
			}
			if(reg)
			{
				System.out.println("Do you have any referral code if yes press 1,no press 0");
			ref_option=input.nextInt();
			if(ref_option==1)
			{
				System.out.println("Enter Referral code");
				referral_code=input.next();
				boolean ref_flag=false;
				for (UserDetails element : user_registration) {
					if(element.getInvite_code().equals(referral_code))
					{
						ref_flag=true;
						ref_mailid=element.getMail_id();
					}
					if(ref_flag)
					{
						System.out.println("hi");
						 for(BankDetails element1 :bank_details)
						 {
							 System.out.println("hii");
							 if(element1.getMailid().equals(ref_mailid))
							 {
								 System.out.println("hiii");
								double amt=element1.getBalance();
								int ref_amt=element1.getReward();
								element1.setReward(ref_amt+50);
								element1.setBalance(amt+50);
								break;
								 
							 }
							 }
						 }
						 break;
					}
				}
			}
				
			
			if(reg)
			{
			System.out.println("Enter your password");
			password=input.next();
			
			boolean valid=passWordVerification(password);
			if(valid)
			{
			 String substring = name.substring(2);
				invite_code=substring+codenum++;
				user_registration.add(new UserDetails(name,mail_id,password,invite_code));
				System.out.println("User Registration is successfully Completed");
				
		 }
			else
			{
				System.out.println("Your Password Strength is weak...please try again");
				userRegistration();
			}
			}
			System.out.println("Do you want to continue 1-yes/0-No");
			int option_continue=input.nextInt();
			if(option_continue==1)
			{
				try {
					welcomePage();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	 public void userLogin() throws ParseException
	 {
		 
		 String password;
		 boolean login=true;
		 System.out.println("*******Login Page********");
		 System.out.println("Enter Email_Id");
		 mailid=input.next();
		 System.out.println("Enteryour password");
		 password=input.next();
		 
		 for(int i=0;i<user_registration.size();i++)
			{
				if(user_registration.get(i).getMail_id().equals(mailid)&&user_registration.get(i).getPassword().equals(password))
				{
					System.out.println("You are Logged in Successfully");
					login=false;
					userMenu();
					
				}
				
		 }
		 if (login)
		 {
			 System.out.println("Please check your mailid & password or kindly register");
			 System.out.println("Do you want to continue 1-yes/0-No");
				int option_continue=input.nextInt();
				if(option_continue==1)
				{
					welcomePage();
					
				}
		 }
		
	 }
	 
}
	 
	 