package am.pages.crud;

import am.data.entites.Route;

public class RouteMenu extends CRUDMenu<Route> {

	private static final long serialVersionUID = 2052047138224585057L;

	public RouteMenu() {
		super("Route");
	}

	@Override
	public CRUDOption<Route> getCreate() {
		return new RouteOptionCreate();
	}

	@Override
	public CRUDOption<Route> getViewOne() {
		return new RouteOptionReadOne();
	}

	@Override
	public CRUDOption<Route> getViewAll() {
		return new RouteOptionReadAll();
	}

	@Override
	public CRUDOption<Route> getEdit() {
		return new RouteOptionUpdate();
	}

	@Override
	public CRUDOption<Route> getDelete() {
		return new RouteOptionDelete();
	}

}
