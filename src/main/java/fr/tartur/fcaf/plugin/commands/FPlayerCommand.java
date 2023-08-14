package fr.tartur.fcaf.plugin.commands;

import fr.tartur.fcaf.libs.plugin.commands.TargetedCommandRunner;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandOptionsBuilder;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandUsageBuilder;
import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FPlayerCommand extends TargetedCommandRunner {

    public FPlayerCommand() {
        super("fplayer", new CommandUsageBuilder()
                .setCommandName("fplayer")
                .setOptions(new CommandOptionsBuilder()
                        .addOptionalOptions(new CommandOptionsBuilder()
                                .addRequiredOptions("joueur")
                                .addRequiredOptions("add", "remove", "set", "reset")
                                .addRequiredOptions("montant", "fb", "fc", "exp")
                                .addRequiredOptions("fb", "fc", "exp")
                                .build())
                        .build())
                .setDescription("Voir/modifier les donn�es du joueur cibl�")
                .build());
    }

    @Override
    protected boolean run(@NotNull CommandSender sender, @NotNull String[] args) {
        if (sender instanceof Player bPlayer) {
            FPlayer player = super.getData().getPlayerManager().getConnectedPlayer(bPlayer);
            bPlayer.sendMessage("�bVos donn�es �f:" +
                    "\n  �f- �6FC �f: �a" + player.data().getFazCoins() +
                    "\n  �f- �4FB �f: �a" + player.data().getFazBadges() +
                    "\n  �f- �bEXP �f: �a" + player.data().getExperience());
        }

        return true;
    }
}
