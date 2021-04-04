package cz.danix25.minihub.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetSpawn implements CommandExecutor {
    public static String prefix = "§7[§e§lServer§7]";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
                             String[] args) {
        Player p = (Player) sender;

        if(p.hasPermission("minihub.lobby.set")){
            p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
            p.sendMessage(SetSpawn.prefix + "§7You just set the §aworld lobby.");
        } else {
            p.sendMessage(SetSpawn.prefix + "§cYou do not have permission to perform this command.");
        }
        return false;
    }



}
