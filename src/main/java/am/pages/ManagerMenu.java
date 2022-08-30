package am.pages;

import javax.swing.JButton;
import javax.swing.JPanel;

import am.Application;
import am.pages.crud.LoginMenu;

public class ManagerMenu extends JPanel {

	private static final long serialVersionUID = -5848887882652905033L;

	public ManagerMenu() {

		JButton login = new JButton("Login");
		login.addActionListener(e -> Application.setPanel(new LoginMenu()));
		this.add(login);

	}
}
