package by.wh.ts.service;

import by.wh.ts.bean.NewUser;
import by.wh.ts.bean.User;
import by.wh.ts.service.util.ValidatorException;

public interface UserService {
	
	boolean registration(NewUser newUser) throws ValidatorException, ServiceException;
	User authorization(String login, String password) throws ValidatorException, ServiceException;

}
