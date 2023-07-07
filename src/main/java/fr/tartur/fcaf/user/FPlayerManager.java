package fr.tartur.fcaf.user;

import fr.tartur.fcaf.libs.data.database.BaseDatabaseConnection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FPlayerManager {

    private final List<FPlayer> connectedPlayers;
    private final BaseDatabaseConnection usersDatabase;

    public FPlayerManager(BaseDatabaseConnection usersDatabase) {
        this.connectedPlayers = new ArrayList<>();
        this.usersDatabase = usersDatabase;
    }

    public FPlayer connectPlayer(Player player) {
        Optional<FPlayer> fcafPlayer = connectedPlayers.stream().filter(fPlayer -> fPlayer.bukkit().getUniqueId() == player.getUniqueId()).findAny();

        if (fcafPlayer.isPresent()) {
            return fcafPlayer.get();
        } else {
            FPlayer newFcafPlayer = new FPlayer(player);
            newFcafPlayer.getData().pull(usersDatabase);
            this.connectedPlayers.add(newFcafPlayer);
            return newFcafPlayer;
        }
    }

    public FPlayer getConnectedPlayer(Player player) {
        return connectedPlayers.stream().filter(fPlayer -> fPlayer.bukkit().getUniqueId() == player.getUniqueId()).findFirst().orElseThrow();
    }

    public void disconnectPlayer(FPlayer player) {
        if (this.connectedPlayers.contains(player)) {
            player.getData().update(this.usersDatabase, false);
            this.connectedPlayers.remove(player);
        }
    }

}
