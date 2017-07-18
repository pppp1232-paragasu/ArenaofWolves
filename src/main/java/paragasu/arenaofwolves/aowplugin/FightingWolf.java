package paragasu.arenaofwolves.aowplugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class FightingWolf {
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


	public FightingWolf(Player owner, Wolf wolf, WolfType wolfType, ArrayList<Integer> allocatedPoints) {
		this.owner = owner;
		this.wolf = wolf;
		this.wolfType = wolfType;
		this.strength = allocatedPoints.get(0);
		this.constitution = allocatedPoints.get(1);
		this.intelligence = allocatedPoints.get(2);
		this.dexterity = allocatedPoints.get(3);
		this.setWolfBaseStats(this.wolfType);
		this.setWolfStats(strength, constitution, intelligence, dexterity);
		fightingWolfSet.add(this);
	}


	// HP ATK DEF CRT ACC EVA SPD
	private void setWolfBaseStats(WolfType wolfType) {
		switch(wolfType){
			case NORMAL:
				this.setWolfStatsValue(300, 60, 70, 0, 0, 100, 0, 0);
				break;

			case TEST:
				this.setWolfStatsValue(300, 100, 50, 30, 5, 100, 30, 0);
				break;

			case Wyatt:
				this.setWolfStatsValue(200, 10, 20, 10, 5, 100, 0, 0);
		}
	}

	private void setWolfStats(int str, int con, int inte, int dex) {
		int atk = this.attack + str;
		int critD = this.critDamage + str / 4;
		int hp = this.hitPoints + con * 10;
		int def = (int) (this.defence + con * 0.6);
		int mp = this.magicPower + inte;
		int acc = this.accuracy + dex;
		int eva = this.evasion + dex / 2;
		int critC = this.critChance + dex / 2;
		this.setWolfStatsValue(hp, atk, def, critC, critD, acc, eva, mp);
	}

	private void setWolfStatsValue(int hp, int atk, int def, int critC, int critD, int acc, int eva, int mp) {
		this.hitPoints = hp;
		this.attack = atk;
		this.defence = def;
		this.critChance = critC;
		this.critDamage = critD;
		this.accuracy = acc;
		this.evasion = eva;
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
	public WolfType getWolfType() {
		return this.wolfType;
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

	public void onBattleStart() {

	}
}
