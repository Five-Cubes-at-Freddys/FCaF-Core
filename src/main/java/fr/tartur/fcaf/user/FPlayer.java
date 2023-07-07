package fr.tartur.fcaf.user;

import org.bukkit.entity.Player;

public class FPlayer {

    private final Player player;

    private final FPlayerData data;

    public FPlayer(Player player) {
        this.player = player;
        this.data = new FPlayerData(this);
    }

    public FPlayerData getData() {
        return data;
    }

    public Player bukkit() {
        return this.player;
    }
}
