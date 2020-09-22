package by.wh.ts.dao;

import by.wh.ts.dao.impl.SQLUserDAO;

public class DAOProvider {

	private static final DAOProvider INSTANCE = new DAOProvider();

	private UserDAO userDAO = new SQLUserDAO();

	private DAOProvider() {

	}

	public static DAOProvider getInstance() {
		return INSTANCE;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

}
