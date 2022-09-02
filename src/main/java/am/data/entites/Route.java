package am.data.entites;

import java.util.ArrayList;
import java.util.List;

import am.data.daos.RoutestopDAO;

public class Route {

	public Route(Long login_id) {
		this.login_id = login_id;
		this.distance = 0;
		this.stop_count = 0;
	}

	public Route(Integer distance, Integer stop_count, Long login_id) {
		this.distance = distance;
		this.stop_count = stop_count;
		this.login_id = login_id;
	}

	public Route(Long route_id, Integer distance, Integer stop_count, Long login_id) {
		this.route_id = route_id;
		this.distance = distance;
		this.stop_count = stop_count;
		this.login_id = login_id;
	}

	private Long route_id;
	private Integer distance; // 0 Default (Calculated)
	private Integer stop_count; // 0 Default (Calculated)
	private Long login_id;

	public List<Routestop> getRoutestops() {
		return new RoutestopDAO().readByRoute(route_id);
	}

	public List<SmartRouteStop> getSmartRoutestop() {
		List<Routestop> routestops = getRoutestops();
		List<SmartRouteStop> smartRouteStops = new ArrayList<SmartRouteStop>();
		for (int i = 0; i < routestops.size(); i++) {
			smartRouteStops.add(new SmartRouteStop(routestops.get(i)));
			System.out.println(routestops.get(i));
		}
		return smartRouteStops;
	}

	@Override
	public String toString() {
		return "Route [route_id=" + route_id + ", distance=" + distance + ", stop_count=" + stop_count + ", login_id="
				+ login_id + "]";
	}

	public Long getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Long route_id) {
		this.route_id = route_id;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getStop_count() {
		return stop_count;
	}

	public void setStop_count(Integer stop_count) {
		this.stop_count = stop_count;
	}

	public Long getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Long login_id) {
		this.login_id = login_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((login_id == null) ? 0 : login_id.hashCode());
		result = prime * result + ((route_id == null) ? 0 : route_id.hashCode());
		result = prime * result + ((stop_count == null) ? 0 : stop_count.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (login_id == null) {
			if (other.login_id != null)
				return false;
		} else if (!login_id.equals(other.login_id))
			return false;
		if (route_id == null) {
			if (other.route_id != null)
				return false;
		} else if (!route_id.equals(other.route_id))
			return false;
		if (stop_count == null) {
			if (other.stop_count != null)
				return false;
		} else if (!stop_count.equals(other.stop_count))
			return false;
		return true;
	}

}
