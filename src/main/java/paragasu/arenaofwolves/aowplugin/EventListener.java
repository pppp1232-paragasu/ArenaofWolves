package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

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

	@EventHandler
	public void onSignClicked(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Material material = e.getClickedBlock().getType();
			if(material == Material.SIGN || material == Material.SIGN_POST || material == Material.WALL_SIGN) {
				Player player = e.getPlayer();
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(1).indexOf("ステージ1") != -1) {
			        if (e.getHand() == EquipmentSlot.OFF_HAND) {
			            return;
			        }
					DebugStage stage = DebugStage.getInstance();
					stage.matching(player);
				}
			}
		}
	}
}
