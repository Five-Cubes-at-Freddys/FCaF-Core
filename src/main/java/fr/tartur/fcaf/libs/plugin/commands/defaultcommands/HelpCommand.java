package fr.tartur.fcaf.libs.plugin.commands.defaultcommands;

import fr.tartur.fcaf.common.log.MessageSender;
import fr.tartur.fcaf.common.log.MessageType;
import fr.tartur.fcaf.libs.plugin.commands.BaseCommand;
import fr.tartur.fcaf.libs.plugin.commands.CommandRunner;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandOptionsBuilder;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandUsageBuilder;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class HelpCommand extends CommandRunner {

    private final List<BaseCommand> commands;

    /**
     * Default class constructor.
     */
    public HelpCommand(String name, List<BaseCommand> commands) {
        super(name, new CommandUsageBuilder()
                .setCommandName(name)
                .setOptions(new CommandOptionsBuilder()
                        .addOptionalOptions("commande")
                        .build())
                .setDescription("Commande d'aide")
                .build());

        this.commands = commands;
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
        if (args.length != 1) {
            MessageSender.tell(MessageType.NOTE, sender, "Voici la liste des commandes disponibles du serveur :");
            this.commands.forEach(command -> sender.sendMessage("§f- " + command.getCommandUsage()));
        } else {
            Optional<BaseCommand> foundCommand = this.commands.stream()
                    .filter(command -> command.getName().equalsIgnoreCase(args[0]))
                    .findAny();

            if (foundCommand.isPresent()) {
                sender.sendMessage(foundCommand.get().getCommandUsage());
            } else {
                MessageSender.tell(MessageType.ERROR, sender, "La commande recherchée n'a pas pu être trouvée.");
                return false;
            }
        }

        return true;
    }
}
