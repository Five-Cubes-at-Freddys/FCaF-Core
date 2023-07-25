package fr.tartur.fcaf.plugin.events;

import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvents implements Listener {

    private final FPlayerManager playerManager;


    public QuitEvents(FPlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.playerManager.disconnectPlayer(player);
        event.setQuitMessage("§f[§4-§f] §e" + player.getName());
    }

}
