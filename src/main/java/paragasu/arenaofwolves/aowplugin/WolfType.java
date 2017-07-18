package paragasu.arenaofwolves.aowplugin;

enum WolfType {
	NORMAL,
	TEST,
	WYATT;

	public  String getTypeName() {
		String name = this.name();
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		return name;
	}
}
