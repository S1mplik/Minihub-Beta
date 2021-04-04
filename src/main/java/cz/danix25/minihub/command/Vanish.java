package cz.danix25.minihub.command;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Vanish implements CommandExecutor {
    static ArrayList<Player> vanish = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("vanish")) {
                if (p.hasPermission("vanish.use")) {
                    if (vanish.contains(p)) {
                        vanish.remove(p);
                        p.spigot().sendMessage(
                            ChatMessageType.ACTION_BAR,
                            new TextComponent("You are §avanish"));

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(p);
                        }
                    } else {
                        vanish.add(p);

                        p.spigot().sendMessage(
                                ChatMessageType.ACTION_BAR,

                                new TextComponent("You are §cunvanish"));

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.hidePlayer(p);
                        }

                    }
                }
            }
        }
        return false;

    }
}



//player.spigot().sendMessage(
//  ChatMessageType.ACTION_BAR,
//  new TextComponent("This message will be in the Action Bar"));
