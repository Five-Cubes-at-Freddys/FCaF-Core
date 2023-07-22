package fr.tartur.fcaf.plugincomponents.commands;

import fr.tartur.fcaf.libs.plugincomponents.commands.TargetedCommandRunner;
import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FPlayerCommand extends TargetedCommandRunner {

    public FPlayerCommand() {
        super("fplayer");
    }

    @Override
    protected boolean run(@NotNull CommandSender sender, @NotNull String[] args) {
        if (sender instanceof Player bPlayer) {
            FPlayer player = super.getData().getPlayerManager().getConnectedPlayer(bPlayer);
            bPlayer.sendMessage("§bVos données §f:" +
                    "\n  §f- §6FC §f: §a" + player.data().getFazCoins() +
                    "\n  §f- §4FB §f: §a" + player.data().getFazBadges() +
                    "\n  §f- §bEXP §f: §a" + player.data().getExperience());
        }

        return true;
    }
}
