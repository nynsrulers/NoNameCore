package com.nynsrulers.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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
        getCommand("nncore").setExecutor(new ManageCMD(this));
        getCommand("rules").setExecutor(new RulesCMD(this));
        getCommand("fillbook").setExecutor(new FillBookCMD());
        // Update checker
        CoreTools.getInstance().checkForUpdates();
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

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e) {
        // France joke
        if ((e.getMessage().equalsIgnoreCase("France") || e.getMessage().equalsIgnoreCase("French")) && (getConfig().getBoolean("FranceJoke"))) {
            e.getPlayer().sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "FRANCE ISN'T REAL");
        }
    }
}