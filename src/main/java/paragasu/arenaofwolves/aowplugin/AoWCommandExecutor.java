//package paragasu.arenaofwolves.aowplugin;
//
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.Player;
//import org.bukkit.entity.Wolf;
//
//public class AoWCommandExecutor implements CommandExecutor {
//
//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
//		if(cmd.getName().equalsIgnoreCase("test")) {
//			if(args.length == 1) {
//				if(sender instanceof Player) {
//					Player player = (Player) sender;
//					if(player.getNearbyEntities(0D, 0D, 0D).size() == 0) {
//						player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
//						Wolf wolf = (Wolf) player.getNearbyEntities(0D, 0D, 0D).get(0);
//						wolf.setOwner(player);
//						String wolfType = args[0].toLowerCase();
//						switch(wolfType) {
//							case "normal":
//								WolfStatus.wolfStatusSet.add(new WolfStatus(player, wolf, WolfType.NORMAL));
//								break;
//
//							case "test":
//								WolfStatus.wolfStatusSet.add(new WolfStatus(player, wolf, WolfType.TEST));
//								break;
//
//							default:
//								return false;
//						}
//						return true;
//					}
//				}
//			}
//		}
//		if(cmd.getName().equalsIgnoreCase("wolfname")) {
//			if(args.length == 1) {
//				if(sender instanceof Player) {
//
//				}
//			}
//		}
//		return false;
//	}
//}
