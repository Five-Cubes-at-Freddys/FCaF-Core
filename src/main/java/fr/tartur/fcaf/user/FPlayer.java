package fr.tartur.fcaf.user;

import org.bukkit.entity.Player;

public class FPlayer {

    private final Player player;

    private final FPlayerData fPlayerData;

    public FPlayer(Player player) {
        this.player = player;
        this.fPlayerData = new FPlayerData(this);
    }

    public FPlayerData data() {
        return fPlayerData;
    }

    public Player bukkit() {
        return this.player;
    }
}
