package by.wh.ts.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.wh.ts.controller.command.impl.ChangeLocal;
import by.wh.ts.controller.command.impl.GoToMainPage;
import by.wh.ts.controller.command.impl.GoToRegistrationPage;
import by.wh.ts.controller.command.impl.GoToSignInPage;
import by.wh.ts.controller.command.impl.Registration;
import by.wh.ts.controller.command.impl.SignIn;
import by.wh.ts.controller.command.impl.SignOut;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	public CommandProvider() {
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocal());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());

		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_SIGN_IN_PAGE, new GoToSignInPage());
	}

	public Command getCommand(String name) {

		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		return commands.get(commandName);

	}

}
