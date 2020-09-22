package by.wh.ts.dao;

import java.sql.Connection;

import by.wh.ts.bean.NewUser;
import by.wh.ts.bean.User;

public interface UserDAO {
	
	boolean saveNewUser(Connection connection, NewUser newUser) throws DAOException;
	User findUser(Connection connection, String login, String password) throws DAOException;

}
