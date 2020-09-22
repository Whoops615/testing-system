package by.wh.ts.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.wh.ts.controller.command.Command;
import by.wh.ts.controller.command.Constant;

public class ChangeLocal implements Command {

	private final static String LOCAL = "local";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {

		String local;
		String lastContent;
		HttpSession session = request.getSession();

		lastContent = (String) session.getAttribute(Constant.SESSION_PARAM_LAST_CONTENT);
		local = request.getParameter(LOCAL);
		session.setAttribute(LOCAL, local);

		if (lastContent != null) {
			request.setAttribute(Constant.CONTENT, lastContent);
		}

		RequestDispatcher disp = request.getRequestDispatcher(Constant.PATH_MAIN_PAGE);
		disp.forward(request, responce);

	}

}
