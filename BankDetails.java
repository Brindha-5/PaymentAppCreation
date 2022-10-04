//$Id$
package payment;

import java.io.Serializable;

public class BankDetails implements Serializable{
	private String mailid;
	private String acc_number;
	private String ifsc_code;
	private String bank_name;
	private String display_name;
	private String upi_id;
	private int reward;
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	private double balance;
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
	}
	public BankDetails(String mailid, String acc_number, String ifsc_code, String bank_name, String display_name, String upi_id, double balance,int reward) {
		super();
		this.mailid = mailid;
		this.acc_number = acc_number;
		this.ifsc_code = ifsc_code;
		this.bank_name = bank_name;
		this.display_name = display_name;
		this.upi_id = upi_id;
		this.balance = balance;
		this.reward=reward;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getUpi_id() {
		return upi_id;
	}
	public void setUpi_id(String upi_id) {
		this.upi_id = upi_id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}

