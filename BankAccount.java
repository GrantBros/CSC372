package Bank;

public class BankAccount {
	private String fname = "";
	private String lname = "";
	private int accountID = 0;
	private double balance = 0;
	
	public BankAccount() {
		this.balance = 0;
	}
	public void deposit(double dval) {
		if (dval > 0) {
			balance += dval;
		}
	}
	public Object get(String attname) {
		//Must use Object type for varied data type inputs
		try {
		switch (attname.toLowerCase()) {
		case "firstname":
			return fname;
		case "lastname":
			return lname;
		case "accountid":
			return accountID;
			default:
				throw new IllegalArgumentException("Invlaid retrival criteria" + attname + 
						"is not a known account label.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
				return null;
		}
	}
	public void set(String att, Object val) {
		try { 
			switch (att.toLowerCase()) {
			//toLowerCase removes non-alphabetical characters so no space needed for case arguments
			//Must use Object type for val due to different data types being possible for 
			//all different attributed to be included in one method
			case "firstname":
				this.fname = (String) val;
				break;
			case "lastname":
				this.lname = (String) (val);
				break;
			case "accountid":
				this.accountID = (Integer) val;
				break;
			case "balance":
				this.balance = (Double) val;
				break;
			default:
				System.out.println("Unknown value entered: " + att + ".");
			}
		} catch (ClassCastException e) {
			System.out.println("Invalid data type for the entered value " + val + ".");
		} catch (Exception e) {
			System.out.println("Error messgae: " + e.getMessage());
		}
	}
	public void withdrawal(double wval) {
		//withdrawal checks against balance to subtract withdrawal value from balance
		if (wval > 0 && wval <= balance) {
			balance -= wval;
		} else {
				System.out.println("Balance is too low to make that withdrawal.");
			}
		}
	public void accountSummary() {
		System.out.println("Account Information:");
		System.out.println("Account holder: " + fname + " " + lname);
		System.out.println("Account ID: " + accountID);
		System.out.printf("Current Balance: $%.2f%n", balance);
	}
}

