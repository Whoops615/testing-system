package by.wh.ts.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.wh.ts.controller.command.Command;
import by.wh.ts.controller.command.Constant;

public class GoToMainPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute(Constant.SESSION_PARAM_LAST_CONTENT);

		request.getRequestDispatcher(Constant.PATH_MAIN_PAGE).forward(request, responce);

	}

}
