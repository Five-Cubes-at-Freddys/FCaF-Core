package fr.tartur.fcaf.plugincomponents.commands;

import fr.tartur.fcaf.libs.plugincomponents.commands.TargetedCommandRunner;
import fr.tartur.fcaf.user.FPlayer;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FPlayerCommand extends TargetedCommandRunner {

    public FPlayerCommand(FPlayerManager players) {
        super("fplayer", -1, 0, players, new ChangeFPlayerData(players));
    }

    @Override
    protected boolean run(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player bPlayer) {
            FPlayer player = this.players.getConnectedPlayer(bPlayer);
            bPlayer.sendMessage("§bVos données §f:" +
                    "\n  §f- §6FC §f: §a" + player.data().getFazCoins() +
                    "\n  §f- §4FB §f: §a" + player.data().getFazBadges() +
                    "\n  §f- §bEXP §f: §a" + player.data().getExperience());
        }

        return true;
    }
}
