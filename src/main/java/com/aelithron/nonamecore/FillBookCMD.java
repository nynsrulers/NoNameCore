package com.aelithron.nonamecore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class FillBookCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = CoreTools.getInstance().getPrefix();
        if (!(sender instanceof Player player)) {
            sender.sendMessage(prefix + ChatColor.RED + "This command can only be used by players!");
            return false;
        }

        ItemStack book = player.getInventory().getItemInMainHand();
        ItemStack newBook = player.getInventory().getItemInOffHand();
        if (book.getType() != Material.WRITTEN_BOOK) {
            sender.sendMessage(prefix + ChatColor.RED + "You must have a book in your main hand!");
            return false;
        }
        if (newBook.getType() != Material.WRITABLE_BOOK) {
            sender.sendMessage(prefix + ChatColor.RED + "You must have a book and quill in your offhand!");
            return false;
        }

        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        BookMeta newBookMeta = (BookMeta) newBook.getItemMeta();
        assert bookMeta != null;
        assert newBookMeta != null;
        newBookMeta.setPages(bookMeta.getPages());
        newBook.setItemMeta(newBookMeta);
        player.getInventory().setItemInOffHand(newBook);
        sender.sendMessage(prefix + ChatColor.GREEN + "The book and quill in your offhand has been filled with the book in your main hand!");
        return true;
    }
}
