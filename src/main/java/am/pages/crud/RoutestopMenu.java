package am.pages.crud;

import am.data.entites.Routestop;

public class RoutestopMenu extends CRUDMenu<Routestop> {

	private static final long serialVersionUID = -6534669852640916117L;

	public RoutestopMenu() {
		super("Routestop");
	}

	@Override
	public CRUDOption<Routestop> getCreate() {
		return new RoutestopOptionCreate();
	}

	@Override
	public CRUDOption<Routestop> getViewOne() {
		return new RoutestopOptionReadOne();
	}

	@Override
	public CRUDOption<Routestop> getViewAll() {
		return new RoutestopOptionReadAll();
	}

	@Override
	public CRUDOption<Routestop> getEdit() {
		return new RoutestopOptionUpdate();
	}

	@Override
	public CRUDOption<Routestop> getDelete() {
		return new RoutestopOptionDelete();
	}

}
