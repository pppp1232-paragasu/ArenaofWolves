package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import paragasu.arenaofwolves.aowplugin.fightingwolf.FightingWolf;

public class WolfMatchSystem implements Listener {

	private static Player stage1RedPlayer;
	private static Player stage1BluePlayer;
	private static boolean stage1CanEnter = true;

//	@EventHandler
//	public void onPlayerMove(PlayerMoveEvent event) {
//		Player player = event.getPlayer();
//		Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
//		Block underBlock = block.getRelative(BlockFace.DOWN);
//		if(block.getType() == Material.SEA_LANTERN) {
//			if(underBlock.getType() == Material.IRON_BLOCK) {
//				player.teleport(stage1RedLoc);
//				Wolf wolf = WolfStatus.getWolfStatus(player).getWolf();
//				if(wolf != null) {
//					wolf.teleport(stage1RedWolfLoc);
//					wolf.setSitting(true);
//				}
//			}
//			else if(underBlock.getType() == Material.GOLD_BLOCK) {
//				player.teleport(stage1BlueLoc);
//				Wolf wolf = WolfStatus.getWolfStatus(player).getWolf();
//				if(wolf != null) {
//					wolf.teleport(stage1BlueWolfLoc);
//					wolf.setSitting(true);
//				}
//			}
//		}
//	}

//	@EventHandler
//	public void setSignLine(PlayerInteractEvent e) {
//		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
//			Material material = e.getClickedBlock().getType();
//			if(material == Material.SIGN || material == Material.SIGN_POST || material == Material.WALL_SIGN) {
//				Sign sign = (Sign) e.getClickedBlock().getState();
//				if(e.getPlayer().getItemInHand().getType() == Material.BREAD) {
//					sign.setLine(1, "§a＝＝ §f§lステージ2§a ＝＝");
//					sign.setLine(2, "§l試合にエントリー");
//					sign.update();
//				}
//			}
//		}
//	}

	@EventHandler
	public void onSignClicked(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Material material = e.getClickedBlock().getType();
			if(material == Material.SIGN || material == Material.SIGN_POST || material == Material.WALL_SIGN) {
				Player player = e.getPlayer();
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(1).indexOf("ステージ1") != -1) {
					if(FightingWolf.getFightingWolf(player) != null) {
						if(FightingWolf.getFightingWolf(player).getWolf() != null) {
							if(stage1CanEnter) {
								if(stage1RedPlayer == null) {
									stage1RedPlayer = player;
								}
								else if(stage1BluePlayer == null) {
									stage1BluePlayer = player;
									stage1CanEnter = false;
									this.startMatch(stage1RedPlayer, stage1BluePlayer, WolfStagesEnum.STAGE1);
								}
							}
							else{
								player.sendMessage("§e§l[AoW]§cステージ1は試合中です");
								player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
							}
						}
						else{
							player.sendMessage("§e§l[AoW]§cオオカミが召喚されていません");
						}
					}
					else{
						Bukkit.broadcastMessage("WolfMatchSytemクラス90行");
						player.sendMessage("§e§l[AoW]§cオオカミが召喚されていません");
					}
				}
			}
		}
	}

	private void startMatch(Player red, Player blue, WolfStagesEnum wolfStagesEnum) {
		Player redPlayer = red;
		Player bluePlayer = blue;
		FightingWolf redFightingWolf = FightingWolf.getFightingWolf(redPlayer);
		FightingWolf blueFightingWolf = FightingWolf.getFightingWolf(bluePlayer);
		Wolf redWolf = redFightingWolf.getWolf();
		Wolf blueWolf = blueFightingWolf.getWolf();
		redFightingWolf.setOpponentWolf(blueWolf);
		blueFightingWolf.setOpponentWolf(redWolf);
		WolfStage stage = new WolfStage(wolfStagesEnum);
		String stageName = stage.getStageName();
		StringBuilder sb = new StringBuilder();
		sb.append("§e§l[AoW] ").append("§a§l" + stageName)
				.append(" §c§l" + redPlayer.getName()).append(" §f§lVS ").append("§9§l" + bluePlayer.getName());
		Bukkit.broadcastMessage(sb.toString());
		new BukkitRunnable() {
			@Override
			public void run() {
				redPlayer.teleport(stage.redPlayerLoc);
				AoWPlugin.WORLD.playSound(redPlayer.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1.2F);
				bluePlayer.teleport(stage.bluePlayerLoc);
				AoWPlugin.WORLD.playSound(bluePlayer.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1.2F);
			}
		}.runTaskLater(new AoWPlugin(), 40);
		new BukkitRunnable() {
			@Override
			public void run() {
				redWolf.teleport(stage.redWolfLoc);
				redWolf.setSitting(true);
				blueWolf.teleport(stage.blueWolfLoc);
				blueWolf.setSitting(true);
			}
		}.runTaskLater(new AoWPlugin(), 10);
		int count = 3;
		new BukkitRunnable() {
			@Override
			public void run() {
				if(count != 0) {
					redPlayer.playSound(redPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
					bluePlayer.playSound(redPlayer.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
				}else{
					redWolf.setTarget(blueWolf);
					blueWolf.setTarget(redWolf);
					this.cancel();
				}
			}
		}.runTaskTimer(new AoWPlugin(), 20, 20);

	}

	@EventHandler
	public void onWolfDeath(EntityDeathEvent event) {
		if(event.getEntity().getType() == EntityType.WOLF) {
			Wolf wolf = (Wolf) event.getEntity();
			FightingWolf fightingWolf = FightingWolf.getFightingWolf(wolf);
			StringBuilder sb = new StringBuilder();
			Location lobbyLoc =  new Location(AoWPlugin.WORLD, 108.5D, 4D, -1327.5D, 90F, 0F);
			if(fightingWolf.getOwner() == stage1RedPlayer) {
				sb.append("§e§l[AoW] ").append("§a§l" + "ステージ1")
						.append(" §c§l" + stage1RedPlayer.getName()).append("§r§fが勝利しました");
				stage1RedPlayer.teleport(lobbyLoc);
				stage1BluePlayer.teleport(lobbyLoc);
				stage1RedPlayer = null;
				stage1BluePlayer = null;
				stage1CanEnter = true;
			}
			else if(fightingWolf.getOwner() == stage1BluePlayer) {
				sb.append("§e§l[AoW] ").append("§a§l" + "ステージ1")
						.append(" §c§l" + stage1RedPlayer.getName()).append("§r§fが勝利しました");
				stage1RedPlayer.teleport(lobbyLoc);
				stage1BluePlayer.teleport(lobbyLoc);
				stage1RedPlayer = null;
				stage1BluePlayer = null;
				stage1CanEnter = true;
			}
			Bukkit.broadcastMessage(sb.toString());
		}
	}
}
