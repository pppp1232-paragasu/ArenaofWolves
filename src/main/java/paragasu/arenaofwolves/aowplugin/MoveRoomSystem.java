package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveRoomSystem implements Listener {

//	private static final Location lobbyLoc = new Location(AoWPlugin.WORLD, 108.5D, 4D, -1327.5D, 90F, 0F);
//	private static final Location goOptionRoomLoc = new Location(AoWPlugin.WORLD, 110D, 3D, -1340D);
	private static final Location optionRoomLoc = new Location(AoWPlugin.WORLD, 102.5D, 4D, -1349.5D, 180F, 0F);
	private static final Location lobbyFromOptionLoc = new Location(AoWPlugin.WORLD, 108.5D, 4, -1339.5, 90F, 0F);

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
		Block underBlock = block.getRelative(BlockFace.DOWN);
		if(block.getType() == Material.DIAMOND_BLOCK) {
			if(underBlock.getType() == Material.REDSTONE_BLOCK) {
				player.teleport(optionRoomLoc);
			}
			else if(underBlock.getType() == Material.COAL_BLOCK) {
				player.teleport(lobbyFromOptionLoc);
			}
		}
	}
}
