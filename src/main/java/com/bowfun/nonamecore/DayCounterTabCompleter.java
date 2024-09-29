package com.bowfun.nonamecore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class DayCounterTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("daycounter") || command.getName().equalsIgnoreCase("daycount") || command.getName().equalsIgnoreCase("wtime")) {
            if (args.length == 1) {
                // Suggesting worlds
                List<String> worldNames = new ArrayList<>();
                for (World world : Bukkit.getWorlds()) {
                    worldNames.add(world.getName());
                }
                return worldNames;
            }
        }
        return new ArrayList<>();
    }
}
