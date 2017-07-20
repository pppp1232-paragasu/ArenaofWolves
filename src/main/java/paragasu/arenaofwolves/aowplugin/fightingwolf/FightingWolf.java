package paragasu.arenaofwolves.aowplugin.fightingwolf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public abstract class FightingWolf {
	public static HashSet<FightingWolf> fightingWolfSet = new HashSet<FightingWolf>();

	protected Wolf wolf;
	protected WolfType wolfType;
	protected Player owner;
	protected Wolf opponentWolf;

	protected int strength;
	protected int constitution;
	protected int intelligence;
	protected int dexterity;

	protected int hitPoints;
	protected int attack;
	protected int defence;
	protected int critChance;
	protected int critDamage;
	protected int accuracy;
	protected int evasion;
	protected int magicPower;
	protected int shield = 0;


	protected FightingWolf(Player owner, Wolf wolf, ArrayList<Integer> allocatedPoints) {
		this.owner = owner;
		this.wolf = wolf;
		this.strength = allocatedPoints.get(0);
		this.constitution = allocatedPoints.get(1);
		this.intelligence = allocatedPoints.get(2);
		this.dexterity = allocatedPoints.get(3);

		this.hitPoints += constitution * 10;
		this.attack += intelligence * 1.4;
		this.defence += constitution * 0.6;
		this.critChance += dexterity / 2;
		this.critDamage += strength / 4;
		this.accuracy += dexterity;
		this.evasion += dexterity / 2;
		this.magicPower += intelligence;
		fightingWolfSet.add(this);
	}

	public static FightingWolf create(Player owner, Wolf wolf, ArrayList<Integer> allocatedPoints, WolfType wolfType) {
		switch(wolfType) {
			case WYATT:
				return new Wyatt(owner, wolf, allocatedPoints);

			default:
				return null;

		}
	}

	public static FightingWolf getFightingWolf(Wolf wolf) {
		for(Iterator<FightingWolf> itr = FightingWolf.fightingWolfSet.iterator(); itr.hasNext();) {
			FightingWolf theFightingWolf = itr.next();
			if(theFightingWolf.getWolf() == wolf) {
				return theFightingWolf;
			}
		}
		return null;
	}

	public static FightingWolf getFightingWolf(Player player) {
		for(Iterator<FightingWolf> itr = FightingWolf.fightingWolfSet.iterator(); itr.hasNext();) {
			FightingWolf theFightingWolf = itr.next();
			if(theFightingWolf.getOwner() == player) {
				return theFightingWolf;
			}
		}
		return null;
	}

	public  Wolf getWolf(){
		return this.wolf;
	}

	public Player getOwner() {
		return this.owner;
	}

	public Wolf getOpponentWolf() {
		return this.opponentWolf;
	}


	public int getHitPoints() {
		return this.hitPoints;
	}
	public int getShield() {
		return this.shield;
	}
	public int getAttack() {
		return this.attack;
	}
	public int getDefence() {
		return this.defence;
	}
	public int getCritChance() {
		return this.critChance;
	}
	public int getCritDamage() {
		return this.critDamage;
	}
	public int getAccuracy() {
		return this.accuracy;
	}
	public int getEvasion() {
		return this.evasion;
	}
	public int getmagicPower() {
		return this.magicPower;
	}

	public void setHitPoints(int value) {
		this.hitPoints = value;
	}

	public void setOpponentWolf(Wolf wolf) {
		this.opponentWolf = wolf;
	}

	public abstract WolfType getWolfType();

	public abstract void onBattleStart();

}
