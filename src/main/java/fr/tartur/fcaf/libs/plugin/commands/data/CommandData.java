package fr.tartur.fcaf.libs.plugin.commands.data;

import fr.tartur.fcaf.libs.plugin.commands.CommandHolder;
import fr.tartur.fcaf.user.FPlayerManager;

public class CommandData {

    private final CommandHolder commandHolder;

    private FPlayerManager playerManager;
    private int index;

    public CommandData(CommandHolder commandHolder) {
        this.commandHolder = commandHolder;
    }

    public CommandData() {
        this(new CommandHolder());
    }

    public CommandHolder getCommandHolder() {
        return commandHolder;
    }

    public FPlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(FPlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
