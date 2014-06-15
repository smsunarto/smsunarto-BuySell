package com.yahoo.smsunarto;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;


public class BuySell extends JavaPlugin
{
  public final Logger logger = Logger.getLogger("Minecraft");
  public static BuySell plugin;
  public static Economy econ = null;
  public static Permission perms = null;
  public static Chat chat = null;

  public void onEnable()
  {
    getLogger().info("By smsunarto And Bukkit Forum Team");
    PluginDescriptionFile pdfFile = getDescription();
    this.logger.info(pdfFile.getName() + "Has Been Enabled");
    setupPermissions();
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  public void onDisable()
  {
    PluginDescriptionFile pdfFile = getDescription();
    this.logger.info(pdfFile.getName() + "Has Been Disabled");
    saveConfig();
  }

  private boolean setupPermissions()
  {
    RegisteredServiceProvider rsp = getServer().getServicesManager().getRegistration(Permission.class);
    perms = (Permission)rsp.getProvider();
    return perms != null;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    Player player = (Player)sender;
    if (commandLabel.equalsIgnoreCase("buy")) {
      if (perms.has(player, "BuySell.Buy")) {
        if (args.length == 0) {
          sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + getConfig().getString("Prefix") + ChatColor.GOLD + "]" + ChatColor.RED + " Usage : /buy <Amount> <Item> <Price>");
          return true;
        }
        getServer().broadcastMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + getConfig().getString("Prefix") + ChatColor.GOLD + "]" + " " + ChatColor.AQUA + player.getName() + " " + ChatColor.YELLOW + "Want to buy " + ChatColor.GOLD + args[0] + " " + args[1] + " " + ChatColor.YELLOW + "for " + ChatColor.GREEN + args[2] + "$");
      } else {
        player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + getConfig().getString("Prefix") + ChatColor.GOLD + "]" + " " + ChatColor.RED + "You Do Not Have Permission To Use This Command");
      }
    }
    if (commandLabel.equalsIgnoreCase("sell")) {
      if (perms.has(player, "BuySell.Sell")) {
        if (args.length == 0) {
          sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + getConfig().getString("Prefix") + ChatColor.GOLD + "]" + ChatColor.RED + " Usage : /sell <Amount> <Item> <Price>");
          return true;
        }
        getServer().broadcastMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + getConfig().getString("Prefix") + ChatColor.GOLD + "]" + " " + ChatColor.AQUA + player.getName() + " " + ChatColor.YELLOW + "Want to sell " + ChatColor.GOLD + args[0] + " " + args[1] + " " + ChatColor.YELLOW + "for " + ChatColor.GREEN + args[2] + "$");
      } else {
        player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + getConfig().getString("Prefix") + ChatColor.GOLD + "]" + " " + ChatColor.RED + "You Do Not Have Permission To Use This Command");
      }
    }
    if (commandLabel.equalsIgnoreCase("buysellhelp")) {
      if (perms.has(player, "BuySell.Help")) {
        player.sendMessage(ChatColor.RED + "<-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=->");
        player.sendMessage(ChatColor.GOLD + "/buy <Item> <Price> | Buy Item");
        player.sendMessage(ChatColor.GOLD + "/sell <Item> <Price> | Sell Item");
        player.sendMessage(ChatColor.RED + "<-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=->");
      } else {
        player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + getConfig().getString("Prefix") + ChatColor.GOLD + "]" + " " + ChatColor.RED + "You Do Not Have Permission To Use This Command");
      }
    }

    return true;
  }
}