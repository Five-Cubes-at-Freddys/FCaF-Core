package fr.tartur.fcaf.plugincomponents.commands;

import fr.tartur.fcaf.libs.plugincomponents.commands.CommandRunner;
import fr.tartur.fcaf.user.FPlayer;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FPlayerCommand extends CommandRunner {

    public FPlayerCommand(FPlayerManager players) {
        super(
                "fplayer",
                -1,
                players
        );
    }

    @Override
    protected boolean run(@NotNull CommandSender sender, @NotNull String[] args) {
        if (sender instanceof Player bPlayer) {
            FPlayer player = this.players.getConnectedPlayer(bPlayer);
            bPlayer.sendMessage("§bVos données §f:" +
                    "\n  §f- §6FC §f: §a" + player.data().getFazCoins() +
                    "\n  §f- §4FB §f: §a" + player.data().getFazBadges() +
                    "\n  §f- §dEXP §f: §a" + player.data().getExperience());
        }

        return true;
    }
}
