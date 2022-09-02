package am.data.entites;

import java.awt.Point;

public class SmartRouteStop extends Routestop {

	private Location location;
	private Address address;
	private Order order;

	private Point routeRenderPoint;

	public SmartRouteStop(Long routestop_id, Long route_id, Integer stop_number, Long order_id, Boolean isDelivered) {
		super(routestop_id, route_id, stop_number, order_id, isDelivered);
		calculateLocation();
	}

	@Override
	public String toString() {
		return "SmartRouteStop [address=" + address + ", order=" + order + ", isDelivered=" + this.getIsDelivered()
				+ "]";
	}

	public SmartRouteStop(Routestop routestop) {
		this(routestop.getRoutestop_id(), routestop.getRoute_id(), routestop.getStop_number(), routestop.getOrder_id(),
				routestop.getIsDelivered());
	}

	public void calculateLocation() {
		this.order = this.getOrder();
		this.address = this.order.getAddress();
		this.location = this.address.getLocation();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Point getRouteRenderPoint() {
		return routeRenderPoint;
	}

	public void setRouteRenderPoint(Point routeRenderPoint) {
		this.routeRenderPoint = routeRenderPoint;
	}

}
