package am.data.entites;

public class Routestop {

	public Routestop(Long route_id, Integer stop_number, Long order_id) {
		this.route_id = route_id;
		this.stop_number = stop_number;
		this.order_id = order_id;
	}

	public Routestop(Long routestop_id, Long route_id, Integer stop_number, Long order_id) {
		this.routestop_id = routestop_id;
		this.route_id = route_id;
		this.stop_number = stop_number;
		this.order_id = order_id;
	}

	private Long routestop_id;

	private Long route_id;
	private Integer stop_number;
	private Long order_id;

	@Override
	public String toString() {
		return "Routestop [routestop_id=" + routestop_id + ", route_id=" + route_id + ", stop_number=" + stop_number
				+ ", order_id=" + order_id + "]";
	}

	public Long getRoutestop_id() {
		return routestop_id;
	}

	public void setRoutestop_id(Long routestop_id) {
		this.routestop_id = routestop_id;
	}

	public Long getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Long route_id) {
		this.route_id = route_id;
	}

	public Integer getStop_number() {
		return stop_number;
	}

	public void setStop_number(Integer stop_number) {
		this.stop_number = stop_number;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((route_id == null) ? 0 : route_id.hashCode());
		result = prime * result + ((routestop_id == null) ? 0 : routestop_id.hashCode());
		result = prime * result + ((stop_number == null) ? 0 : stop_number.hashCode());
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
		Routestop other = (Routestop) obj;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (route_id == null) {
			if (other.route_id != null)
				return false;
		} else if (!route_id.equals(other.route_id))
			return false;
		if (routestop_id == null) {
			if (other.routestop_id != null)
				return false;
		} else if (!routestop_id.equals(other.routestop_id))
			return false;
		if (stop_number == null) {
			if (other.stop_number != null)
				return false;
		} else if (!stop_number.equals(other.stop_number))
			return false;
		return true;
	}

}
