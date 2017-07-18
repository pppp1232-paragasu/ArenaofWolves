package paragasu.arenaofwolves.aowplugin;

import org.bukkit.Location;

public class WolfStage {

	public Location redPlayerLoc;
	public Location redWolfLoc;
	public Location bluePlayerLoc;
	public Location blueWolfLoc;
	private String stageName;

	public WolfStage(WolfStagesEnum wolfStagesEnum) {
		switch(wolfStagesEnum){
			case STAGE1:
				this.redPlayerLoc = new Location(AoWPlugin.WORLD, 144.5D, 7D, -1304.5D, 90F, 0F);
				this.redWolfLoc = new Location(AoWPlugin.WORLD, 138.5D, 4D, -1304.5D, 90F, 0F);
				this.bluePlayerLoc = new Location(AoWPlugin.WORLD, 122.5D, 7D, -1304.5D, -90F, 0F);
				this.blueWolfLoc = new Location(AoWPlugin.WORLD, 128.5D, 4D, -1304.5D, -90F, 0F);
				this.stageName = "ステージ1";
				break;

			default:
				break;
		}
	}

	public String getStageName() {
		return this.stageName;
	}
}
