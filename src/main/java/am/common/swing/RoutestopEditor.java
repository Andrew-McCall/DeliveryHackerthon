package am.common.swing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import am.data.daos.RoutestopDAO;
import am.data.entites.Routestop;

public class RoutestopEditor extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1846213716631605208L;

	private Routestop routestop;

	private JButton isDelivered = new JButton("Delivered");
	private JLabel label = new JLabel("");

	public RoutestopEditor(Routestop routestop) {
		this.routestop = routestop;

		label.setText(routestop.toString());
		this.add(label);

		isDelivered.addActionListener(this);
		this.add(isDelivered);

		calculateButtonColour();

		this.setLayout(new GridLayout(2, 1, 12, 12));

		this.setTitle("Routestop");
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		routestop.setIsDelivered(!routestop.getIsDelivered());
		label.setText(routestop.toString());
		new RoutestopDAO().update(routestop);
		calculateButtonColour();
		this.repaint();
	}

	private void calculateButtonColour() {
		if (routestop.getIsDelivered()) {
			isDelivered.setBackground(Color.GREEN);
		} else {
			isDelivered.setBackground(Color.PINK);
		}
	}

	public Routestop getRoutestop() {
		return routestop;
	}

	public void setRoutestop(Routestop routestop) {
		this.routestop = routestop;
	}

}
