package by.wh.ts.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.wh.ts.bean.NewUser;
import by.wh.ts.controller.command.Command;
import by.wh.ts.controller.command.Constant;
import by.wh.ts.controller.command.LocalKey;
import by.wh.ts.service.ServiceException;
import by.wh.ts.service.ServiceProvider;
import by.wh.ts.service.UserService;
import by.wh.ts.service.util.ValidatorException;

public class Registration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {

		NewUser newUser = new NewUser();
		newUser.setLogin(request.getParameter(Constant.REQUEST_PARAM_LOGIN));
		newUser.setPassword(request.getParameter(Constant.REQUEST_PARAM_PASSWORD));
		newUser.setEmail(request.getParameter(Constant.REQUEST_PARAM_EMAIL));
		newUser.setName(request.getParameter(Constant.REQUEST_PARAM_NAME));
		newUser.setSurname(request.getParameter(Constant.REQUEST_PARAM_SURNAME));

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		HttpSession session = request.getSession();

		String message;
		String content = null;

		try {

			if (userService.registration(newUser)) {
				message = LocalKey.REGISTRATION_SUCCESSFUL;
				content = Constant.PATH_SIGN_IN_PAGE;
			} else {
				message = LocalKey.REGISTRATION_FAIL;
				content = Constant.PATH_REGISTRATION_PAGE;
			}

		} catch (ValidatorException e) {
			message = LocalKey.INCORRECT_DATA;
			content = Constant.PATH_REGISTRATION_PAGE;
		} catch (ServiceException e) {
			message = LocalKey.TECHNICAL_PROBLEM;
		}

		session.setAttribute(Constant.SESSION_PARAM_LAST_CONTENT, content);
		request.setAttribute(Constant.CONTENT, content);
		request.setAttribute(Constant.MESSAGE, message);
		RequestDispatcher disp = request.getRequestDispatcher(Constant.PATH_MAIN_PAGE);
		disp.forward(request, responce);

	}

}