package paragasu.arenaofwolves.aowplugin;

import org.bukkit.ChatColor;

enum WolfType {
	NORMAL {                     //仮
		@Override
		public String getColoredName() {
			return this.name();
		}
	},
	TEST {                       //仮
		@Override
		public String getColoredName() {
			return this.name();
		}
	},
	WYATT {
		@Override
		public String getColoredName() {
			String coloredName = ChatColor.YELLOW + "" + ChatColor.BOLD + "Wyatt" + ChatColor.RESET;
			return coloredName;
		}
	};

	public  String getTypeName() {
		String name = this.name();
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		return name;
	}

	public abstract String getColoredName();
}
