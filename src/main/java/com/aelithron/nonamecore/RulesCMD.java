package com.aelithron.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCMD implements CommandExecutor {
    private NoNameCore plugin;
    public RulesCMD(NoNameCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = CoreTools.getInstance().getPrefix();
        sender.sendMessage(prefix + ChatColor.DARK_GRAY + "-- " + ChatColor.AQUA + "Server Rules" + ChatColor.DARK_GRAY + " --");
        int i = 1;
        for (String rule : plugin.getConfig().getStringList("Rules")) {
            sender.sendMessage(ChatColor.AQUA.toString() + i + ". " + ChatColor.translateAlternateColorCodes('&', rule));
            i++;
        }
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Please note, these can change at any time.");
        return false;
    }
}
