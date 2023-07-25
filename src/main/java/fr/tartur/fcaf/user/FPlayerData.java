package fr.tartur.fcaf.user;

import fr.tartur.fcaf.libs.data.database.BaseDatabaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FPlayerData {

    private final FPlayer player;

    private int fazCoins;
    private int fazBadges;
    private int experience;

    public FPlayerData(FPlayer player) {
        this.player = player;

        this.fazCoins = 0;
        this.fazBadges = 0;
        this.experience = 0;
    }

    public void pull(BaseDatabaseConnection usersDatabase) {
        try {
            PreparedStatement pullRequest = usersDatabase.get().prepareStatement("SELECT fazcoins, fazbadges, experience FROM " +
                    usersDatabase.getUsersTableName() + " WHERE uuid = ?");
            pullRequest.setString(1, player.bukkit().getUniqueId().toString());

            ResultSet resultSet = pullRequest.executeQuery();

            if (resultSet.next()) {
                this.fazCoins = resultSet.getInt(1);
                this.fazBadges = resultSet.getInt(2);
                this.experience = resultSet.getInt(3);
            } else {
                update(usersDatabase, true);
            }
        } catch (SQLException e) {
            this.player.bukkit().sendMessage("§cUne erreur est survenue lors de la récupération de vos " +
                    "données. Pour plus d'informations, §acontactez un §eadministrateur§c.");
            throw new RuntimeException(e);
        }
    }

    public void update(BaseDatabaseConnection usersDatabase, boolean fillAllColumns) {
        try {
            PreparedStatement update;
            if (fillAllColumns) {
                update = usersDatabase.get().prepareStatement("INSERT INTO " + usersDatabase.getUsersTableName() + " VALUES (?, ?, ?, ?, ?, ?, ?)");
                update.setString(1, this.player.bukkit().getUniqueId().toString());
                update.setString(2, this.player.bukkit().getName());
                update.setInt(3, this.fazCoins);
                update.setInt(4, this.fazBadges);
                update.setInt(5, this.experience);
                update.setDate(6, new Date(System.currentTimeMillis()));
                update.setDate(7, new Date(System.currentTimeMillis()));
            } else {
                System.out.println("UPDATE");
                update = usersDatabase.get().prepareStatement("""
                    UPDATE %s
                    SET fazcoins = ?,
                        fazbadges = ?,
                        experience = ?,
                        lastconnexion = ?
                    WHERE uuid = ?
                """.formatted(usersDatabase.getUsersTableName()));
                update.setInt(1, this.fazCoins);
                update.setInt(2, this.fazBadges);
                update.setInt(3, this.experience);
                update.setDate(4, new Date(System.currentTimeMillis()));
                update.setString(5, this.player.bukkit().getUniqueId().toString());
            }

            update.executeUpdate();
        } catch (SQLException e) {
            this.player.bukkit().sendMessage("§cUne erreur est survenue lors de la mise à jour de vos " +
                    "données. Pour plus d'informations, §acontactez un §eadministrateur§c.");
            throw new RuntimeException(e);
        }

    }

    public int getFazCoins() {
        return fazCoins;
    }

    public void setFazCoins(int fazCoins) {
        this.fazCoins = fazCoins;
    }

    public int getFazBadges() {
        return fazBadges;
    }

    public void setFazBadges(int fazBadges) {
        this.fazBadges = fazBadges;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void addFazCoins(int fazCoins) {
        this.fazCoins += fazCoins;
    }

    public void addFazBadges(int fazCoins) {
        this.fazBadges += fazCoins;
    }

    public void addExperience(int fazCoins) {
        this.experience += fazCoins;
    }
}
