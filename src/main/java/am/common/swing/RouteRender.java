package am.common.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;

import am.data.entites.Location;
import am.data.entites.Route;
import am.data.entites.SmartRouteStop;

public class RouteRender extends JPanel implements MouseListener, WindowFocusListener {

	private static final long serialVersionUID = 3663220966374932098L;
	private Route route;
	private JDialog routestopEditor = null;

	private List<SmartRouteStop> smartRouteStops;

	private final int DIMENTION = 250;

	private boolean calculated = false;
	private double smallestLatitude;
	private double smallestLongitude;
	private double largeLatitude;
	private double largeLongitude;

	public RouteRender() {
		this.setSize(DIMENTION, DIMENTION);
		this.setPreferredSize(new Dimension(DIMENTION, DIMENTION));
		this.setVisible(true);
		this.setBackground(Color.LIGHT_GRAY);
		this.addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (route != null) {
			List<SmartRouteStop> smartroutestops = getSmartRouteStops();
			if (!calculated) {
				calculated = true;
				smallestLatitude = 100.0;
				smallestLongitude = 100.0;
				largeLatitude = -100.0;
				largeLongitude = -100.0;
				for (int i = 0; i < smartroutestops.size(); i++) {
					Location loc = smartroutestops.get(i).getLocation();
					if (smallestLatitude > loc.getLatitude()) {
						smallestLatitude = loc.getLatitude();
					}
					if (smallestLongitude > loc.getLongitude()) {
						smallestLongitude = loc.getLongitude();
					}
					if (largeLatitude < loc.getLatitude()) {
						largeLatitude = loc.getLatitude();
					}
					if (largeLongitude < loc.getLongitude()) {
						largeLongitude = loc.getLongitude();
					}
				}
				double scaleX = (largeLatitude - smallestLatitude) * .3;
				double scaleY = (largeLongitude - smallestLongitude) * .3;
				smallestLatitude -= scaleX;
				largeLatitude += scaleX;
				smallestLongitude -= scaleY;
				largeLongitude += scaleY;

				scaleX = (largeLatitude - smallestLatitude);
				scaleY = (largeLongitude - smallestLongitude);

				for (int i = 0; i < smartroutestops.size(); i++) {
					Location loc = smartroutestops.get(i).getLocation();
					smartroutestops.get(i).setRouteRenderPoint(
							new Point((int) ((loc.getLatitude() - smallestLatitude) / scaleX * DIMENTION),
									(int) ((loc.getLongitude() - smallestLongitude) / scaleY * DIMENTION)));

				}
			}

			for (int i = 0; i < smartroutestops.size(); i++) {
				SmartRouteStop srs = smartroutestops.get(i);
				Point coords = srs.getRouteRenderPoint();
				if (srs.getIsDelivered()) {
					g2d.setColor(Color.GREEN);
				} else {
					g2d.setColor(Color.RED);
				}
				g2d.fillOval((int) coords.getX(), (int) coords.getY(), 20, 20);
			}

		}

	}

	private List<SmartRouteStop> getSmartRouteStops() {
		if (smartRouteStops == null) {
			smartRouteStops = route.getSmartRoutestop();
		}
		return smartRouteStops;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		calculated = false;
		this.route = route;
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (route != null) {
			for (int i = 0; i < smartRouteStops.size(); i++) {
				if (smartRouteStops.get(i).getRouteRenderPoint().distanceSq(new Point(e.getX(), e.getY())) < 600) {
					this.setRoutestopEditor(new RoutestopEditor(smartRouteStops.get(i)));
					break;
				}
			}
		}
	}

	private void popupOver() {
		this.routestopEditor.dispose();
		this.repaint();
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		popupOver();
	}

	public JDialog getRoutestopEditor() {
		return routestopEditor;
	}

	public void setRoutestopEditor(JDialog routestopEditor) {
		if (this.routestopEditor != null)
			this.routestopEditor.dispose();
		routestopEditor.addWindowFocusListener(this);
		this.routestopEditor = routestopEditor;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowGainedFocus(WindowEvent e) {

	}

}
