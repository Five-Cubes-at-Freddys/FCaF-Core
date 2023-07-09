package fr.tartur.fcaf.libs.plugincomponents.commands;

import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.command.CommandExecutor;

import java.util.Arrays;

public abstract class BaseCommand implements CommandExecutor {

    protected final String[] names;
    protected final int index;
    protected final FPlayerManager players;
    protected final BaseCommand[] subCommands;

    public BaseCommand(String names, int argIndex, FPlayerManager players, BaseCommand... subCommands) {
        this.names = names.split(",");
        this.index = argIndex;
        this.players = players;
        this.subCommands = subCommands;
    }

    protected String[] tail(int currentIndex, String[] args) {
        return args.length - currentIndex > 1
                ? Arrays.copyOfRange(args, currentIndex + 1, args.length)
                : new String[]{};
    }
}
