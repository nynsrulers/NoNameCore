package com.bowfun.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        getCommand("daycounter").setExecutor(new DayCounterCMD());
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            List<String> welcomeMessage = getConfig().getStringList("WelcomeMessage");
            if (!welcomeMessage.getFirst().equals("DISABLED")) {
                for (String welcomeLine : welcomeMessage) {
                    if (welcomeLine.contains("%allplayers%")) {
                        if (getServer().getOnlinePlayers().size() > 1) {
                            welcomeLine = welcomeLine.replace("%allplayers%",
                                    getServer().getOnlinePlayers().stream()
                                            .map(Player::getName)
                                            .filter(name -> !name.equals(player.getName()))
                                            .collect(Collectors.joining(", "))
                            );
                        } else {
                            welcomeLine = welcomeLine.replace("%allplayers%", ChatColor.RED + "No one else!");
                        }
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(welcomeLine)));
                }
            }
        }, 60L);
    }
}
