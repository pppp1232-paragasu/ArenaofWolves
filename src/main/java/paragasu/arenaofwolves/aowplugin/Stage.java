package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import paragasu.arenaofwolves.aowplugin.fightingwolf.FightingWolf;

public class Stage {

	private Player player1;
	private Player player2;

	protected String stageName;
	protected Location player1Loc;
	protected Location player2Loc;
	protected Location wolf1Loc;
	protected Location wolf2Loc;

	protected Stage(){}

    public void playerReset() {
    	player1 = null;
    	player2 = null;
    }

	public void matching(Player player) {
		if(FightingWolf.getFightingWolf(player) == null) {
			Bukkit.broadcastMessage("狼用意してこい");
			return;
		}
		if(player1 == null) {
			player1 = player;
			Bukkit.broadcastMessage(player1.getName());
			return;
		}
		else if(player2 == null) {
			player2 = player;
			Bukkit.broadcastMessage(player1.getName() + " vs " + player2.getName());
			startMatch();
		}
		else {
			return;
		}
	}

	private void startMatch() {
		Bukkit.broadcastMessage("[デバッグステージ] " + player1.getName() + " VS " + player2.getName());
		new StartMatchTask(player1, player2, player1Loc, player2Loc, wolf1Loc, wolf2Loc)
				.runTaskTimerAsynchronously(JavaPlugin.getPlugin(AoWPlugin.class), 40, 20);
	}
}
