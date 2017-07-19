package tk.thediamondpicks.sc;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.plugin.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.*;
import org.bukkit.*;

public class Chat extends JavaPlugin implements Listener{


	public void onEnable() {
		this.saveDefaultConfig();
		this.getLogger().info("Ultra Simple Staff Chat has successfully started! Found " + getConfig().getConfigurationSection("chats").getKeys(false).size() + "chats");
        getServer().getPluginManager().registerEvents(this, this);
        loadConfigFile();

	}
    private void loadConfigFile() {
        saveDefaultConfig();
        if (getConfig().getConfigurationSection("formats") != null) {
            getConfig().createSection("chats");
            getConfig().set("chats.helper.format", getConfig().get("formats.helper"));
            getConfig().set("chats.moderator.format", getConfig().get("formats.moderator"));
            getConfig().set("chats.modplus.format", getConfig().get("formats.modplus"));
            getConfig().set("chats.admin.format", getConfig().get("formats.admin"));
            getConfig().set("chats.developer.format", getConfig().get("formats.developer"));
            getConfig().set("chats.builder.format", getConfig().get("formats.builder"));
            getConfig().set("chats.heads.format", getConfig().get("formats.heads"));
            getConfig().set("chats.staff.format", getConfig().get("formats.staff"));
            getConfig().createSection("options");
            getConfig().set("options.allow-colour-codes", true);
            getConfig().set("options.console-username", "&4CONSOLE");
            getConfig().set("options.no-permission-message", "&cYou do not have permission to perform that command.");
            getConfig().set("options.not-console-message", "&cConsole use only!");
            getConfig().set("chats.helper.command", "hc");
            getConfig().set("chats.helper.permission", "staffchat.helper");
            getConfig().set("chats.moderator.command", "mc");
            getConfig().set("chats.moderator.permission", "staffchat.mod");
            getConfig().set("chats.modplus.command", "mpc");
            getConfig().set("chats.modplus.permission", "staffchat.modplus");
            getConfig().set("chats.admin.command", "ac");
            getConfig().set("chats.admin.permission", "staffchat.admin");
            getConfig().set("chats.developer.command", "dc");
            getConfig().set("chats.developer.permission", "staffchat.dev");
            getConfig().set("chats.builder.command", "bc");
            getConfig().set("chats.builder.permission", "staffchat.builder");
            getConfig().set("chats.heads.command", "hdc");
            getConfig().set("chats.heads.permission", "staffchat.heads");
            getConfig().set("chats.staff.command", "sc");
            getConfig().set("chats.staff.permission", "staffchat.staff");
            getConfig().set("formats", null);
            saveConfig();
        }

    }

	public boolean onCommand(final CommandSender s, final Command cmd, final String label, final String[] args) {
        ConfigurationSection k = getConfig().getConfigurationSection("chats");
	    if (cmd.getName().equalsIgnoreCase("csc")) {
            if (s instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    s.sendMessage(ChatColor.RED + "Incorrect Usage! Use" + ChatColor.WHITE + "/csc <chat command> <message>");
                } else {
                    for (String command : k.getKeys(false)) {
                        String comd = getConfig().getString("chats." + command + ".command");
                        if (args[0].equalsIgnoreCase(comd)) {
                            String permission = getConfig().getString("chats." + command + ".permission");
                            String format = getConfig().getString("chats." + command + ".format");
                            String ps = getConfig().getString("options.console-username");
                            String msg = "";

                            for (int i = 1; i < args.length; ++i) {
                                msg = String.valueOf(msg) + args[i] + ' ';
                            }
                            format = format.replace("%PLAYER%", ps);
                            format = this.format(format);
                            format = format.replace("%MSG%", msg);
                            if (getConfig().getBoolean("options.allow-colour-codes")) {
                                format = this.format(format);
                            }

                            for (Player player : this.getServer().getOnlinePlayers()) {
                                if (player.hasPermission(permission)) {
                                    player.sendMessage(format);
                                }
                            }
                            getServer().getConsoleSender().sendMessage(format);
                        }
                    }

                }
            } else if (s instanceof Player) {
                String message = getConfig().getString("options.not-console-message");
                message = format(message);
                s.sendMessage(message);
            }
        }

            if (cmd.getName().equalsIgnoreCase("screload")) {
                if (s.hasPermission("staffchat.reload")) {
                    this.reloadConfig();
                    s.sendMessage(ChatColor.DARK_AQUA + "[Ultra Simple Staff Chat] " + ChatColor.AQUA + "The configuration was reloaded! Found " + ChatColor.WHITE + getConfig().getConfigurationSection("chats").getKeys(false).size() + ChatColor.AQUA+ " chats");
                }
                else {
                    String message = getConfig().getString("options.no-permission-message");
                    message = format(message);
                    s.sendMessage(message);
                }
            }
            return true;
        }


    @EventHandler
    public void onPreprocess(PlayerCommandPreprocessEvent e) {
        ConfigurationSection s = getConfig().getConfigurationSection("chats");

        for (String command : s.getKeys(false)) {
            String cmd = getConfig().getString("chats." + command + ".command");
            String[] args = e.getMessage().split(" ");
            if (args[0].equalsIgnoreCase("/" + cmd)) {
                String permission = getConfig().getString("chats." + command + ".permission");
                String format = getConfig().getString("chats." + command + ".format");



                Player m = e.getPlayer();
                if (m.hasPermission(permission)) {
                    if (args.length == 1) {
                        m.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/" + cmd + " <message>");
                    } else {
                        String ps = m.getName();
                        String msg = "";

                        for (int i = 1; i < args.length; ++i) {
                            msg = String.valueOf(msg) + args[i] + ' ';
                        }
                        format = format.replace("%PLAYER%", ps);
                        format = this.format(format);
                        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
                        format = PlaceholderAPI.setPlaceholders(m, format);
                        format = format.replace("%MSG%", msg);

                        if (getConfig().getBoolean("options.allow-colour-codes")) {
                            format = this.format(format);
                        }


                        for (Player player : this.getServer().getOnlinePlayers()) {
                            if (player.hasPermission(permission)) {
                                player.sendMessage(format);
                            }
                        }
                        getServer().getConsoleSender().sendMessage(format);
                    }


                }
                else {
                    String message = getConfig().getString("options.no-permission-message");
                    message = format(message);
                    e.getPlayer().sendMessage(message);
                }
                e.setCancelled(true);
            }

            //if (!s.getKeys(false).contains(e.getMessage().replace("/", ""))) {
            //    e.setCancelled(false);
            //}
        }
    }


    private String format ( final String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }


}
