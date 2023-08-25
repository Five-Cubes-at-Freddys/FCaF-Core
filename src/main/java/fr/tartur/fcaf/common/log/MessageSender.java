package fr.tartur.fcaf.common.log;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageSender {

    public static void tell(MessageType type, CommandSender sender, boolean playSound, String... messages) {
        StringBuilder message = new StringBuilder();

        message.append(type.getPrefix());
        for (String msg : messages) {
            message.append(type.getColor()).append(" ").append(msg);
        }

        if (messages.length > 0) {
            String lastArg = messages[messages.length - 1];
            char lastChar = lastArg.charAt(lastArg.length() - 1);

            if (Character.isDigit(lastChar) || Character.isLetter(lastChar)) {
                message.append(type.getColor()).append('.');
            }
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
