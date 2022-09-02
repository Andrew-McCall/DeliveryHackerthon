package am.pages;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import am.Application;
import am.common.swing.RouteRender;
import am.common.swing.Title;
import am.data.daos.RouteDAO;
import am.data.entites.Route;

public class ManageRoutes extends JPanel implements ActionListener {

	private static final long serialVersionUID = -4161341089493342519L;
	private RouteRender routeRender = new RouteRender();
	private JComboBox<Route> routeSelect;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ManageRoutes() {
		JPanel leftPane = new JPanel();
		leftPane.setLayout(new GridLayout(5, 1, 12, 12));
		this.add(leftPane);

		leftPane.add(new Title("Route Manager"));

		leftPane.add(new JLabel("Routes:"));
		List<Route> routes = new RouteDAO().readAll();
		routeSelect = new JComboBox(routes.toArray());
		routeSelect.addActionListener(this);
		leftPane.add(routeSelect);

		this.add(Box.createVerticalStrut(10));

		JButton back = new JButton("Back");
		back.addActionListener(e -> Application.setPanel(new ManagerPage()));
		leftPane.add(back);

		this.add(Box.createHorizontalStrut(20));

		this.add(routeRender);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		routeRender.setRoute((Route) routeSelect.getSelectedItem());
	}

}
