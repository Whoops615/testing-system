package by.wh.ts.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.mindrot.jbcrypt.BCrypt;

import by.wh.ts.bean.NewUser;
import by.wh.ts.bean.User;
import by.wh.ts.dao.DAOException;
import by.wh.ts.dao.UserDAO;

public class SQLUserDAO implements UserDAO {

	private static Lock lock = new ReentrantLock();

	private static final String SQL_INSERT_NEW_USER = "INSERT user(login, password, email, name, surname)\r\n"
			+ "VALUES (?, ?, ?, ?, ?);";
	private static final String SQL_SELECT_LOGIN = "SELECT login FROM user WHERE login=?";
	private static final String SQL_SELECT_EMAIL = "SELECT email FROM user WHERE email=?";
	private static final String SQL_SELECT_USER = "SELECT  user.password, user.id, user.email, user.name, user.surname, role.title FROM user "
			+ "INNER JOIN role ON user.role_id = role.id WHERE user.login = ?;";

	@Override
	public boolean saveNewUser(Connection connection, NewUser newUser) throws DAOException {

		String salt;
		String hashpw;

		try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_NEW_USER)) {

			lock.lock();
			if (hasLogin(connection, newUser.getLogin())) {
				return false;
			}
			if (hasEmail(connection, newUser.getEmail())) {
				return false;
			}

			salt = BCrypt.gensalt();
			hashpw = BCrypt.hashpw(newUser.getPassword(), salt);

			ps.setString(1, newUser.getLogin());
			ps.setString(2, hashpw);
			ps.setString(3, newUser.getEmail());
			ps.setString(4, newUser.getName());
			ps.setString(5, newUser.getSurname());

			ps.executeUpdate();

		} catch (SQLException e) {
			// log
			throw new DAOException();
		} finally {
			lock.unlock();
		}

		return true;
	}

	private boolean hasLogin(Connection connection, String login) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_LOGIN)) {
			ps.setString(1, login);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean hasEmail(Connection connection, String email) throws SQLException {

		try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_EMAIL)) {
			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public User findUser(Connection connection, String login, String password) throws DAOException {

		User user = new User();

		try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_USER)) {
			
			ps.setString(1, login);
			
			try (ResultSet rs = ps.executeQuery()) {
				
				if (rs.next()) {
					
					if (!BCrypt.checkpw(password, rs.getString(1))) {
						return null;
					}

					user.setId(rs.getInt(2));
					user.setEmail(rs.getString(3));
					user.setName(rs.getString(4));
					user.setSurname(rs.getString(5));
					user.setRole(rs.getString(6));

				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			// log
			throw new DAOException();
		}

		return user;
	}

}
