package am.pages.crud;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import am.common.swing.Title;

public abstract class CRUDOption<T> extends JPanel implements ActionListener {

	private static final long serialVersionUID = -4459465244620589509L;
	private JLabel output;

	public CRUDOption(String title, int layoutHeight) {

		this.add(new Title(title));

		output = new JLabel("");
		this.add(output);

		create();

		JButton back = new JButton("Back");
		this.add(back);
		back.addActionListener(this);

		this.setLayout(new GridLayout(layoutHeight + 3, 1, 12, 12));
	}

	public abstract void create();

	public void setOutput(String message) {
		output.setText(message);
	}

}
