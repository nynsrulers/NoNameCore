package com.aelithron.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class ManageCMD implements CommandExecutor {
    private NoNameCore plugin;
    public ManageCMD(NoNameCore plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = CoreTools.getInstance().getPrefix();

        if (!sender.hasPermission("nncore.manage")) {
            sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to do this!");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(prefix + ChatColor.GREEN + "To see what you can do, run /nncore check.");
            return false;
        }

        if (args[0].equalsIgnoreCase("check")) {
            sender.sendMessage(prefix + ChatColor.DARK_AQUA + "Checking possible actions...");
            // Map of permissions and features
            Map<String, String> permissionMap = new HashMap<>();
            permissionMap.put("nncore.manage.reload", "/nncore reload: Reloads the plugin");

            boolean hasPermission = false;

            for (Map.Entry<String, String> entry : permissionMap.entrySet()) {
                if (sender.hasPermission(entry.getKey())) {
                    sender.sendMessage(ChatColor.AQUA + entry.getValue());
                    hasPermission = true;
                }
            }

            if (!hasPermission) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to perform any actions.");
                sender.sendMessage(ChatColor.AQUA + "This is likely a misconfiguration, as you have management permissions but no feature permissions.");
            }
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("nncore.manage.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
                return true;
            } else {
                noPermission(sender, prefix);
                return false;
            }
        }
        sender.sendMessage(prefix + ChatColor.RED + "Incorrect usage!");
        sender.sendMessage(ChatColor.GREEN + "To see what you can do, run /nncore check.");
        return false;
    }

    private void noPermission(CommandSender sender, String prefix) {
        sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to do this!");
        sender.sendMessage(ChatColor.GREEN + "To see what you can do, run /nncore check.");
    }
}