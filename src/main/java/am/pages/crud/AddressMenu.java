package am.pages.crud;

import am.data.entites.Address;

public class AddressMenu extends CRUDMenu<Address> {

	private static final long serialVersionUID = 4149871324845783435L;

	public AddressMenu() {
		super("Address");
	}

	@Override
	public CRUDOption<Address> getCreate() {
		return new AddressOptionCreate();
	}

	@Override
	public CRUDOption<Address> getViewOne() {
		return new AddressOptionReadOne();
	}

	@Override
	public CRUDOption<Address> getViewAll() {
		return new AddressOptionReadAll();
	}

	@Override
	public CRUDOption<Address> getEdit() {
		return new AddressOptionUpdate();
	}

	@Override
	public CRUDOption<Address> getDelete() {
		return new AddressOptionDelete();
	}

}
