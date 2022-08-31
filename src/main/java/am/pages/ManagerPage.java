package am.pages;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import am.Application;
import am.common.swing.Title;
import am.pages.crud.AddressMenu;
import am.pages.crud.LoginMenu;
import am.pages.crud.OrderMenu;
import am.pages.crud.RouteMenu;
import am.pages.crud.RoutestopMenu;

public class ManagerPage extends JPanel {

	private static final long serialVersionUID = -5848887882652905033L;

	public ManagerPage() {

		this.add(new Title("Manager Menu"));

		JButton login = new JButton("Login CRUD");
		login.addActionListener(e -> Application.setPanel(new LoginMenu()));
		this.add(login);

		JButton address = new JButton("Address CRUD");
		address.addActionListener(e -> Application.setPanel(new AddressMenu()));
		this.add(address);

		JButton order = new JButton("Order CRUD");
		order.addActionListener(e -> Application.setPanel(new OrderMenu()));
		this.add(order);

		JButton route = new JButton("Route CRUD");
		route.addActionListener(e -> Application.setPanel(new RouteMenu()));
		this.add(route);

		JButton routestop = new JButton("Routestop CRUD");
		routestop.addActionListener(e -> Application.setPanel(new RoutestopMenu()));
		this.add(routestop);

		JButton logout = new JButton("LOGOUT");
		logout.addActionListener(e -> Application.setPanel(new LoginPage()));
		this.add(logout);

		this.setLayout(new GridLayout(7, 1, 12, 12));

	}
}
