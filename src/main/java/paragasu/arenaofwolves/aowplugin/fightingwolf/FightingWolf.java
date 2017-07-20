package paragasu.arenaofwolves.aowplugin.fightingwolf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.util.Vector;

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

	public boolean damage(FightingWolf attacker) {
		int aAccuracy = attacker.getAccuracy();
		int vEvasion = this.getEvasion();
		int hitChance = aAccuracy - vEvasion;
		int aAttack = attacker.getAttack();
		int vDefence = attacker.getDefence();
		int aCritChance = attacker.getCritChance();
		int aCritDamage = attacker.getCritDamage();
		int vHitPoints = this.getHitPoints();
		int vShield = this.getShield();
		int damagePoints = 0;
		Wolf attackerWolf = attacker.getWolf();
		Wolf victimWolf = this.getWolf();
		Random rnd = new Random();
		if(rnd.nextInt(100) < hitChance) {
			damagePoints = aAttack - vDefence;
			if(damagePoints < 0) {
				damagePoints = 0;
			}
			if(rnd.nextInt(100) < aCritChance) {
				damagePoints *= 1.5;
				damagePoints += aCritDamage;
				attackerWolf.getWorld().playSound(attackerWolf.getLocation(), Sound.ENTITY_BLAZE_HURT, 100F, 1.5F);
			}
			if(vShield >= damagePoints) {
				vShield -= damagePoints;
			}else {
				vHitPoints -= (damagePoints - vShield);
			}
		}else{
			victimWolf.getWorld().playSound(attackerWolf.getLocation(), Sound.ENTITY_ARROW_SHOOT, 100F, 1F);
			victimWolf.getWorld().playEffect(attackerWolf.getLocation(), Effect.SMOKE, 0, 3);
			Vector attackerVector = attackerWolf.getLocation().toVector();
			Vector knockBack = attackerVector.clone().normalize().multiply(0.004);
			knockBack.setY(0.5D);

			victimWolf.setVelocity(knockBack);

			return false;
		}
		if(vHitPoints <= 0) {
			vHitPoints = 0;
			victimWolf.setHealth(0);
		}else{
			victimWolf.setHealth(victimWolf.getMaxHealth());
		}
		this.setHitPoints(vHitPoints);
		Bukkit.broadcastMessage("HP " + String.valueOf(this.getHitPoints()));
		return true;
	}

	public abstract WolfType getWolfType();

	public abstract void onBattleStart();

}
