package am.pages.crud;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import am.common.swing.TickBox;
import am.data.entites.Login;
import am.root.Application;

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
			} else if (password.getText().length() == 0) {
				setOutput("Missing Password");
				password.grabFocus();
				return;
			}
			return;
		}
		Application.setPanel(new LoginMenu());
	}

}
