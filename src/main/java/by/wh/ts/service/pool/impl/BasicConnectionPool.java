package by.wh.ts.service.pool.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import by.wh.ts.service.pool.ConnectionPool;

public class BasicConnectionPool implements ConnectionPool {

	private BlockingQueue<Connection> availableConnection;
	private BlockingQueue<Connection> unavailableConnection;

	public BasicConnectionPool() {

	}

	@Override
	public void initPool() {

		DBConfig config = DBConfig.getDBConfig();

		String driver = config.getDriver();
		String url = config.getUrl();
		String user = config.getUser();
		String password = config.getPassword();
		int poolsize = config.getPoolsize();

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// log
			e1.printStackTrace();
			throw new RuntimeException();
		}

		availableConnection = new ArrayBlockingQueue<Connection>(poolsize);
		unavailableConnection = new ArrayBlockingQueue<Connection>(poolsize);
		try {
			for (int i = 0; i < poolsize; i++) {
				Connection connection;
				connection = DriverManager.getConnection(url, user, password);
				availableConnection.add(connection);
			}
		} catch (SQLException e) {
			// log
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	@Override
	public void destroyPool() {

		try {
			closeConnectionQueue(availableConnection);
			closeConnectionQueue(unavailableConnection);
		} catch (SQLException e) {
			// log
		}

	}

	private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {

		Connection connection;
		while ((connection = queue.poll()) != null) {
			connection.close();
		}
	}

	@Override
	public Connection takeConnection() {

		Connection connection = null;
		try {
			connection = availableConnection.take();
			unavailableConnection.add(connection);
		} catch (InterruptedException e) {
			// log , ошибка при прерывании ожидания
		}
		return connection;
	}

	@Override
	public void returnConnection(Connection connection) {

		unavailableConnection.remove(connection);
		availableConnection.add(connection);

	}

}
