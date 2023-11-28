package by.bsuir.wtlab2.logic;

import java.util.HashMap;
import java.util.Map;

import by.bsuir.wtlab2.logic.impl.*;

/**
 * Class of command helper
 * @author haidukevgen
 * @version 1.0
 */
public final class CommandHelper {
    private static final CommandHelper instance = new CommandHelper();
    private final Map<CommandName, ICommand> commands = new HashMap<>();

    /**
     * Constructor to put all commands to map
     */
    public CommandHelper() {
        commands.put(CommandName.DO_LOGIN, new LoginCommand());
        commands.put(CommandName.DO_REGISTER, new RegisterCommand());
        commands.put(CommandName.DO_LOGOUT, new LogoutCommand());
        commands.put(CommandName.GET_FILMS, new GetFilmsCommand());
        commands.put(CommandName.ADD_FILM, new AddFilmCommand());
        commands.put(CommandName.EDIT_FILM, new EditFilmCommand());
        commands.put(CommandName.GET_FILM, new GetFilmCommand());
        commands.put(CommandName.GET_REVIEWS, new GetReviewsCommand());
        commands.put(CommandName.ADD_REVIEW, new AddReviewCommand());
        commands.put(CommandName.DELETE_REVIEW, new DeleteReviewCommand());
        commands.put(CommandName.GET_USERS, new GetUsersCommand());
        commands.put(CommandName.EDIT_USER, new EditUserCommand());
        commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }

    /**
     * Method to get class instance
     * @return Class instance
     */
    public static CommandHelper getInstance() {
        return instance;
    }

    /**
     * Method to get command instance by name
     * @param commandName Name of command
     * @return Specific command instance
     */
    public ICommand getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandName.NO_SUCH_COMMAND);
        }
        CommandName name;
        try {
            name = CommandName.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return commands.get(name);
    }
}
