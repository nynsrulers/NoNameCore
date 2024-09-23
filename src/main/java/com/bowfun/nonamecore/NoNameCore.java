package com.bowfun.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NoNameCore extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Event Listener
        getServer().getPluginManager().registerEvents(this,this);
        // Some extra staging
        CoreTools.getInstance().setPlugin(this);
        // Commands
        getCommand("dayCounter").setExecutor(new DayCounterCMD(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            for (String welcomeLine : getConfig().getStringList("WelcomeMessage")) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(welcomeLine)));
            }
        }, 60L);
    }
}
