package com.aelithron.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

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
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Book mode setup things
            ItemStack ruleBook = new ItemStack(Material.WRITABLE_BOOK);
            BookMeta ruleMeta = (BookMeta) ruleBook.getItemMeta();
            ruleMeta.setGeneration(BookMeta.Generation.ORIGINAL);
            ruleMeta.setAuthor("Server Managers");
            ruleMeta.setTitle("Server Rules");
            ruleMeta.addPage(
                    ChatColor.DARK_GRAY + "---- " + ChatColor.AQUA + "Server Rules" + ChatColor.DARK_GRAY + " ----",
                    ChatColor.GRAY + "One rule per page of the book.",
                    ChatColor.LIGHT_PURPLE + "Please note, these can change at any time.");
            for (String rule : plugin.getConfig().getStringList("Rules")) {
                ruleMeta.addPage(ChatColor.AQUA.toString() + i + ". " + ChatColor.translateAlternateColorCodes('&', rule));
                i++;
            }
            ruleBook.setItemMeta(ruleMeta);
            player.openBook(ruleBook);
        } else {
            for (String rule : plugin.getConfig().getStringList("Rules")) {
                sender.sendMessage(ChatColor.AQUA.toString() + i + ". " + ChatColor.translateAlternateColorCodes('&', rule));
                i++;
            }
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Please note, these can change at any time.");
        }
        return true;
    }
}