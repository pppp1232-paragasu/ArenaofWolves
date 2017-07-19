package paragasu.arenaofwolves.aowplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AoWAPI {

	public static void sendGameMessage(Player player, String message) {
		player.sendMessage(ChatColor.BOLD +"[" + ChatColor.YELLOW + ChatColor.BOLD +"AoW" + ChatColor.WHITE + ChatColor.BOLD + "] " + ChatColor.RESET + message);
	}
}
