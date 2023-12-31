package fr.tartur.fcaf.plugin.commands;

import fr.tartur.fcaf.libs.plugin.commands.CommandRunner;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandUsage;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TestCommand extends CommandRunner {
    /**
     * Default class constructor.
     */
    public TestCommand() {
        super("test", new CommandUsage.Builder()
                .setDescription("Commande de test, envoie un simple message")
                .build());
    }

    /**
     * The command to be runned if no subcommands provided by the player have been set or found.
     *
     * @param sender The basic command sender.
     * @param args   The args entered by the player.
     * @return true if the command was correctly executed, false otherwise.
     */
    @Override
    protected boolean run(@NotNull CommandSender sender, @NotNull String[] args) {
        sender.sendMessage("�aGG! You are the best coder!");
        return false;
    }
}
