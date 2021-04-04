package cz.danix25.minihub.command;

import cz.danix25.minihub.Minihub;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameMode implements CommandExecutor {

    public static String prefix = "§7[§e§lGamemode§7]";
    public static String np = prefix + " §cNo Permissions";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(GameMode.prefix + "§7You are not player");
        }else{
            final Player player = ((Player)sender);

            if(player.hasPermission("minihub.gm.use")){
                if(args.length == 1){

                    if(args[0].equalsIgnoreCase("s")){
                        player.setGameMode(org.bukkit.GameMode.SURVIVAL);
                        player.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aSurvival");

                    }else if(args[0].equalsIgnoreCase("c")){
                        player.setGameMode(org.bukkit.GameMode.CREATIVE);
                        player.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aCreative");

                    }else if(args[0].equalsIgnoreCase("a")){
                        player.setGameMode(org.bukkit.GameMode.ADVENTURE);
                        player.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aAdventure");

                    } else if (args[0].equalsIgnoreCase("sp")){
                        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
                        player.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aSpectator");
                    }

                }else if(args.length == 2){
                    if(player.hasPermission("minihub.gm.use.other")){

                        final Player target = Bukkit.getPlayer(args[0]);

                        if(args[1].equalsIgnoreCase("0")){
                            target.setGameMode(org.bukkit.GameMode.SURVIVAL);
                            target.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aSurvival");
                            player.sendMessage(GameMode.prefix + " §r§7" + target.getName() +  "§7get gamemode §aSurvival");

                        }else if(args[1].equalsIgnoreCase("1")){
                            target.setGameMode(org.bukkit.GameMode.CREATIVE);
                            target.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aCreative");
                            player.sendMessage(GameMode.prefix + " §r§7" + target.getName() +  "§7get gamemode §aCreative");

                        }else if(args[1].equalsIgnoreCase("2")){
                            target.setGameMode(org.bukkit.GameMode.ADVENTURE);
                            target.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aAdventure");
                            player.sendMessage(GameMode.prefix + " §r§7" + target.getName() +  "§7get gamemode §aAdventure");

                        }else if(args[1].equalsIgnoreCase("3")){
                            target.setGameMode(org.bukkit.GameMode.SPECTATOR);
                            target.sendMessage(GameMode.prefix + " §r§7Your gamemode is §aSpectator");
                            player.sendMessage(GameMode.prefix + " §r§7" + target.getName() +  "§7get gamemode §aSpectator");

                        }

                    }else{
                        player.sendMessage(GameMode.np);
                    }
                }else{
                    player.sendMessage("§7[§e§lGamemode§7] §cUse: /gm c, gm s, gm a, gm sp");
                }
            }else{
                player.sendMessage(GameMode.np);
            }

        }

        return false;
    }

}