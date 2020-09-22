package by.wh.ts.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import by.wh.ts.bean.NewUser;
import by.wh.ts.bean.User;
import by.wh.ts.dao.DAOException;
import by.wh.ts.dao.DAOProvider;
import by.wh.ts.dao.UserDAO;
import by.wh.ts.service.ServiceException;
import by.wh.ts.service.UserService;
import by.wh.ts.service.pool.ConnectionPool;
import by.wh.ts.service.pool.ConnectionPoolProvider;
import by.wh.ts.service.util.Validator;
import by.wh.ts.service.util.ValidatorException;

public class UserServiceImpl implements UserService {

	private ConnectionPoolProvider poolProvider = ConnectionPoolProvider.getInstance();
	private DAOProvider daoProvider = DAOProvider.getInstance();

	@Override
	public boolean registration(NewUser newUser) throws ValidatorException, ServiceException {

		if (!Validator.checkNewUser(newUser)) {
			throw new ValidatorException();
		}

		UserDAO userDAO = daoProvider.getUserDAO();
		ConnectionPool pool = poolProvider.getConnectionPool();
		Connection connection = pool.takeConnection();
		boolean result;

		try {

			result = userDAO.saveNewUser(connection, newUser);

		} catch (DAOException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				// log
			}
			throw new ServiceException();

		} finally {
			pool.returnConnection(connection);
		}

		return result;
	}

	@Override
	public User authorization(String login, String password) throws ValidatorException, ServiceException {

		if (!Validator.checkLogin(login) || !Validator.checkPassword(password)) {
			throw new ValidatorException();
		}

		UserDAO dao = daoProvider.getUserDAO();
		ConnectionPool pool = poolProvider.getConnectionPool();
		Connection connection = pool.takeConnection();
		User user;

		try {
			user = dao.findUser(connection, login, password);
			if (user == null) {
				return null;
			}

			// подтягиваем инфо

		} catch (DAOException e) {
			throw new ServiceException();
		}

		return user;
	}

}
