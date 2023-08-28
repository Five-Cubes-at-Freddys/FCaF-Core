package fr.tartur.fcaf.plugin.commands.playerdata;

import fr.tartur.fcaf.common.log.MessageSender;
import fr.tartur.fcaf.common.log.MessageType;
import fr.tartur.fcaf.libs.plugin.commands.TargetedCommandRunner;
import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class BaseChangeDataCommand extends TargetedCommandRunner implements IDataModifier {
    /**
     * Class constructor.
     *
     * @param name This command name.
     */
    protected BaseChangeDataCommand(String name) {
        super(name);
    }

    /**
     * The command to be runned if no subcommands provided by the player have been set or found.
     *
     * @param sender The basic command sender.
     * @param args   The args entered by the player.
     * @return true if the command was correctly executed, false otherwise.
     */
    @Override
    protected boolean run(@NotNull CommandSender sender, String[] args) {
        if (args.length == 2) {
            FPlayer target = super.getData().getTarget();
            Player bTarget = target.bukkit();
            bTarget.sendMessage(this.getData().getPermission().getName());

            try {
                int amount = Integer.parseInt(args[0]);

                switch (args[1].toLowerCase()) {
                    case "fazcoins", "fc" -> {
                        onFazCoinsChange(amount, target, bTarget, sender);
                        return true;
                    }
                    case "fazbadges", "fb" -> {
                        onFazBadgesChange(amount, target, bTarget, sender);
                        return true;
                    }
                    case "experience", "exp", "xp" -> {
                        onExperienceChange(amount, target, bTarget, sender);
                        return true;
                    }
                    default -> {
                        MessageSender.tell(MessageType.ERROR, sender, "La donnée \"" + args[1] + "\" n'existe pas.");
                        return false;
                    }
                }
            } catch (NumberFormatException exception) {
                sender.sendMessage("§8[§4FCaF§8] §cLe nombre \"" + args[0] + "\" est incorrect.");
                return false;
            }
        }

        MessageSender.tell(MessageType.ERROR, sender, "Les sous-commandes entrées n'ont pas été trouvées.");
        return false;
    }
}
