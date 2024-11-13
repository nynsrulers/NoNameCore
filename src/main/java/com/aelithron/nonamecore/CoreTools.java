package com.aelithron.nonamecore;

import org.bukkit.ChatColor;

import java.util.Objects;

public class CoreTools {
    private NoNameCore plugin;
    private static CoreTools instance;

    public static CoreTools getInstance() {
        if (instance == null) {
            instance = new CoreTools();
        }
        return instance;
    }

    public void setPlugin(NoNameCore plugin) {
        this.plugin = plugin;
    }

    public String getPrefix() {
        return (ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Prefix"))) + " ");
    }
}
