package tk.thediamondpicks.sc;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.plugin.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.*;
import org.bukkit.*;

public class Chat extends JavaPlugin implements Listener {
	private HashMap<String, Boolean> toggle;

	public Chat() {
		super();
		this.toggle = new HashMap<String, Boolean>();
	}

	public void onEnable() {
		this.getServer().getPluginManager()
				.registerEvents((Listener) this, (Plugin) this);
		this.getLogger().info("Server Staff Chat has successfully started!");
		
	}

	public boolean onCommand(final CommandSender s, final Command cmd,
			final String label, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("modchat") && s instanceof Player) {
			final Player m = (Player) s;
			if (m.hasPermission("staffchat.mod")) {
				if (args.length == 0) {
					m.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/mc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.LIGHT_PURPLE + "<" 
							+ m.getName() + ChatColor.LIGHT_PURPLE + "> " + msg;
					this.sendmods(path);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("adminchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.admin")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/ac <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.RED + "<" 
							+ a.getName() + ChatColor.RED + "> " + msg;
					this.sendadmins(path);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("helperchat") && s instanceof Player) {
			final Player h = (Player) s;
			if (h.hasPermission("staffchat.helper")) {
				if (args.length == 0) {
					h.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/hc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.AQUA + "<" 
							+ h.getName() + ChatColor.AQUA + "> " + msg;
					this.sendhelpers(path);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("modpluschat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.modplus")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/mpc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.DARK_PURPLE + "<" 
							+ a.getName() + ChatColor.DARK_PURPLE + "> " + msg;
					this.sendmodplus(path);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("devchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.dev")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/dc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.BLUE + "<" 
							+ a.getName() + ChatColor.BLUE + "> " + msg;
					this.senddevs(path);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("headchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.heads")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/hdc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.YELLOW + "<" 
							+ a.getName() + ChatColor.YELLOW + "> " + msg;
					this.sendheads(path);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("builderchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.builder")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/bc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.GREEN + "<" 
							+ a.getName() + ChatColor.GREEN + "> " + msg;
					this.sendbuilders(path);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("staffchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.staff")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use "
							+ ChatColor.WHITE + "/sc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String path = ChatColor.GOLD + "<" 
							+ a.getName() + ChatColor.GOLD + "> " + msg;
					this.sendallstaff(path);
				}
			}
		}
		return true;
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e) {
		this.toggle.put(e.getPlayer().getName(), false);
	}

	private void sendmods(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.mod")) {
				player.sendMessage(msg);
			}
		}
	}

	private void sendadmins(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.admin")) {
				player.sendMessage(msg);
			}
		}
	}

	private void sendhelpers(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.helper")) {
				player.sendMessage(msg);
			}
		}
	}
	private void sendmodplus(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.modplus")) {
				player.sendMessage(msg);
			}
		}
	}
	private void senddevs(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.dev")) {
				player.sendMessage(msg);
			}
		}
	}
	private void sendbuilders(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.builder")) {
				player.sendMessage(msg);
			}
		}
	}
	private void sendheads(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.heads")) {
				player.sendMessage(msg);
			}
		}
	}
	private void sendallstaff(String msg) {
		msg = this.format(msg);
		for (Player player : this.getServer().getOnlinePlayers()) {
			if (player.hasPermission("staffchat.staff")) {
				player.sendMessage(msg);
			}
		}
	}
	private String format(final String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}
}
