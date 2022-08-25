package am.pages;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import am.common.Title;
import am.root.Application;

public class LoginPage extends JPanel {

	private static final long serialVersionUID = -4998086629168265669L;
	private JTextField username = new JTextField(32);
	private JTextField password = new JTextField(32);

	public LoginPage() {
		this.add(new Title("Login"));

		this.add(new JLabel("Username:"));
		this.add(username);

		this.add(new JLabel("Password:"));
		this.add(password);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(e -> Application.setPanel(new ManagerMenu()));
		this.add(loginButton);

		this.setLayout(new GridLayout(6, 1, 12, 12));
	}

}
