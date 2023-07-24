package fr.tartur.fcaf.common.log;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

public class MessageSender {

    public static void tell(MessageType type, CommandSender sender, boolean playSound, String... messages) {
        StringBuilder message = new StringBuilder();

        message.append(type.getPrefix());
        for (String msg : messages) {
            message.append(type.getColor()).append(" ").append(msg);
        }

        sender.sendMessage(message.toString());

        if (playSound) {
            if (sender instanceof Player player) {
                player.playSound(player.getLocation(), type.getSound(), 3.0f, type.getPitch());
            }
        }
    }

    public static void tell(MessageType type, CommandSender sender, String... messages) {
        tell(type, sender, false, messages);
    }

}
