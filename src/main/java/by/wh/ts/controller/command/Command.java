package by.wh.ts.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException;

}
