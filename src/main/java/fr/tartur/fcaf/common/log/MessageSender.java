package fr.tartur.fcaf.common.log;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

public class MessageSender {

    public static void tell(MessageType type, CommandSender sender, String message, boolean playSound) {
        sender.sendMessage(type.toString() + message);

        if (playSound) {
            if (sender instanceof Player player) {
                player.playSound(player.getLocation(), switch (type) {
                    case SUCCESS -> Sound.ENTITY_PLAYER_LEVELUP;
                    case NOTE -> Sound.BLOCK_NOTE_BLOCK_PLING;
                    case WARNING -> Sound.BLOCK_NOTE_BLOCK_SNARE;
                    case ERROR -> Sound.ENTITY_CAT_DEATH;
                }, 3.0f, 3.0f);
            }
        }
    }

    public static void tell(MessageType type, CommandSender sender, String message) {
        tell(type, sender, message, false);
    }

}
