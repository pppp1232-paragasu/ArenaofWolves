package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import paragasu.arenaofwolves.aowplugin.fightingwolf.FightingWolf;

public class WolfBattleSystem implements Listener{

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

