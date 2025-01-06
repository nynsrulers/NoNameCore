package com.aelithron.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

public class NukeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(CoreTools.getInstance().getPrefix() + ChatColor.RED + "This command can only be used by players!");
            return false;
        }
        if (!sender.hasPermission("nncore.cmd.nuke")) {
            sender.sendMessage(CoreTools.getInstance().getPrefix() + ChatColor.RED + "You don't have permission to do this!");
            return false;
        }
        if (args.length == 0) {
            Location nukeSpawn = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 10, player.getLocation().getZ());
            player.getWorld().strikeLightning(nukeSpawn);
            TNTPrimed tnt = player.getWorld().spawn(nukeSpawn, TNTPrimed.class);
            tnt.setFuseTicks(5);
            tnt.setYield(20);
            for (int i = 0; i < 20; i++) {
                TNTPrimed nukeTNT = player.getWorld().spawn(nukeSpawn, TNTPrimed.class);
                nukeTNT.setFuseTicks(40);
                nukeTNT.setYield(12);
            }
            sender.sendMessage(CoreTools.getInstance().getPrefix() + ChatColor.GREEN + "Nuke has been triggered!");
            return true;
        }
        return false;
    }
}
