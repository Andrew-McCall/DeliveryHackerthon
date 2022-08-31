package am.pages;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import am.Application;
import am.common.swing.Title;

public class DriverPage extends JPanel {

	private static final long serialVersionUID = 8278489080221641405L;

	public DriverPage() {
		this.add(new Title("Driver"));

		JButton logout = new JButton("LOGOUT");
		logout.addActionListener(e -> Application.setPanel(new LoginPage()));
		this.add(logout);

		this.setLayout(new GridLayout(2, 1, 12, 12));
	}

}
