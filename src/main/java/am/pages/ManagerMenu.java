package am.pages;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import am.Application;
import am.common.swing.Title;
import am.pages.crud.AddressMenu;
import am.pages.crud.LoginMenu;

public class ManagerMenu extends JPanel {

	private static final long serialVersionUID = -5848887882652905033L;

	public ManagerMenu() {

		this.add(new Title("Manager Menu"));

		JButton login = new JButton("Login CRUD");
		login.addActionListener(e -> Application.setPanel(new LoginMenu()));
		this.add(login);

		JButton address = new JButton("Address CRUD");
		address.addActionListener(e -> Application.setPanel(new AddressMenu()));
		this.add(address);

		this.setLayout(new GridLayout(3, 1, 12, 12));
	}
}
