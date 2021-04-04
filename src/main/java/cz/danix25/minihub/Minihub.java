package cz.danix25.minihub;

import cz.danix25.minihub.command.*;
import cz.danix25.minihub.listener.DoubleJump;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;


public final class Minihub extends JavaPlugin implements Listener {

    private static Minihub instance;

static Plugin plugin;
private ArrayList<String> usingClock;

    @Override
    public void onEnable() {
        this.usingClock = new ArrayList<String>();
        getServer().getPluginManager().registerEvents(new DoubleJump(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("gm").setExecutor(new GameMode());
        getCommand("fly").setExecutor(new Fly());

        CommandExecutor lobby = new Spawn();
        getCommand("lobby").setExecutor(lobby);
        CommandExecutor setlobby = new SetSpawn();
        getCommand("setlobby").setExecutor(setlobby);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    //Compasss
    private void teleportInWorld(Player player, int x, int y, int z) {
        player.teleport(new Location(player.getWorld(),x,y,z));
    }

    public void openGUI (Player player) {
        Inventory i = Bukkit.createInventory(null, 45, ChatColor.YELLOW + "Server Selector");

        ItemStack Glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta GlassMeta = Glass.getItemMeta();

        ItemStack Bedwars = new ItemStack(Material.RED_BED);
        ItemMeta BedwarsMeta = Bedwars.getItemMeta();

        ItemStack Skywars = new ItemStack(Material.BOW);
        ItemMeta SkywarsMeta = Skywars.getItemMeta();

        ItemStack MurderMystery = new ItemStack(Material.IRON_SWORD);
        ItemMeta MurderMysteryMeta = MurderMystery.getItemMeta();

        GlassMeta.setDisplayName(ChatColor.GRAY + " ");
        Glass.setItemMeta(GlassMeta);

        BedwarsMeta.setDisplayName(ChatColor.RED + "Bedwars");
        ArrayList<String> bedwars = new ArrayList();
        bedwars.add("§fOnline: §a" + Bukkit.getOnlinePlayers().size());
        BedwarsMeta.setLore(bedwars);
        Bedwars.setItemMeta(BedwarsMeta);

        SkywarsMeta.setDisplayName(ChatColor.BLUE + "Skywars");
        ArrayList<String> skywars = new ArrayList();
        skywars.add("§fOnline: §a" + Bukkit.getOnlinePlayers().size());
        SkywarsMeta.setLore(skywars);
        Skywars.setItemMeta(SkywarsMeta);

        MurderMysteryMeta.setDisplayName(ChatColor.YELLOW + "MurderMystery");
        ArrayList<String> murder = new ArrayList();
        murder.add("§fOnline: §a" + Bukkit.getOnlinePlayers().size());
        MurderMysteryMeta.setLore(murder);
        MurderMystery.setItemMeta(MurderMysteryMeta);

        i.setItem(0, Glass);
        i.setItem(1, Glass);
        i.setItem(2, Glass);
        i.setItem(3, Glass);
        i.setItem(4, Glass);
        i.setItem(5, Glass);
        i.setItem(6, Glass);
        i.setItem(7, Glass);
        i.setItem(8, Glass);
        i.setItem(22, Skywars);
        i.setItem(20, Bedwars);
        i.setItem(24, MurderMystery);
        i.setItem(36, Glass);
        i.setItem(37, Glass);
        i.setItem(38, Glass);
        i.setItem(39, Glass);
        i.setItem(40, Glass);
        i.setItem(41, Glass);
        i.setItem(42, Glass);
        i.setItem(43, Glass);
        i.setItem(44, Glass);



        player.openInventory(i);
    }
    @EventHandler
    public void onItemDrop( PlayerDropItemEvent e ) {
        ArrayList<Material> blocked = new ArrayList<Material>();
        blocked.add(Material.COMPASS);
        if( blocked.contains(e.getItemDrop().getItemStack().getType()) ) {
            e.setCancelled(true);
            blocked.remove(e.getPlayer().getInventory());
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setKeepInventory(true);
        e.getDrops().clear();


        }


    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory().getType().equals("§e§lServer Selector")); {

            switch (e.getCurrentItem().getType()) {
                case RED_BED:
                    teleportInWorld(player, 100,50,100);
                    player.closeInventory();
                    e.setCancelled(true);

                    break;
                case BOW:
                    teleportInWorld(player, 150,50,100);
                    player.closeInventory();
                    e.setCancelled(true);

                    break;
                case IRON_SWORD:
                    teleportInWorld(player, 50,50,100);
                    player.closeInventory();
                    e.setCancelled(true);

                    break;
                case COMPASS:
                    e.setCancelled(true);
                    break;
                default:
                    e.setCancelled(true);
                    break;
            }
        }



    }



    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta CompassMeta = Compass.getItemMeta();
        ;
        CompassMeta.setDisplayName(ChatColor.YELLOW + "Server Selector");
        Compass.setItemMeta(CompassMeta);
        e.getPlayer().getInventory().addItem(Compass);

        ItemStack magicClock = new ItemStack(Material.LEGACY_WATCH, 1);
        ItemMeta magicClockMeta = magicClock.getItemMeta();
        magicClockMeta.setDisplayName("§a§lPlayer Hider");
        magicClock.setItemMeta(magicClockMeta);
        e.getPlayer().getInventory().addItem(magicClock);
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if( p != e.getPlayer()) {
                if(usingClock.contains(p.getName())) {
                    p.hidePlayer(e.getPlayer());
                }else {
                    p.showPlayer(e.getPlayer());
                }
            }
        }
    }


    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();

    }
    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Action a = e.getAction();
        if(a == Action.PHYSICAL || e.getItem() == null || e.getItem().getType() == Material.AIR)
            return;

        if(e.getItem().getType() == Material.COMPASS) {
            openGUI(e.getPlayer());
        }
        if(e.getAction() != Action.RIGHT_CLICK_AIR || e.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }
        if(e.getItem().getType() != Material.LEGACY_WATCH) {
            return;
        }
        if(
                !e.getItem().hasItemMeta() ||
                        !e.getItem().getItemMeta().hasDisplayName() ||
                        !e.getItem().getItemMeta().getDisplayName().equals("§a§lPlayer Hider")
        ) {
            return;
        }
        if(usingClock.contains(e.getPlayer().getName())) { //off
            usingClock.remove(e.getPlayer().getName());

            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if(p != e.getPlayer()) {
                    e.getPlayer().hidePlayer(p);
                }
            }
        }
        else {
            usingClock.add(e.getPlayer().getName()); //on

            for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                if(p != e.getPlayer()) {
                    e.getPlayer().hidePlayer(p);
                }
            }
        }
    }
    //Scoreboard
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        setScoreboard(e.getPlayer());


    }


    public void setScoreboard(Player p)  {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("Scoreboard", "");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("    §e§lMinihub 1.0   ");

        Score score = obj.getScore("          ");
        score.setScore(6);
        Score score1 = obj.getScore("§e§lNick:");
        score1 .setScore(5);
        Score score2  = obj.getScore(" " + p.getName());
        score2.setScore(4);
        Score score3 = obj.getScore("  ");
        score3.setScore(3);
        Score score4 = obj.getScore("§e§lRank:");
        score4.setScore(2);
        Score score5 = obj.getScore(" " + PlaceholderAPI.setPlaceholders(p,  "%luckperms_prefix%"));
        score5.setScore(1);
        Score score6 = obj.getScore(" ");
        score6.setScore(0);


        p.setScoreboard(board);


    }



}




