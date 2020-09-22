package by.wh.ts.service.pool.impl;

import java.util.ResourceBundle;

public class DBConfig {

	private final static DBConfig DBCONFIG = new DBConfig();
	private final String PROPERTIES_DRIVER = "db.driver";
	private final String PROPERTIES_URL = "db.url";
	private final String PROPERTIES_USER = "db.user";
	private final String PROPERTIES_PASSWORD = "db.password";
	private final String PROPERTIES_POOLSIZE = "db.poolsize";
	
	private String driver;
	private String url;
	private String user;
	private String password;
	private int poolsize;

	private DBConfig() {
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		driver = bundle.getString(PROPERTIES_DRIVER);
		url = bundle.getString(PROPERTIES_URL);
		user = bundle.getString(PROPERTIES_USER);
		password = bundle.getString(PROPERTIES_PASSWORD);
		poolsize = Integer.parseInt(bundle.getString(PROPERTIES_POOLSIZE));
	}

	public static DBConfig getDBConfig() {
		return DBCONFIG;
	}
	
	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public int getPoolsize() {
		return poolsize;
	}

}
