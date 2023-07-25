package fr.tartur.fcaf.plugin.commands.playerdata;

import fr.tartur.fcaf.common.log.MessageSender;
import fr.tartur.fcaf.common.log.MessageType;
import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddCommand extends BaseChangeDataCommand {

    public AddCommand() {
        super("add");
    }

    @Override
    public void onFazCoinsChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().addFazCoins(amount);
        MessageSender.tell(MessageType.SUCCESS, bTarget, true, "Vous avez reçu §6§l" + amount + " FC", "!");
        MessageSender.tell(MessageType.SUCCESS, sender, "§6§l" + amount + " FC", "ont bien été ajoutés à §e" + bTarget.getName());
    }

    @Override
    public void onFazBadgesChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().addFazBadges(amount);
        MessageSender.tell(MessageType.SUCCESS, bTarget, true, "Vous avez reçu §4§l" + amount + " FB", "!");
        MessageSender.tell(MessageType.SUCCESS, sender, "§4§l" + amount + " FB", "ont bien été ajoutés à §e" + bTarget.getName());
    }

    @Override
    public void onExperienceChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().addExperience(amount);
        MessageSender.tell(MessageType.SUCCESS, bTarget, true, "Vous avez reçu §b§l" + amount + " EXP", "!");
        MessageSender.tell(MessageType.SUCCESS, sender, "§b§l" + amount + " EXP", "ont bien été ajoutés à §e" + bTarget.getName());
    }
}
