package cz.danix25.minihub.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    public static String prefix = "§7[§e§lFly§7]";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("minihub.fly.use")){
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (args.length == 0) {
                player.sendMessage(Fly.prefix + " Use: /fly on:off");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    player.setAllowFlight(true);
                    player.sendMessage(Fly.prefix + " §7Fly mode §aenabled");
                } else if (args[0].equalsIgnoreCase("off")) {
                    player.setAllowFlight(false);
                    player.sendMessage(Fly.prefix + " §7Fly mode §cdisabled");
                }
            }
            }
        }
        return false;
    }

}

