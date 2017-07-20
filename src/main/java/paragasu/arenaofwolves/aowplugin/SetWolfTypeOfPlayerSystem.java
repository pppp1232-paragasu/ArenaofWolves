package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import paragasu.arenaofwolves.aowplugin.fightingwolf.WolfType;

public class SetWolfTypeOfPlayerSystem implements Listener {

	@EventHandler
	public void onSignClicked(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Material material = event.getClickedBlock().getType();
			if(material == Material.SIGN || material == Material.SIGN_POST || material == Material.WALL_SIGN) {
				Player player = event.getPlayer();
				Sign sign = (Sign) event.getClickedBlock().getState();
				WolfType wolfType = null;
				if(sign.getLine(1).contains("Wyatt")) {
					wolfType = WolfType.WYATT;
				}
				if(wolfType != null) {
					player.setMetadata("wolftype", new FixedMetadataValue(new AoWPlugin(), wolfType));
				}
			}
		}
	}

}
