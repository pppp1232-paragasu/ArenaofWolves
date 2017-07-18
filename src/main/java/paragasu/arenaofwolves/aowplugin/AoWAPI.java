package paragasu.arenaofwolves.aowplugin;

public class AoWAPI {
	public static WolfType getWolfTypeFromString(String wolfTypeName) {
		if(wolfTypeName.equalsIgnoreCase("Wyatt")) {
			return WolfType.Wyatt;
		}
		else{
			return null;
		}
	}
}
