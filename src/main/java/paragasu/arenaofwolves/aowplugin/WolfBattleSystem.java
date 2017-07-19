package paragasu.arenaofwolves.aowplugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.util.Vector;

public class WolfBattleSystem implements Listener{

	@EventHandler
	public void onWolfDamage(EntityDamageByEntityEvent event) {
		if(event.getEntity().getType() == EntityType.WOLF && event.getDamager().getType() == EntityType.WOLF) {
			Wolf attacker = (Wolf) event.getDamager();
			Wolf victim = (Wolf) event.getEntity();
			FightingWolf attackerFightingWolf = FightingWolf.getFightingWolf(attacker);
			FightingWolf victimFightingWolf = FightingWolf.getFightingWolf(victim);
			if(! this.damage(attackerFightingWolf, victimFightingWolf)) {
				event.setCancelled(true);
			}

		}
	}

	//a = Attacker v = Victim
	//dodgeしたらfalseを返す
	private boolean damage(FightingWolf aFightingWolf, FightingWolf vFightingWolf) {
		int aAccuracy = aFightingWolf.getAccuracy();
		int vEvasion = vFightingWolf.getEvasion();
		int hitChance = aAccuracy - vEvasion;
		int aAttack = aFightingWolf.getAttack();
		int vDefence = aFightingWolf.getDefence();
		int aCritChance = aFightingWolf.getCritChance();
		int aCritDamage = aFightingWolf.getCritDamage();
		int vHitPoints = vFightingWolf.getHitPoints();
		int vShield = vFightingWolf.getShield();
		int damagePoints = 0;
		Wolf attacker = aFightingWolf.getWolf();
		Wolf victim = vFightingWolf.getWolf();
		Random rnd = new Random();
		if(rnd.nextInt(100) < hitChance) {
			damagePoints = aAttack - vDefence;
			if(damagePoints < 0) {
				damagePoints = 0;
			}
			if(rnd.nextInt(100) < aCritChance) {
				damagePoints *= 1.5;
				damagePoints += aCritDamage;
				attacker.getWorld().playSound(attacker.getLocation(), Sound.ENTITY_BLAZE_HURT, 100F, 1.5F);
			}
			if(vShield >= damagePoints) {
				vShield -= damagePoints;
			}else {
				vHitPoints -= (damagePoints - vShield);
			}
		}else{
			victim.getWorld().playSound(attacker.getLocation(), Sound.ENTITY_ARROW_SHOOT, 100F, 1F);
			victim.getWorld().playEffect(attacker.getLocation(), Effect.SMOKE, 0, 3);
			Vector attackerVector = attacker.getLocation().toVector();
			Vector knockBack = attackerVector.clone().multiply(0.004);
			knockBack.setY(0.5D);

			victim.setVelocity(knockBack);

			return false;
		}
		if(vHitPoints <= 0) {
			vHitPoints = 0;
			victim.setHealth(0);
		}else{
			victim.setHealth(victim.getMaxHealth());
		}
		vFightingWolf.setHitPoints(vHitPoints);
		Bukkit.broadcastMessage("HP " + String.valueOf(vFightingWolf.getHitPoints()));
		return true;
	}

	@EventHandler
	public void onWolfDie(EntityDeathEvent event) {
		if(event.getEntity().getType() == EntityType.WOLF) {
			Wolf wolf = (Wolf) event.getEntity();
			FightingWolf fightingWolf = FightingWolf.getFightingWolf(wolf);
			if(fightingWolf != null) {
				FightingWolf.fightingWolfSet.remove(fightingWolf);
			}
			else{
				Bukkit.broadcastMessage("WolfBattleSystemクラスonWolfDieメソッド nullremoveエラー");
			}
		}
	}
}

