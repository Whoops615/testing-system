package by.wh.ts.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.wh.ts.bean.NewUser;

public class Validator {

	private final static Pattern PATTERN_LOGIN = Pattern.compile("[a-zA-Z|0-9|\\u005F|-]{4,20}");
	private final static Pattern PATTERN_PASSWORD = Pattern.compile("[a-zA-Z|0-9]{4,20}");
	private final static Pattern PATTERN_EMAIL = Pattern
			.compile("[a-zA-Z|0-9|\u005F|-]+@{1}[a-z]+\u002E{1}((by)|(ru)|(com)|(org)|(net)){1}");
	private final static Pattern PATTERN_NAME_OR_SURNAME = Pattern.compile("[a-zA-Z]{1,30}");

	public static boolean checkNewUser(NewUser newUser) {

		if (!checkLogin(newUser.getLogin())) {
			return false;
		}
		if (!checkPassword(newUser.getPassword())) {
			return false;
		}
		if (!checkEmail(newUser.getEmail())) {
			return false;
		}
		if (!checkNameOrSurname(newUser.getName())) {
			return false;
		}
		if (!checkNameOrSurname(newUser.getSurname())) {
			return false;
		}

		return true;
	}

	public static boolean checkLogin(String login) {
		Matcher m = PATTERN_LOGIN.matcher(login);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	public static boolean checkPassword(String password) {
		Matcher m = PATTERN_PASSWORD.matcher(password);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	public static boolean checkEmail(String email) {
		Matcher m = PATTERN_EMAIL.matcher(email);
		if (m.matches()) {
			return true;
		}
		return false;

	}

	public static boolean checkNameOrSurname(String name) {
		Matcher m = PATTERN_NAME_OR_SURNAME.matcher(name);
		if (m.matches()) {
			return true;
		}
		return false;

	}

}
