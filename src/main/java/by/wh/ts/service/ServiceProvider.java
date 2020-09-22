package by.wh.ts.service;

import by.wh.ts.service.impl.UserServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider INSTANCE = new ServiceProvider();

	private UserService userService = new UserServiceImpl();

	private ServiceProvider() {

	}

	public static ServiceProvider getInstance() {
		return INSTANCE;
	}

	public UserService getUserService() {
		return userService;
	}

}
