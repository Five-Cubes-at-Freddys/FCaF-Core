package fr.tartur.fcaf.plugincomponents.commands.playerdata;

import fr.tartur.fcaf.common.log.MessageSender;
import fr.tartur.fcaf.common.log.MessageType;
import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveCommand extends BaseChangeDataCommand {

    public RemoveCommand() {
        super("remove");
    }

    @Override
    public void onFazCoinsChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().addFazCoins(-amount);
        MessageSender.tell(MessageType.ERROR, bTarget, true, "§6§l" + amount + " FC", "ont été supprimés de votre compte");
        MessageSender.tell(MessageType.SUCCESS, sender, "§6§l" + amount + " FC", "ont bien été supprimés du compte de §e" + bTarget.getName());
    }

    @Override
    public void onFazBadgesChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().addFazBadges(-amount);
        MessageSender.tell(MessageType.ERROR, bTarget, true, "§4§l" + amount + " FB", "ont été supprimés de votre compte");
        MessageSender.tell(MessageType.SUCCESS, sender, "§4§l" + amount + " FB", "ont bien été supprimés du compte de §e" + bTarget.getName());
    }

    @Override
    public void onExperienceChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().addExperience(-amount);
        MessageSender.tell(MessageType.ERROR, bTarget, true, "§b§l" + amount + " EXP", "ont été supprimés de votre compte");
        MessageSender.tell(MessageType.SUCCESS, sender, "§b§l" + amount + " EXP", "ont bien été supprimés du compte de §e" + bTarget.getName());
    }
}
