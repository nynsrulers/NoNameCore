package com.bowfun.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCounterCMD implements CommandExecutor {
    private NoNameCore plugin;
    public DayCounterCMD(NoNameCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command label, String command, String[] args) {
        String prefix = CoreTools.getInstance().getPrefix();
        World world = null;

        if (!sender.hasPermission("nncore.cmd.daycounter")) {
            sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to do this!");
            return false;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + ChatColor.RED + "Incorrect usage!");
                sender.sendMessage(ChatColor.DARK_AQUA + "Usage: /daycounter <world name>");
                return false;
            }
            world = ((Player) sender).getWorld();
        } else {
            World checkWorld = plugin.getServer().getWorld(args[1]);
            if (checkWorld == null) {
                sender.sendMessage(prefix + ChatColor.RED + "Invalid world name! (" + args[1] + ")");
                return false;
            } else {
                world = checkWorld;
            }
        }

        long worldTimeInDays = (world.getFullTime() / 24000);

        sender.sendMessage(prefix + ChatColor.GREEN + "Day counter for " + ChatColor.LIGHT_PURPLE + world.getName() + ChatColor.GREEN + ": " + worldTimeInDays + "days");

        return false;
    }
}
