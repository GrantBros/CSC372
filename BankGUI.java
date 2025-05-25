package Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * This GUI is built initially to allow for static use of an independent interface for Bank transactions.
 * Future development is intended to incorporate the BankAccount and CheckingAccount classes. 
 * It should eventually replace the main class as the driver for running the program.
 * The initial balance is statically built and used as a while condition to set that balance.
 *  Will be replaced with actions for creating a new bank account.
 * having the parameter "this" in the listeners means that the current class is affected by the GUI.
 * (thoughts about having a button to affect a separate class, i.e. change a checking account balance)
 */
public class BankGUI extends JFrame implements ActionListener {

	    private double balance = 0.0;

	    private JLabel balanceLabel;
	    private JTextField amountField;
	    private JButton checkBalanceButton, depositButton, withdrawButton, exitButton;
	    private JPanel panel;

	    public BankGUI() {
	        setTitle("Bank Balance Application");
	        setSize(400, 200);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        	while (true) {
	        		String input = JOptionPane.showInputDialog(this, "Enter the Initial balance: ", 
	        				"Initial Balance", JOptionPane.QUESTION_MESSAGE);
	        		if (input == null) {
	        			JOptionPane.showMessageDialog(this,  "Application will now exit.");
	        			System.exit(0);
	        		}
	        		try {
	        			balance = Double.parseDouble(input);
	        			if (balance < 0) throw new NumberFormatException();
	        			break;
	        		} catch (NumberFormatException e) {
	        			JOptionPane.showMessageDialog(this,  "please enter a valid non-negative"
	        					+ " number for the initial balance.");
	        		}
	        	}

	        panel = new JPanel();
	        panel.setLayout(new GridLayout(5, 2, 10, 10));

	        balanceLabel = new JLabel("Balance: $0.00");
	        amountField = new JTextField(10);

	        checkBalanceButton = new JButton("Check Balance");
	        depositButton = new JButton("Deposit");
	        withdrawButton = new JButton("Withdraw");
	        exitButton = new JButton("Exit");

	        // Add action listeners
	        checkBalanceButton.addActionListener(this);
	        depositButton.addActionListener(this);
	        withdrawButton.addActionListener(this);
	        exitButton.addActionListener(this);

	        // Add components to panel
	        panel.add(new JLabel("Enter amount:"));
	        panel.add(amountField);
	        panel.add(depositButton);
	        panel.add(withdrawButton);
	        panel.add(checkBalanceButton);
	        panel.add(exitButton);
	        panel.add(new JLabel("")); // filler
	        panel.add(balanceLabel);

	        add(panel);
	        setVisible(true);
	    }
/*
 * 
 * Lots of if-elses with try-catches to mitigate user input errors and perform specific actions based on button clicked via Object.getSource() methods.
 * invokeLater is used to keep entries sequential without possibly overwriting or causing deadlocks due to bad queuing.
 * 
 */
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String input = amountField.getText();
	        double amount = 0;

	        try {
	            if (!input.isEmpty()) {
	                amount = Double.parseDouble(input);
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
	            return;
	        }

	        if (e.getSource() == checkBalanceButton) {
	            balanceLabel.setText(String.format("Balance: $%.2f", balance));
	        } else if (e.getSource() == depositButton) {
	            if (amount > 0) {
	                balance += amount;
	                balanceLabel.setText(String.format("Deposited $%.2f", amount));
	            } else {
	                JOptionPane.showMessageDialog(this, "Enter a positive amount to deposit.");
	            }
	        } else if (e.getSource() == withdrawButton) {
	            if (amount > 0 && amount <= balance) {
	                balance -= amount;
	                balanceLabel.setText(String.format("Withdrew $%.2f", amount));
	            } else {
	                JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount.");
	            }
	        } else if (e.getSource() == exitButton) {
	            JOptionPane.showMessageDialog(this, String.format("Final balance: $%.2f", balance));
	            System.exit(0);
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new BankGUI());
	    }
	}