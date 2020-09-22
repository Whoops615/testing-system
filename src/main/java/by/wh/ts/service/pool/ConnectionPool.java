package by.wh.ts.service.pool;

import java.sql.Connection;

public interface ConnectionPool {
	
	Connection takeConnection();
	void returnConnection(Connection connection);
	void initPool();
	void destroyPool();
	

}
