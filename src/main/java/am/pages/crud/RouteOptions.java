package am.pages.crud;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import am.Application;
import am.data.daos.LoginDAO;
import am.data.daos.RouteDAO;
import am.data.entites.Login;
import am.data.entites.Route;

class RouteOptionCreate extends CRUDOption<Route> {

	private static final long serialVersionUID = 1448715683393055636L;
	private JComboBox<Login> loginBox;

	public RouteOptionCreate() {
		super("Create Route", 5);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void create() {

		this.add(new JLabel("Login:"));
		List<Login> logins = new LoginDAO().readAll();
		loginBox = new JComboBox(logins.toArray());
		this.add(loginBox);

		JButton create = new JButton("Create");
		create.addActionListener(this);
		this.add(create);

		if (logins.size() == 0) {
			setOutput("Please create a Login First");
			create.setEnabled(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create")) {
			Login login = (Login) loginBox.getSelectedItem();
			new RouteDAO().create(new Route(login.getLogin_id()));
		}
		Application.setPanel(new RouteMenu());
	}

}

class RouteOptionReadAll extends CRUDOption<Route> {

	private static final long serialVersionUID = 7973420261651227122L;

	public RouteOptionReadAll() {
		super("Read All Routes", 1);
	}

	@Override
	public void create() {

		String output = " ";
		List<Route> routes = new RouteDAO().readAll();
		for (int i = 0; i < routes.size(); i++) {
			output += routes.get(i).toString();
			if (i + 1 < routes.size())
				output += " \n ";
		}

		JTextArea outputArea = new JTextArea(output);
		outputArea.setEditable(false);
		this.add(outputArea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.setPanel(new RouteMenu());
	}

}

class RouteOptionReadOne extends CRUDOption<Route> {

	private static final long serialVersionUID = -3766723895624840971L;
	private JTextField route_id;
	private JLabel response;

	private RouteDAO RouteDAO;

	public RouteOptionReadOne() {
		super("Read Route", 4);
		RouteDAO = new RouteDAO();
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		route_id = new JTextField(24);
		this.add(route_id);

		response = new JLabel("");
		this.add(response);

		JButton read = new JButton("Read");
		read.addActionListener(this);
		this.add(read);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Read")) {
			setOutput("");
			Long id = (long) 0;
			try {
				id = Long.parseLong(route_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				route_id.grabFocus();
				return;
			}
			Route read = RouteDAO.read(id);
			if (read == null) {
				response.setText("Null");
			} else {
				response.setText(read.toString());
				Application.repack();
			}
			return;
		}
		Application.setPanel(new RouteMenu());
	}

}

class RouteOptionUpdate extends CRUDOption<Route> {

	private static final long serialVersionUID = -8891367215027605177L;
	private JTextField route_id;
	private JComboBox<Login> loginBox;

	public RouteOptionUpdate() {
		super("Update Route", 4);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		route_id = new JTextField(24);
		this.add(route_id);

		List<Login> logins = new LoginDAO().readAll();
		loginBox = new JComboBox(logins.toArray());
		this.add(loginBox);

		JButton read = new JButton("Update");
		read.addActionListener(this);
		this.add(read);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Update")) {
			setOutput("");
			Long id = (long) 0;
			try {
				id = Long.parseLong(route_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				route_id.grabFocus();
				return;
			}

			RouteDAO routeDAO = new RouteDAO();

			Login login = (Login) loginBox.getSelectedItem();
			Route route = routeDAO.read(id);
			route.setLogin_id(login.getLogin_id());
			if (!routeDAO.update(route)) {
				setOutput("Route does not exist");
				return;
			}
		}
		Application.setPanel(new RouteMenu());
	}

}

class RouteOptionDelete extends CRUDOption<Route> {

	private static final long serialVersionUID = -8932944635148471362L;
	private JTextField route_id;

	public RouteOptionDelete() {
		super("Delete Route", 3);
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		route_id = new JTextField(24);
		this.add(route_id);

		JButton read = new JButton("Delete");
		read.addActionListener(this);
		this.add(read);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Delete")) {
			setOutput("");
			Long id = (long) 0;
			try {
				id = Long.parseLong(route_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				route_id.grabFocus();
				return;
			}
			if (!new RouteDAO().delete(id)) {
				setOutput("Route does not exist");
				return;
			}

		}
		Application.setPanel(new RouteMenu());
	}

}