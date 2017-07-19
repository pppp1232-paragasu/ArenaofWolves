package paragasu.arenaofwolves.aowplugin;

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


	public FightingWolf(Player owner, Wolf wolf, ArrayList<Integer> allocatedPoints) {
		this.owner = owner;
		this.wolf = wolf;
		this.strength = allocatedPoints.get(0);
		this.constitution = allocatedPoints.get(1);
		this.intelligence = allocatedPoints.get(2);
		this.dexterity = allocatedPoints.get(3);
		this.setWolfStats(strength, constitution, intelligence, dexterity);
		fightingWolfSet.add(this);
	}

	private void setWolfStats(int str, int con, int inte, int dex) {
		this.hitPoints += con * 10;
		this.attack += str * 1.4;
		this.defence += con * 0.6;
		this.critChance += dex / 2;
		this.critDamage += str / 4;
		this.accuracy += dex;
		this.evasion += dex / 2;
		this.magicPower += inte;
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
