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
		this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents((Listener) this, (Plugin) this);
		this.getLogger().info("Server Staff Chat has successfully started!");

	}

	public boolean onCommand(final CommandSender s, final Command cmd, final String label, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("modchat") && s instanceof Player) {
			final Player m = (Player) s;
			if (m.hasPermission("staffchat.mod")) {
				if (args.length == 0) {
					m.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/mc <message>");
				} else {
					String ps = m.getName();
					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String mes = getConfig().getString("formats.moderator");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.sendmods(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("adminchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.admin")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/ac <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String ps = a.getName();
					String mes = getConfig().getString("formats.admin");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.sendadmins(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("helperchat") && s instanceof Player) {
			final Player h = (Player) s;
			if (h.hasPermission("staffchat.helper")) {
				if (args.length == 0) {
					h.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/hc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String ps = h.getName();
					String mes = getConfig().getString("formats.helper");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.sendhelpers(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("modpluschat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.modplus")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/mpc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String ps = a.getName();
					String mes = getConfig().getString("formats.modplus");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.sendmodplus(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("devchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.dev")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/dc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String ps = a.getName();
					String mes = getConfig().getString("formats.developer");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.senddevs(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("headchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.heads")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/hdc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String ps = a.getName();
					String mes = getConfig().getString("formats.heads");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.sendheads(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("builderchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.builder")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/bc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String ps = a.getName();
					String mes = getConfig().getString("formats.builder");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.sendbuilders(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("staffchat") && s instanceof Player) {
			final Player a = (Player) s;
			if (a.hasPermission("staffchat.staff")) {
				if (args.length == 0) {
					a.sendMessage(ChatColor.RED + "Invalid Arguments! Use " + ChatColor.WHITE + "/sc <message>");
				} else {

					String msg = "";

					for (int i = 0; i < args.length; ++i) {
						msg = String.valueOf(msg) + args[i] + ' ';
					}
					String ps = a.getName();
					String mes = getConfig().getString("formats.staff");
					mes = mes.replace("%PLAYER%", ps);
					mes = mes.replace("%MSG%", msg);
					ChatColor.translateAlternateColorCodes('&', mes);

					this.sendallstaff(mes);
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("screload") && s instanceof Player) {
			Player a = (Player) s;
			if (a.hasPermission("staffchat.reload")) {
				this.reloadConfig();
				a.sendMessage(ChatColor.DARK_AQUA + "[Staff Chat] " + ChatColor.AQUA + "The configuration was reloaded!");
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
