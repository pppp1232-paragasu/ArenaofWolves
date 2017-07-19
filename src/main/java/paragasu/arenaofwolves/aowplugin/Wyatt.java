package paragasu.arenaofwolves.aowplugin;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class Wyatt extends FightingWolf {

	protected int hitPoints = 200;
	protected int attack = 10;
	protected int defence = 20;
	protected int critChance = 10;
	protected int critDamage = 5;
	protected int accuracy = 100;
	protected int evasion = 0;
	protected int magicPower = 0;

	public Wyatt(Player owner, Wolf wolf, ArrayList<Integer> allocatedPoints) {
		super(owner, wolf, allocatedPoints);
	}

	@Override
	public void onBattleStart() {
		this.shield = this.magicPower * 5;
	}

	@Override
	public WolfType getWolfType() {
		return WolfType.WYATT;
	}

}
