package fr.tartur.fcaf.libs.plugincomponents.commands.data;

import fr.tartur.fcaf.libs.plugincomponents.commands.CommandHolder;
import fr.tartur.fcaf.user.FPlayer;

public class TargetedCommandData extends CommandData {

    private final int playerIndex;
    private FPlayer target;

    public TargetedCommandData(CommandHolder commandHolder, int playerIndex) {
        super(commandHolder);
        this.playerIndex = playerIndex;
    }

    public TargetedCommandData(CommandHolder commandHolder) {
        this(commandHolder, 0);
    }

    public TargetedCommandData(int playerIndex) {
        this(new CommandHolder(), playerIndex);
    }

    public TargetedCommandData() {
        this(0);
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public boolean hasTarget() {
        return this.target != null;
    }

    public FPlayer getTarget() {
        return target;
    }

    public void setTarget(FPlayer target) {
        this.target = target;
    }
}
