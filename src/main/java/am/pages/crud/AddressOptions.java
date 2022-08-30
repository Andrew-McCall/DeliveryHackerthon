package am.pages.crud;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import am.Application;
import am.data.daos.AddressDAO;
import am.data.entites.Address;

class AddressOptionCreate extends CRUDOption<Address> {

	private static final long serialVersionUID = -1067714785178650578L;

	private JTextField address_line_one;
	private JTextField address_line_two;
	private JTextField city;
	private JTextField postcode;

	public AddressOptionCreate() {
		super("Create Address", 9);
	}

	@Override
	public void create() {

		this.add(new JLabel("Address Line One:"));
		address_line_one = new JTextField(24);
		this.add(address_line_one);

		this.add(new JLabel("Address Line One:"));
		address_line_two = new JTextField(24);
		this.add(address_line_two);

		this.add(new JLabel("City:"));
		city = new JTextField(24);
		this.add(city);

		this.add(new JLabel("Postcode:"));
		postcode = new JTextField(8);
		this.add(postcode);

		JButton create = new JButton("Create");
		create.addActionListener(this);
		this.add(create);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create")) {
			if (address_line_one.getText().length() == 0) {
				setOutput("Missing Address Line One");
				address_line_one.grabFocus();
				return;
			}
			if (address_line_two.getText().length() == 0) {
				setOutput("Missing Address Line Two");
				address_line_two.grabFocus();
				return;
			}
			if (city.getText().length() == 0) {
				setOutput("Missing City");
				city.grabFocus();
				return;
			}
			if (postcode.getText().length() == 0) {
				setOutput("Missing Postcode");
				postcode.grabFocus();
				return;
			}
			if (postcode.getText().length() > 8) {
				setOutput("Invaild Postcode");
				postcode.grabFocus();
				return;
			}
			new AddressDAO().create(new Address(address_line_one.getText(), address_line_two.getText(),
					postcode.getText(), postcode.getText()));
		}
		Application.setPanel(new AddressMenu());
	}

}

class AddressOptionReadAll extends CRUDOption<Address> {

	private static final long serialVersionUID = 847574099153405447L;

	public AddressOptionReadAll() {
		super("Read All Addresses", 1);
	}

	@Override
	public void create() {

		String output = " ";
		List<Address> addresss = new AddressDAO().readAll();
		for (int i = 0; i < addresss.size(); i++) {
			output += addresss.get(i).toString();
			if (i + 1 < addresss.size())
				output += " \n ";
		}

		JTextArea outputArea = new JTextArea(output);
		outputArea.setEditable(false);
		this.add(outputArea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.setPanel(new AddressMenu());
	}

}

class AddressOptionReadOne extends CRUDOption<Address> {

	private static final long serialVersionUID = -5835954606729670933L;
	private JTextField address_id;
	private JLabel response;

	private AddressDAO AddressDAO;

	public AddressOptionReadOne() {
		super("Read Address", 4);
		AddressDAO = new AddressDAO();
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		address_id = new JTextField(24);
		this.add(address_id);

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
				id = Long.parseLong(address_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				address_id.grabFocus();
				return;
			}
			Address read = AddressDAO.read(id);
			if (read == null) {
				response.setText("Null");
			} else {
				response.setText(read.toString());
				Application.repack();
			}
			return;
		}
		Application.setPanel(new AddressMenu());
	}

}

class AddressOptionUpdate extends CRUDOption<Address> {

	private static final long serialVersionUID = 9001020055950930664L;
	private JTextField address_id;
	private JTextField address_line_one;
	private JTextField address_line_two;
	private JTextField city;
	private JTextField postcode;

	public AddressOptionUpdate() {
		super("Update Address", 11);
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		address_id = new JTextField(24);
		this.add(address_id);

		this.add(new JLabel("Address Line One:"));
		address_line_one = new JTextField(24);
		this.add(address_line_one);

		this.add(new JLabel("Address Line One:"));
		address_line_two = new JTextField(24);
		this.add(address_line_two);

		this.add(new JLabel("City:"));
		city = new JTextField(24);
		this.add(city);

		this.add(new JLabel("Postcode:"));
		postcode = new JTextField(8);
		this.add(postcode);

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
				id = Long.parseLong(address_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				address_id.grabFocus();
				return;
			}
			if (address_line_one.getText().length() == 0) {
				setOutput("Missing Address Line One");
				address_line_one.grabFocus();
				return;
			}
			if (address_line_two.getText().length() == 0) {
				setOutput("Missing Address Line Two");
				address_line_two.grabFocus();
				return;
			}
			if (city.getText().length() == 0) {
				setOutput("Missing City");
				city.grabFocus();
				return;
			}
			if (postcode.getText().length() == 0) {
				setOutput("Missing Postcode");
				postcode.grabFocus();
				return;
			}
			if (postcode.getText().length() > 8) {
				setOutput("Invaild Postcode");
				postcode.grabFocus();
				return;
			}
			if (!new AddressDAO().update(new Address(id, address_line_one.getText(), address_line_two.getText(),
					postcode.getText(), postcode.getText()))) {
				setOutput("Address does not exist");
				return;
			}
		}
		Application.setPanel(new AddressMenu());
	}

}

class AddressOptionDelete extends CRUDOption<Address> {

	private static final long serialVersionUID = -5652569231626301350L;
	private JTextField address_id;

	public AddressOptionDelete() {
		super("Delete Address", 3);
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		address_id = new JTextField(24);
		this.add(address_id);

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
				id = Long.parseLong(address_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				address_id.grabFocus();
				return;
			}
			if (!new AddressDAO().delete(id)) {
				setOutput("Address does not exist");
				return;
			}

		}
		Application.setPanel(new AddressMenu());
	}

}