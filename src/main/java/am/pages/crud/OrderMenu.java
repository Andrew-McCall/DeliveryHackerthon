package am.pages.crud;

import am.data.entites.Order;

public class OrderMenu extends CRUDMenu<Order> {

	private static final long serialVersionUID = -4341068397029107297L;

	public OrderMenu() {
		super("Order");
	}

	@Override
	public CRUDOption<Order> getCreate() {
		return new OrderOptionCreate();
	}

	@Override
	public CRUDOption<Order> getViewOne() {
		return new OrderOptionReadOne();
	}

	@Override
	public CRUDOption<Order> getViewAll() {
		return new OrderOptionReadAll();
	}

	@Override
	public CRUDOption<Order> getEdit() {
		return new OrderOptionUpdate();
	}

	@Override
	public CRUDOption<Order> getDelete() {
		return new OrderOptionDelete();
	}

}
