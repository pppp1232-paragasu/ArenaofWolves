package paragasu.arenaofwolves.aowplugin;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class AoWPlugin extends JavaPlugin implements Listener {

	public static final World WORLD = Bukkit.getWorld("suika");
	public static int count;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new WolfBattleSystem(), this);
		getServer().getPluginManager().registerEvents(new MoveRoomSystem(), this);
		getServer().getPluginManager().registerEvents(new WolfMatchSystem(), this);
		getServer().getPluginManager().registerEvents(new SetWolfTypeOfPlayerSystem(), this);
//		getCommand("test").setExecutor(new AoWCommandExecutor());
		getCommand("sw").setExecutor(new SummonCommandExcutor());
	}

	@EventHandler
	public void test(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_AIR && player.getItemInHand().getType() == Material.STICK) {
			List<Entity> nearbyEntities = player.getNearbyEntities(2D, 2D, 2D);
			if(nearbyEntities.get(0).getType() == EntityType.WOLF && nearbyEntities.get(1).getType() == EntityType.WOLF) {
				Wolf wolf1 = ((Wolf) nearbyEntities.get(0));
				wolf1.setTarget(((LivingEntity)nearbyEntities.get(1)));
			}
		}

		if(event.getAction() == Action.RIGHT_CLICK_AIR && player.getItemInHand().getType() == Material.BLAZE_ROD) {
			StringBuilder sb = new StringBuilder();
			sb.append("§e§l[AoW] ").append("§a§l" + "ステージ1")
			.append(" §c§l" + player.getName()).append(" §f§lVS ").append("§9§l" + player.getName());
			Bukkit.broadcastMessage(sb.toString());
		}

		if(event.getAction() == Action.RIGHT_CLICK_AIR && player.getItemInHand().getType() == Material.BLAZE_POWDER) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("test", "test1");
			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			objective.setDisplayName("aaaaa");
			player.setScoreboard(board);
		}

		if(event.getAction() == Action.RIGHT_CLICK_AIR && player.getItemInHand().getType() == Material.STRING) {
			player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
	}

	@EventHandler
	public void onClickWolf(PlayerInteractAtEntityEvent event) {
		if(event.getPlayer().getItemInHand().getType() == Material.NAME_TAG) {
			if(event.getRightClicked().getType() == EntityType.WOLF) {
				Bukkit.broadcastMessage("wolf clicked");
				event.getRightClicked().setCustomName("てすと§c§l♥300");
				event.getRightClicked().setCustomName(ChatColor.GREEN + "❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘❘");
			}
		}
	}


//	@EventHandler
//	public void onWolfSpawn(CreatureSpawnEvent event) {
//		if(event.getEntityType() == EntityType.WOLF && event.getSpawnReason() != SpawnReason.NATURAL) {
//			Bukkit.broadcastMessage("wolf spawning");
//			Wolf wolf = (Wolf) event.getEntity();
//			WolfStatus.wolfStatusSet.add(new WolfStatus(wolf, WolfType.NORMAL));
//		}
//	}
}
