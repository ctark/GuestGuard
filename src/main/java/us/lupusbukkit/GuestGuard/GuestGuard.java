package us.lupusbukkit.GuestGuard;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class GuestGuard extends JavaPlugin implements Listener {
    public FileConfiguration options;
    public static GuestGuard plugin;
    
    
    @Override
    public void onDisable(){
       
        System.out.println(this + " is now disabled!");
    }
    @Override
        public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    @EventHandler
    public void StopPlace(BlockPlaceEvent event){
        boolean stopPlace = plugin.getConfig().getBoolean("stopPlace");
        Player player = event.getPlayer();
        if(stopPlace) {
        if(!player.hasPermission("guestguard.build")) {
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED+"You cannot place blocks!");
        }
        }
        }
    @EventHandler
    public void StopChat(PlayerChatEvent event){
        boolean stopChat = plugin.getConfig().getBoolean("stopChat");
        Player player = event.getPlayer();
        if(stopChat) {
        if(!player.hasPermission("guestguard.chat")) {
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED+"You cannot chat!");
        }
        }
    }
    @EventHandler
    public void StopBreak(BlockBreakEvent event){
        boolean stopBreak = plugin.getConfig().getBoolean("stopBreak");
        Player player = event.getPlayer();
        if(stopBreak){
        if(!player.hasPermission("guestguard.build")) {
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED+"You cannot destroy blocks!");
        }
        }
    }
    
    @EventHandler
    public void StopDrop(PlayerDropItemEvent event){
        boolean stopDrop = plugin.getConfig().getBoolean("stopDrop");
        boolean stopDropCreative = plugin.getConfig().getBoolean("stopDropCreative");
        Player player = event.getPlayer();
        if(stopDropCreative){
            if(player.getGameMode() == GameMode.CREATIVE){
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED+"You cannot drop items!");
            }
        }
        if(stopDrop){
        if(!player.hasPermission("guestguard.drop")) {
          event.setCancelled(true);
          player.sendMessage(ChatColor.RED+"You cannot drop items!"); 
        }
        }
    }
    
    @EventHandler
    public void StopPickup(PlayerPickupItemEvent event){
        boolean stopPickup = plugin.getConfig().getBoolean("stopPickup");
        Player player = event.getPlayer();
       if(stopPickup){
        if(!player.hasPermission("guestguard.pickup")) {
          event.setCancelled(true);
          player.sendMessage(ChatColor.RED+"You cannot pickup items!"); 
        }
        } 
    }
    }