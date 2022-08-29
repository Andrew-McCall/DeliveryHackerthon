package am.pages.crud;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import am.Application;
import am.common.swing.Title;
import am.pages.ManagerMenu;

public abstract class CRUDMenu<T> extends JPanel implements ActionListener {

	private static final long serialVersionUID = 424004304431544769L;

	private String title;

	public CRUDMenu(String title) {
		this.setTitle(title);

		JButton create = new JButton("Create");
		JButton viewAll = new JButton("View All");
		JButton viewOne = new JButton("View One");
		JButton edit = new JButton("Edit");
		JButton delete = new JButton("Delete");

		JLabel space = new JLabel();
		JButton back = new JButton("Back");

		this.add(new Title(title));

		this.add(create);
		create.addActionListener(this);
		this.add(viewAll);
		viewAll.addActionListener(this);
		this.add(viewOne);
		viewOne.addActionListener(this);
		this.add(edit);
		edit.addActionListener(this);
		this.add(delete);
		delete.addActionListener(this);

		this.add(space);
		this.add(back);
		back.addActionListener(this);

		this.setLayout(new GridLayout(8, 10, 20, 5));

	}

	public abstract CRUDOption<T> getCreate();

	public abstract CRUDOption<T> getViewOne();

	public abstract CRUDOption<T> getViewAll();

	public abstract CRUDOption<T> getEdit();

	public abstract CRUDOption<T> getDelete();

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Back")) {
			Application.setPanel(new ManagerMenu());
			return;
		}

		this.setVisible(false);

		switch (e.getActionCommand()) {

		case "Create":
			Application.setPanel(getCreate());
			return;

		case "View One":
			Application.setPanel(getViewOne());
			break;

		case "View All":
			Application.setPanel(getViewAll());
			break;

		case "Edit":
			Application.setPanel(getEdit());
			break;

		case "Delete":
			Application.setPanel(getDelete());
			break;
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}