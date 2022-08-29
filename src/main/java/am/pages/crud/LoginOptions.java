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
	private TickBox manager;

	public LoginOptionCreate() {
		super("Create", 6);
	}

	@Override
	public void create() {

		this.add(new JLabel("Username:"));
		username = new JTextField(24);
		this.add(username);

		this.add(new JLabel("Password:"));
		password = new JTextField(24);
		this.add(password);

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
			new LoginDAO().create(new Login(null, username.getText(), password.getText(), manager.isChecked()));
		}
		Application.setPanel(new LoginMenu());
	}

}

class LoginOptionReadAll extends CRUDOption<Login> {

	private static final long serialVersionUID = -2106076781285195145L;

	public LoginOptionReadAll() {
		super("Read All", 1);
	}

	@Override
	public void create() {

		String output = "";
		List<Login> logins = new LoginDAO().readAll();
		output += logins.get(0).toString();
		for (int i = 1; i < logins.size(); i++) {
			output += "\n";
			output += logins.get(i).toString();
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