package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class DebugStage extends Stage {



	private static DebugStage singleton = new DebugStage();

	private DebugStage() {
		this.stageName = ChatColor.DARK_PURPLE + "デバッグステージ";
		this.player1Loc = new Location(Bukkit.getWorld("aow"), 40, 14, -8);
		this.player1Loc = new Location(Bukkit.getWorld("aow"), 40, 14, -8);
		this.wolf1Loc = new Location(Bukkit.getWorld("aow"), 40, 11, 4);
		this.wolf2Loc = new Location(Bukkit.getWorld("aow"), 40, 11, -4);
	}

    public static DebugStage getInstance(){
        return singleton;
    }

}
