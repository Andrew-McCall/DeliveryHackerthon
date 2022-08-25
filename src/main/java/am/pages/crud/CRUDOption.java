package am.pages.crud;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import am.common.Title;

public abstract class CRUDOption<T> extends JPanel implements ActionListener {

	private static final long serialVersionUID = -4459465244620589509L;

	public CRUDOption(String title, int layoutHeight) {

		this.add(new Title(title));

		create();

		JButton back = new JButton("Back");
		this.add(back);
		back.addActionListener(this);

		this.setLayout(new GridLayout(layoutHeight, 1, 12, 12));
	}

	public abstract void create();

//	public abstract JPanel getParent();
//
//	public abstract boolean action();

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getActionCommand().equals("Back") || action()) {
//			Application.setPanel(getParent());
//		}
//	}

}
