package fr.tartur.fcaf.plugincomponents.commands.playerdata;

import fr.tartur.fcaf.common.log.MessageSender;
import fr.tartur.fcaf.common.log.MessageType;
import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand extends BaseChangeDataCommand {
    /**
     * Class constructor.
     */
    public SetCommand() {
        super("set");
    }

    @Override
    public void onFazCoinsChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().setFazCoins(amount);
        MessageSender.tell(MessageType.NOTE, bTarget, true, "Vous avez désormais §6§l" + amount + " FC");
        MessageSender.tell(MessageType.SUCCESS, sender, "§e" + bTarget.getName(), "a désormais §6§l" + amount + " FC");
    }

    @Override
    public void onFazBadgesChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().setFazBadges(amount);
        MessageSender.tell(MessageType.NOTE, bTarget, true, "Vous avez désormais §4§l" + amount + " FB");
        MessageSender.tell(MessageType.SUCCESS, sender, "§e" + bTarget.getName(), "a désormais §4§l" + amount + " FB");
    }

    @Override
    public void onExperienceChange(int amount, FPlayer target, Player bTarget, CommandSender sender) {
        target.data().setExperience(amount);
        MessageSender.tell(MessageType.NOTE, bTarget, true, "Vous avez désormais §b§l" + amount + " EXP");
        MessageSender.tell(MessageType.SUCCESS, sender, "§e" + bTarget.getName(), "a désormais §b§l" + amount + " EXP");
    }
}
