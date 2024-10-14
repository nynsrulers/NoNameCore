package com.bowfun.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCounterCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command label, String command, String[] args) {
        String prefix = CoreTools.getInstance().getPrefix();

        if (!sender.hasPermission("nncore.cmd.daycounter")) {
            sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to do this!");
            return false;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + ChatColor.RED + "This command can only be used by players!");
            return false;
        }
        Player player = (Player) sender;
        World world = player.getWorld();

        long worldTimeInDays = (world.getFullTime() / 24000);

        sender.sendMessage(prefix + ChatColor.GREEN + "Day counter for " + ChatColor.LIGHT_PURPLE + world.getName() + ChatColor.GREEN + ": " + worldTimeInDays + "days");

        return false;
    }
}
