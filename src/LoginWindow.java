import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginWindow {
	private JFrame frame;

	private String enteredUsername;
	private String enteredPassword;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public void launchWindow() {
		int numTriesLeft = 3;
		boolean done = false;

		do {
			if (numTriesLeft > 3){
				JOptionPane.showMessageDialog(null, "Number of Attempts Exceeded!", "Reception", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			UserDB user = new UserDB();
			
			// Create and initialize the text fields for the user login box
			usernameField = new JTextField();
			passwordField = new JPasswordField();

			/* This array of Object objects stores both a labels (as strings) and text fields
			 * that will go into the dialog box */
			Object[] message = {
					"Username:", usernameField,
					"Password:", passwordField			
			};

			// dialog box that will include the registration form
			JOptionPane.showConfirmDialog(null, message, "Select an Option", JOptionPane.OK_CANCEL_OPTION);
			
			enteredUsername = usernameField.getText();
			enteredPassword = new String(passwordField.getPassword());

			
			if(enteredUsername == null || enteredPassword == null){
				System.exit(0);
			}


			

			if (user.checkUser(enteredUsername,enteredPassword))
			{
				if (user.isAdmin()) {
					frame = new UIAdministrativeFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} else {
					frame = new UICustomerViewFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
				break;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Incorrect password!!!", "Reception", JOptionPane.ERROR_MESSAGE);
				System.out.println(passwordField.getPassword());
				numTriesLeft--;
			}
		} while (!done);

	}
	
	
}
