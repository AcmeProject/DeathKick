package net.poweredbyhate.deathkick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathKick extends JavaPlugin implements Listener {

    String time;
    String banMessage;

    public void onEnable() {
        saveDefaultConfig();
        saveConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        time = getConfig().getString("Ban-Time");
        banMessage = getConfig().getString("Ban-Message");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent ev) {
        Player p = ev.getEntity();
        if (!p.hasPermission("deathkick.bypass")) {
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', banMessage).replace("%time%", time));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + p.getName() + " " + time);//Assuming everyone has essentials
        }
    }


}
