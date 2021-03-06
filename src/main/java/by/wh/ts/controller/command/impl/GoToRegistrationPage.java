package by.wh.ts.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.wh.ts.controller.command.Command;
import by.wh.ts.controller.command.Constant;

public class GoToRegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {

		HttpSession session = request.getSession();

		request.setAttribute(Constant.CONTENT, Constant.PATH_REGISTRATION_PAGE);
		session.setAttribute(Constant.SESSION_PARAM_LAST_CONTENT, Constant.PATH_REGISTRATION_PAGE);

		RequestDispatcher disp = request.getRequestDispatcher(Constant.PATH_MAIN_PAGE);
		disp.forward(request, responce);

	}

}
