package fr.tartur.fcaf.plugincomponents.events;

import fr.tartur.fcaf.user.FPlayer;
import fr.tartur.fcaf.user.FPlayerData;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvents implements Listener {

    private final FPlayerManager connectedFPlayers;

    public JoinEvents(FPlayerManager connectedFPlayers) {
        this.connectedFPlayers = connectedFPlayers;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FPlayer fPlayer = this.connectedFPlayers.connectPlayer(player);
        FPlayerData data = fPlayer.data();

        player.sendMessage("§aBonjour, §e" + player.getName() + " §a!\n§6Tu as §c" + data.getFazCoins() + " FC§6, " +
                "§c" + data.getFazBadges() + " FB §6et §c" + data.getExperience() + " EXP§6.");
    }

}
