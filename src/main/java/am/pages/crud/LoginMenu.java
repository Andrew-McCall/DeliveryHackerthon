package am.pages.crud;

import am.entities.Login;

public class LoginMenu extends CRUDMenu<Login> {

	private static final long serialVersionUID = -1758657975841667766L;

	public LoginMenu() {
		super("Login");
	}

	@Override
	public CRUDOption<Login> getCreate() {
		return new LoginOptionCreate();
	}

	@Override
	public CRUDOption<Login> getViewOne() {
		return null;
	}

	@Override
	public CRUDOption<Login> getViewAll() {
		return null;
	}

	@Override
	public CRUDOption<Login> getEdit() {
		return null;
	}

	@Override
	public CRUDOption<Login> getDelete() {
		return null;
	}

}
