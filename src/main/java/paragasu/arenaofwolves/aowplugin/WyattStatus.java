package paragasu.arenaofwolves.aowplugin;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class WyattStatus extends FightingWolf {

	public WyattStatus(Player owner, Wolf wolf, ArrayList<Integer> allocatedPoints) {
		super(owner, wolf, WolfType.Wyatt, allocatedPoints);
	}

	@Override
	public void onBattleStart() {
		this.shield = this.magicPower * 5;
	}

}
