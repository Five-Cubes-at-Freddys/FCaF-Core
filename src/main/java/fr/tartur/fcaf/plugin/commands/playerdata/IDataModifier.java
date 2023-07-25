package fr.tartur.fcaf.plugin.commands.playerdata;

import fr.tartur.fcaf.user.FPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface IDataModifier {
    void onFazCoinsChange(int amount, FPlayer target, Player bTarget, CommandSender sender);
    void onFazBadgesChange(int amount, FPlayer target, Player bTarget, CommandSender sender);
    void onExperienceChange(int amount, FPlayer target, Player bTarget, CommandSender sender);
}
