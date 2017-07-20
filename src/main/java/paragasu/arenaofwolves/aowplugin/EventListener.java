package paragasu.arenaofwolves.aowplugin;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import paragasu.arenaofwolves.aowplugin.fightingwolf.FightingWolf;

public class EventListener implements Listener {

	@EventHandler
	public void onWolfDamage(EntityDamageByEntityEvent event) {
		if(event.getEntity().getType() == EntityType.WOLF && event.getDamager().getType() == EntityType.WOLF) {
			Wolf attacker = (Wolf) event.getDamager();
			Wolf victim = (Wolf) event.getEntity();
			FightingWolf attackerFightingWolf = FightingWolf.getFightingWolf(attacker);
			FightingWolf victimFightingWolf = FightingWolf.getFightingWolf(victim);
			if(! victimFightingWolf.damage(attackerFightingWolf)) {
				event.setCancelled(true);
			}

		}
	}

	@EventHandler
	public void onWolfDie(EntityDeathEvent event) {
		if(event.getEntity().getType() == EntityType.WOLF) {
			Wolf wolf = (Wolf) event.getEntity();
			FightingWolf fightingWolf = FightingWolf.getFightingWolf(wolf);
			if(fightingWolf != null) {
				FightingWolf.fightingWolfSet.remove(fightingWolf);
			}
		}
	}
}
