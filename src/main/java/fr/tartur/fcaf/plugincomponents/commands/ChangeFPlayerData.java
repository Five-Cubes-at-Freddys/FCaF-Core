package fr.tartur.fcaf.plugincomponents.commands;

import fr.tartur.fcaf.common.log.MessageSender;
import fr.tartur.fcaf.common.log.MessageType;
import fr.tartur.fcaf.libs.plugincomponents.commands.TargetedCommandRunner;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class ChangeFPlayerData extends TargetedCommandRunner {

    public ChangeFPlayerData(FPlayerManager players) {
        super("add,remove,set,reset", 1, players);
    }

    @Override
    protected boolean run(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (args.length == 2) {
            Player bTarget = target.bukkit();

            switch (label) {
                case "add" -> {
                    try {
                        int amount = Integer.parseInt(args[0]);

                        switch (args[1].toLowerCase()) {
                            case "fazcoins", "fc" -> {
                                target.data().addFazCoins(amount);
                                MessageSender.tell(MessageType.SUCCESS, bTarget, "Vous avez reçu §6§l" + amount + " FC §a!", true);
                                MessageSender.tell(MessageType.SUCCESS, sender, "§6§l" + amount + " FC §aont bien été ajoutés à §e" + bTarget.getName() + "§a.");
                                return true;
                            }
                            case "fazbadges", "fb" -> {
                                target.data().addFazBadges(amount);
                                MessageSender.tell(MessageType.SUCCESS, bTarget, "Vous avez reçu §4§l" + amount + " FB §a!", true);
                                MessageSender.tell(MessageType.SUCCESS, sender, "§4§l" + amount + " FC §aont bien été ajoutés à §e" + bTarget.getName() + "§a.");
                                return true;
                            }
                            case "experience", "xp" -> {
                                target.data().addExperience(amount);
                                MessageSender.tell(MessageType.SUCCESS, bTarget, "Vous avez reçu §b§l" + amount + " EXP §a!", true);
                                MessageSender.tell(MessageType.SUCCESS, sender, "§b§l" + amount + " EXP §aont bien été ajoutés à §e" + bTarget.getName() + "§a.");
                                return true;
                            }
                            default -> {
                                MessageSender.tell(MessageType.ERROR, sender, "La donnée \"" + args[1] + "\" n'existe pas.");
                                return false;
                            }
                        }
                    } catch (NumberFormatException exception) {
                        sender.sendMessage("§8[§4FCaF§8] §cLe nombre \"" + args[0] + "\" est incorrect.");
                        return false;
                    }
                }

                default -> {
                    MessageSender.tell(MessageType.WARNING, sender, "Cette commande n'existe pas ou n'est toujours pas implémentée.", true);
                    return true;
                }
            }
        }

        MessageSender.tell(MessageType.ERROR, sender, "Les sous-commandes entrées n'ont pas été trouvées.");
        return false;
    }
}
