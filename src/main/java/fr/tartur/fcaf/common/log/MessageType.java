package fr.tartur.fcaf.common.log;

import org.bukkit.Sound;

public enum MessageType {

    SUCCESS("§6[§2FCaF§6]", "§a", Sound.ENTITY_PLAYER_LEVELUP, 3.0f),
    NOTE("§f[§bFCaF§f]", "§b", Sound.BLOCK_NOTE_BLOCK_PLING, 1.5f),
    WARNING("§6[§eFCaF§6]", "§e", Sound.BLOCK_ANVIL_PLACE, 2.0f),
    ERROR("§8[§4FCaF§8]", "§c", Sound.ENTITY_CAT_AMBIENT, 0.5f);

    private final String prefix;
    private final String color;
    private final Sound sound;
    private final float pitch;

    MessageType(String prefix, String color, Sound sound, float pitch) {
        this.prefix = prefix;
        this.color = color;
        this.sound = sound;
        this.pitch = pitch;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColor() {
        return color;
    }

    public Sound getSound() {
        return sound;
    }

    public float getPitch() {
        return pitch;
    }
}
