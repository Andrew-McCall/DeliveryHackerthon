package am.pages;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import am.Application;
import am.common.swing.Title;
import am.data.daos.LoginDAO;
import am.data.entites.Login;

public class LoginPage extends JPanel implements ActionListener {

	private static final long serialVersionUID = -6912981079379749091L;
	private LoginDAO loginDAO = new LoginDAO();
	private JTextField username = new JTextField(24);
	private JTextField password = new JTextField(24);
	private JLabel output;

	public LoginPage() {
		this.add(new Title("Login"));

		output = new JLabel("");
		output.setForeground(Color.RED);
		this.add(output);

		this.add(new JLabel("Username:"));
		this.add(username);

		this.add(new JLabel("Password:"));
		this.add(password);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		this.add(loginButton);

		this.setLayout(new GridLayout(7, 1, 12, 12));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		output.setText("");
		if (username.getText().length() == 0) {
			output.setText("Missing Username");
			return;
		}
		if (password.getText().length() == 0) {
			output.setText("Missing Password");
			return;
		}

		Login login = loginDAO.credentialCheck(new Login(username.getText(), password.getText()));

		if (login == null) {
			output.setText("Incorrect Username or Password");
			return;
		}

		Application.setCurrentUser(login);

		if (login.getIsManager()) {
			Application.setPanel(new ManagerPage());
		} else {
			Application.setPanel(new DriverPage());
		}

	}

}
