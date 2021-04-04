package cz.danix25.minihub.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    public static String prefix = "§7[§e§lServer§7]";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {

        Player p = (Player) sender;
        if(p.hasPermission("minihub.lobby.tp")){
            p.teleport(Bukkit.getServer().getWorld("world").getSpawnLocation());
            p.sendMessage(Spawn.prefix + "§7You have been teleported.");
        } else {
            p.sendMessage(Spawn.prefix + "§cYou do not have permission to perform this command.");
        }

        return false;
    }

}
