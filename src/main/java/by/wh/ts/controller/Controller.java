package by.wh.ts.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.wh.ts.controller.command.Command;
import by.wh.ts.controller.command.CommandProvider;
import by.wh.ts.service.pool.ConnectionPool;
import by.wh.ts.service.pool.ConnectionPoolProvider;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ConnectionPoolProvider poolProvider = ConnectionPoolProvider.getInstance();
	private CommandProvider commands = new CommandProvider();

	private static final String COMMAND_NAME = "command";

	@Override
	public void destroy() {
		super.destroy();
		ConnectionPool pool = poolProvider.getConnectionPool();
		pool.destroyPool();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ConnectionPool pool = poolProvider.getConnectionPool();
		pool.initPool();

	}

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Command command;
		String name = request.getParameter(COMMAND_NAME);
		command = commands.getCommand(name);
		command.execute(request, response);

	}

}
