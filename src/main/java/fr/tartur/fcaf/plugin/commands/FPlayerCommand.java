package fr.tartur.fcaf.plugin.commands;

import fr.tartur.fcaf.libs.plugin.commands.TargetedCommandRunner;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandOptions;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandUsage;
import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FPlayerCommand extends TargetedCommandRunner {

    public FPlayerCommand() {
        super("fplayer", new CommandUsage.Builder()
                .setOptions(new CommandOptions.Builder()
                        .addOptionalOptions(new CommandOptions.Builder()
                                .addRequiredOptions("joueur")
                                .addRequiredOptions("add", "remove", "set")
                                .addRequiredOptions("montant")
                                .addRequiredOptions("fb", "fc", "exp")
                                .build())
                        .build())
                .setDescription("Voir/modifier les données du joueur ciblé")
                .build());
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
