package am.pages.crud;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import am.Application;
import am.common.swing.TickBox;
import am.data.daos.LoginDAO;
import am.data.entites.Login;

class LoginOptionCreate extends CRUDOption<Login> {

	private static final long serialVersionUID = 9137282126778111696L;

	private JTextField username;
	private JTextField password;
	private JTextField name;
	private TickBox manager;

	public LoginOptionCreate() {
		super("Create Login", 8);
	}

	@Override
	public void create() {

		this.add(new JLabel("Username:"));
		username = new JTextField(24);
		this.add(username);

		this.add(new JLabel("Password:"));
		password = new JTextField(24);
		this.add(password);

		this.add(new JLabel("Name:"));
		name = new JTextField(24);
		this.add(name);

		manager = new TickBox("Manager?");
		this.add(manager);

		JButton create = new JButton("Create");
		create.addActionListener(this);
		this.add(create);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create")) {
			if (username.getText().length() == 0) {
				setOutput("Missing Username");
				username.grabFocus();
				return;
			}
			if (password.getText().length() == 0) {
				setOutput("Missing Password");
				password.grabFocus();
				return;
			}
			if (name.getText().length() == 0) {
				setOutput("Missing Name");
				name.grabFocus();
				return;
			}
			new LoginDAO()
					.create(new Login(username.getText(), password.getText(), name.getText(), manager.isChecked()));
		}
		Application.setPanel(new LoginMenu());
	}

}

class LoginOptionReadAll extends CRUDOption<Login> {

	private static final long serialVersionUID = -2106076781285195145L;

	public LoginOptionReadAll() {
		super("Read All Logins", 1);
	}

	@Override
	public void create() {

		String output = " ";
		List<Login> logins = new LoginDAO().readAll();
		for (int i = 0; i < logins.size(); i++) {
			output += logins.get(i).toString();
			if (i + 1 < logins.size())
				output += " \n ";
		}

		JTextArea outputArea = new JTextArea(output);
		outputArea.setEditable(false);
		this.add(outputArea);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.setPanel(new LoginMenu());
	}

}

class LoginOptionReadOne extends CRUDOption<Login> {

	private static final long serialVersionUID = -4456311836942685454L;
	private JTextField login_id;
	private JLabel response;

	private LoginDAO loginDAO;

	public LoginOptionReadOne() {
		super("Read Login", 4);
		loginDAO = new LoginDAO();
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		login_id = new JTextField(24);
		this.add(login_id);

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
				id = Long.parseLong(login_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				login_id.grabFocus();
				return;
			}
			Login read = loginDAO.read(id);
			if (read == null) {
				response.setText("Null");
			} else {
				response.setText(read.toString());
				Application.repack();
			}
			return;
		}
		Application.setPanel(new LoginMenu());
	}

}

class LoginOptionUpdate extends CRUDOption<Login> {

	private static final long serialVersionUID = 9001020055950930664L;
	private JTextField login_id;
	private TickBox manager;
	private JTextField username;
	private JTextField password;
	private JTextField name;

	public LoginOptionUpdate() {
		super("Update Login", 10);
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		login_id = new JTextField(24);
		this.add(login_id);

		this.add(new JLabel("Username:"));
		username = new JTextField(24);
		this.add(username);

		this.add(new JLabel("Password:"));
		password = new JTextField(24);
		this.add(password);

		this.add(new JLabel("Name:"));
		name = new JTextField(24);
		this.add(name);

		manager = new TickBox("Manager?");
		this.add(manager);

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
				id = Long.parseLong(login_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				login_id.grabFocus();
				return;
			}
			if (username.getText().length() == 0) {
				setOutput("Missing Username");
				username.grabFocus();
				return;
			}
			if (password.getText().length() == 0) {
				setOutput("Missing Password");
				password.grabFocus();
				return;
			}
			if (name.getText().length() == 0) {
				setOutput("Missing Name");
				name.grabFocus();
				return;
			}
			Login login = new Login(id, username.getText(), null, name.getText(), manager.isChecked());
			login.setPassword(password.getText()); // Hash
			if (!new LoginDAO().update(login)) {
				setOutput("Login does not exist");
				return;
			}
		}
		Application.setPanel(new LoginMenu());
	}

}

class LoginOptionDelete extends CRUDOption<Login> {

	private static final long serialVersionUID = -8690498038032389738L;
	private JTextField login_id;

	public LoginOptionDelete() {
		super("Delete Login", 3);
	}

	@Override
	public void create() {

		this.add(new JLabel("Id:"));
		login_id = new JTextField(24);
		this.add(login_id);

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
				id = Long.parseLong(login_id.getText());
			} catch (NumberFormatException error) {
				setOutput("Please enter a vaild number");
				login_id.grabFocus();
				return;
			}
			if (!new LoginDAO().delete(id)) {
				setOutput("Login does not exist");
				return;
			}

		}
		Application.setPanel(new LoginMenu());
	}

}