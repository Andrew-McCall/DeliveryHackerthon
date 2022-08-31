package am.pages.crud;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import am.Application;
import am.data.daos.OrderDAO;
import am.data.daos.RouteDAO;
import am.data.daos.RoutestopDAO;
import am.data.entites.Order;
import am.data.entites.Route;
import am.data.entites.Routestop;

class RoutestopOptionCreate extends CRUDOption<Routestop> {

	private static final long serialVersionUID = 2136835610502283849L;
	private JComboBox<Order> orderBox;
	private JComboBox<Route> routeBox;

	public RoutestopOptionCreate() {
		super("Create Routestop", 5);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void create() {

		this.add(new JLabel("Route:"));
		List<Route> routes = new RouteDAO().readAll();
		routeBox = new JComboBox(routes.toArray());
		this.add(routeBox);

		this.add(new JLabel("Order:"));
		List<Order> orders = new OrderDAO().readAll();
		orderBox = new JComboBox(orders.toArray());
		this.add(orderBox);

		JButton create = new JButton("Create");
		create.addActionListener(this);
		this.add(create);

		if (routes.size() == 0) {
			setOutput("Please create a Route First");
			create.setEnabled(false);
		} else if (orders.size() == 0) {
			setOutput("Please create an Order First");
			create.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create")) {
			Order order = (Order) orderBox.getSelectedItem();
			Route route = (Route) routeBox.getSelectedItem();
			new RoutestopDAO().create(new Routestop(route.getRoute_id(), -1, order.getOrder_id()));
		}
		Application.setPanel(new RoutestopMenu());
	}

}

class RoutestopOptionReadAll extends CRUDOption<Routestop> {

	private static final long serialVersionUID = 5724064534226609720L;

	public RoutestopOptionReadAll() {
		super("Read All Routestops", 1);
	}

	@Override
	public void create() {

		String output = " ";
		List<Routestop> routestops = new RoutestopDAO().readAll();
		for (int i = 0; i < routestops.size(); i++) {
			output += routestops.get(i).toString();
			if (i + 1 < routestops.size())
				output += " \n ";
		}

		JTextArea outputArea = new JTextArea(output);
		outputArea.setEditable(false);
		this.add(outputArea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.setPanel(new RoutestopMenu());
	}

}

class RoutestopOptionReadOne extends CRUDOption<Routestop> {

	private static final long serialVersionUID = 214797071803273016L;
	private JTextField routestop_id;
	private JLabel response;

	private RoutestopDAO RoutestopDAO;

	public RoutestopOptionReadOne() {
		super("Read Routestop", 4);
		RoutestopDAO = new RoutestopDAO();
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		routestop_id = new JTextField(24);
		this.add(routestop_id);

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
				id = Long.parseLong(routestop_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				routestop_id.grabFocus();
				return;
			}
			Routestop read = RoutestopDAO.read(id);
			if (read == null) {
				response.setText("Null");
			} else {
				response.setText(read.toString());
				Application.repack();
			}
			return;
		}
		Application.setPanel(new RoutestopMenu());
	}

}

class RoutestopOptionUpdate extends CRUDOption<Routestop> {

	private static final long serialVersionUID = 7516357123193978431L;
	private JTextField routestop_id;
	private JTextField stop_number;
	private JComboBox<Route> routeBox;
	private JComboBox<Order> orderBox;

	public RoutestopOptionUpdate() {
		super("Update Routestop", 9);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		routestop_id = new JTextField(24);
		this.add(routestop_id);

		this.add(new JLabel("Route:"));
		List<Route> routes = new RouteDAO().readAll();
		routeBox = new JComboBox(routes.toArray());
		this.add(routeBox);

		this.add(new JLabel("Stop Number:"));
		stop_number = new JTextField(24);
		this.add(stop_number);

		this.add(new JLabel("Order:"));
		List<Order> orders = new OrderDAO().readAll();
		orderBox = new JComboBox(orders.toArray());
		this.add(orderBox);

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
				id = Long.parseLong(routestop_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				routestop_id.grabFocus();
				return;
			}

			int stop_numberValue = (int) 0;
			try {
				stop_numberValue = Integer.parseInt(stop_number.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				stop_number.grabFocus();
				return;
			}

			Order order = (Order) orderBox.getSelectedItem();
			Route route = (Route) routeBox.getSelectedItem();

			if (!new RoutestopDAO()
					.update(new Routestop(id, route.getRoute_id(), stop_numberValue, order.getOrder_id()))) {
				setOutput("Routestop does not exist");
				return;
			}
		}
		Application.setPanel(new RoutestopMenu());
	}

}

class RoutestopOptionDelete extends CRUDOption<Routestop> {

	private static final long serialVersionUID = -9128838389954398543L;
	private JTextField routestop_id;

	public RoutestopOptionDelete() {
		super("Delete Routestop", 3);
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		routestop_id = new JTextField(24);
		this.add(routestop_id);

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
				id = Long.parseLong(routestop_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				routestop_id.grabFocus();
				return;
			}
			if (!new RoutestopDAO().delete(id)) {
				setOutput("Routestop does not exist");
				return;
			}

		}
		Application.setPanel(new RoutestopMenu());
	}

}