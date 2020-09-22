package by.wh.ts.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.wh.ts.bean.User;
import by.wh.ts.controller.command.Command;
import by.wh.ts.controller.command.Constant;
import by.wh.ts.controller.command.LocalKey;
import by.wh.ts.service.ServiceException;
import by.wh.ts.service.ServiceProvider;
import by.wh.ts.service.UserService;
import by.wh.ts.service.util.ValidatorException;

public class SignIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {

		String login = request.getParameter(Constant.REQUEST_PARAM_LOGIN);
		String password = request.getParameter(Constant.REQUEST_PARAM_PASSWORD);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService service = provider.getUserService();

		HttpSession session = request.getSession();

		User user;
		String content = null;
		String message;

		try {

			user = service.authorization(login, password);

			if (user != null) {
				message = LocalKey.SIGN_IN_SUCCESS;
				session.setAttribute(Constant.SESSION_PARAM_USER, user);
			} else {
				content = Constant.PATH_SIGN_IN_PAGE;
				message = LocalKey.SIGN_IN_FAIL;
			}

		} catch (ValidatorException e) {
			content = Constant.PATH_SIGN_IN_PAGE;
			message = LocalKey.INCORRECT_DATA;
		} catch (ServiceException e) {
			message = LocalKey.TECHNICAL_PROBLEM;
		}

		session.setAttribute(Constant.SESSION_PARAM_LAST_CONTENT, content);
		request.setAttribute(Constant.CONTENT, content);
		request.setAttribute(Constant.MESSAGE, message);

		request.getRequestDispatcher(Constant.PATH_MAIN_PAGE).forward(request, responce);

	}

}
