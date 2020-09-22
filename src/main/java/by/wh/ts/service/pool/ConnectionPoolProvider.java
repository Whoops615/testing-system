package by.wh.ts.service.pool;

import by.wh.ts.service.pool.impl.BasicConnectionPool;

public class ConnectionPoolProvider {

	private static final ConnectionPoolProvider POOL_PROVIDER = new ConnectionPoolProvider();

	private final ConnectionPool connectionPool = new BasicConnectionPool();

	private ConnectionPoolProvider() {

	}

	public static ConnectionPoolProvider getInstance() {
		return POOL_PROVIDER;
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

}
