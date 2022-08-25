package am.pages.crud;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import am.entities.Login;
import am.root.Application;

class LoginOptionCreate extends CRUDOption<Login> {

	private static final long serialVersionUID = 9137282126778111696L;

	public LoginOptionCreate() {
		super("Create", 3);
	}

	@Override
	public void create() {

		JButton create = new JButton("Create");
		create.addActionListener(this);
		this.add(create);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Back")) {
			System.out.println("Back");
		} else {
			System.out.println("Create");
		}
		Application.setPanel(new LoginMenu());
	}

}
