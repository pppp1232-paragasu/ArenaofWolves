package paragasu.arenaofwolves.aowplugin.fightingwolf;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class Wyatt extends FightingWolf {

//	protected int hitPoints = 200;
//	protected int attack = 10;
//	protected int defence = 20;
//	protected int critChance = 10;
//	protected int critDamage = 5;
//	protected int accuracy = 100;
//	protected int evasion = 0;
//	protected int magicPower = 0;

	Wyatt(Player owner, Wolf wolf, ArrayList<Integer> allocatedPoints) {
		super(owner, wolf, allocatedPoints);
		this.hitPoints += 200;
		this.attack += 10;
		this.defence += 20;
		this.critChance += 10;
		this.critDamage += 5;
		this.accuracy += 100;
		this.evasion += 0;
		this.magicPower += 0;
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
