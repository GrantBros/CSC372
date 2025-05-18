package Bank;

public class CheckingAccount extends BankAccount{
	private double interestRate;

	public CheckingAccount() {
		//constructor
		super();
	}

public CheckingAccount(String fname, String lname, int accountID, double interestRate) {
	super();
	set("firstname", fname);
	set("lastname", lname);
	set("accountid",accountID);
	set("balance", 0.0);
	this.interestRate = interestRate;
	}
public void set(String att, Object val) {
	try {
		if (att.equalsIgnoreCase("interestrate")) {
			this.interestRate = (Double) val;
		} else {
			super.set(att, val);
		}
	} catch (ClassCastException e) {
		System.out.println("Invalid entry criteria. " + att + " is not the appropriate data type.");
	} catch (Exception e) {
		System.out.println("There was an error processing the request: " + e.getMessage());
		}
	}
public Object get(String att) {
	try {
		if (att.equalsIgnoreCase("interestrate")) {
			return interestRate;
			} else {
				return super.get(att);
			}
		} catch (Exception e) {
			System.out.println("Error retrieving request: " + e.getMessage());
			return null;
		}
	}
public void processWithdrawal (double wval) {
	if (wval <= 0) {
		System.out.println("You may only withdraw positive values.");
		return;
	}
	double balance = (double) get("balance");
	
	if (balance >= wval) {
		withdrawal(wval);
	} else {
		double newbalance = balance - wval - 30;
		System.out.println("The balance is now processing an overdraft withdrawal. A fee of $30 will be applied.");
		
		try {
			set("balance", newbalance);
		} catch (Exception e) {
			System.out.println("Error processing. Unable to process withdrawal");
			}
		}	
	}
public void displayAccount() {
	accountSummary();
	System.out.printf("The current Interest Rate is %.2f%%%n", interestRate);
	
	}
}
