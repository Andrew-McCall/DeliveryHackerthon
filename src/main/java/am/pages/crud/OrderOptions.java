package am.pages.crud;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import am.Application;
import am.data.daos.AddressDAO;
import am.data.daos.OrderDAO;
import am.data.entites.Address;
import am.data.entites.Order;

class OrderOptionCreate extends CRUDOption<Order> {

	private static final long serialVersionUID = -2076442809400233052L;
	private JTextField description;
	private JComboBox<Address> addressBox;

	public OrderOptionCreate() {
		super("Create Order", 4);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void create() {

		this.add(new JLabel("Description:"));
		description = new JTextField(24);
		this.add(description);

		List<Address> addresses = new AddressDAO().readAll();
		addressBox = new JComboBox(addresses.toArray());
		this.add(addressBox);

		JButton create = new JButton("Create");
		create.addActionListener(this);
		this.add(create);

		if (addresses.size() == 0) {
			setOutput("Please create an Address First");
			create.setEnabled(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create")) {
			if (description.getText().length() == 0) {
				setOutput("Missing Description");
				description.grabFocus();
				return;
			}

			Address addresss = (Address) addressBox.getSelectedItem();
			new OrderDAO().create(new Order(description.getText(), addresss.getAddress_id()));
		}
		Application.setPanel(new OrderMenu());
	}

}

class OrderOptionReadAll extends CRUDOption<Order> {

	private static final long serialVersionUID = 6568793864218143757L;

	public OrderOptionReadAll() {
		super("Read All Orders", 1);
	}

	@Override
	public void create() {

		String output = " ";
		List<Order> orders = new OrderDAO().readAll();
		for (int i = 0; i < orders.size(); i++) {
			output += orders.get(i).toString();
			if (i + 1 < orders.size())
				output += " \n ";
		}

		JTextArea outputArea = new JTextArea(output);
		outputArea.setEditable(false);
		this.add(outputArea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.setPanel(new OrderMenu());
	}

}

class OrderOptionReadOne extends CRUDOption<Order> {

	private static final long serialVersionUID = 4163430884132315160L;
	private JTextField order_id;
	private JLabel response;

	private OrderDAO OrderDAO;

	public OrderOptionReadOne() {
		super("Read Order", 4);
		OrderDAO = new OrderDAO();
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		order_id = new JTextField(24);
		this.add(order_id);

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
				id = Long.parseLong(order_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				order_id.grabFocus();
				return;
			}
			Order read = OrderDAO.read(id);
			if (read == null) {
				response.setText("Null");
			} else {
				response.setText(read.toString());
				Application.repack();
			}
			return;
		}
		Application.setPanel(new OrderMenu());
	}

}

class OrderOptionUpdate extends CRUDOption<Order> {

	private static final long serialVersionUID = 8345818936421961544L;
	private JTextField order_id;
	private JTextField description;
	private JComboBox<Address> addressBox;

	public OrderOptionUpdate() {
		super("Update Order", 6);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		order_id = new JTextField(24);
		this.add(order_id);

		this.add(new JLabel("Description:"));
		description = new JTextField(24);
		this.add(description);

		List<Address> addresses = new AddressDAO().readAll();
		addressBox = new JComboBox(addresses.toArray());
		this.add(addressBox);

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
				id = Long.parseLong(order_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				order_id.grabFocus();
				return;
			}

			if (description.getText().length() == 0) {
				setOutput("Missing Description");
				description.grabFocus();
				return;
			}

			Address addresss = (Address) addressBox.getSelectedItem();
			if (!new OrderDAO().update(new Order(id, description.getText(), addresss.getAddress_id()))) {
				setOutput("Order does not exist");
				return;
			}
		}
		Application.setPanel(new OrderMenu());
	}

}

class OrderOptionDelete extends CRUDOption<Order> {

	private static final long serialVersionUID = 1053077388343208395L;
	private JTextField order_id;

	public OrderOptionDelete() {
		super("Delete Order", 3);
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		order_id = new JTextField(24);
		this.add(order_id);

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
				id = Long.parseLong(order_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				order_id.grabFocus();
				return;
			}
			if (!new OrderDAO().delete(id)) {
				setOutput("Order does not exist");
				return;
			}

		}
		Application.setPanel(new OrderMenu());
	}

}