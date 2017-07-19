package paragasu.arenaofwolves.aowplugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class SummonCommandExcutor implements CommandExecutor {

//	private int strength;
//	private int constitution;
//	private int intelligence;
//	private int dexterity;

	ArrayList<Integer> allocatedPoints;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("sw") || command.getName().equalsIgnoreCase("summonwolf")) {
			if(sender instanceof Player) {
				if(args.length == 5) {
					Player player = (Player) sender;
					this.allocatedPoints = new ArrayList<Integer>();
					for(int i = 0; i < 4; i++) {
						String s = args[i];
						if(s.matches("\\d+")) {
							this.allocatedPoints.add(Integer.parseInt(s)); //NullPointerException
						}
						else{
							AoWAPI.sendGameMessage(player, ChatColor.RED + "エラー：正しく数値を入力してください");
							return true;
						}
					}
					for(int i : this.allocatedPoints) {
						if(i > 30) {
							AoWAPI.sendGameMessage(player, ChatColor.RED + "エラー：つのステータスには30までしか割り振れません");
							return true;
						}
					}
					int sum = allocatedPoints.get(0) + allocatedPoints.get(1) + allocatedPoints.get(2) + allocatedPoints.get(3);
					if(sum > 60) {
						AoWAPI.sendGameMessage(player, ChatColor.RED + "エラー：割り振られたポイントの合計が60を超えています");
						return true;
					}
					else if(sum < 60) {
						AoWAPI.sendGameMessage(player, ChatColor.YELLOW + "確認：割り振られたポイントの合計が60未満です。");
					}
					Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
					FightingWolf fightingWolf = null;
					FightingWolf.fightingWolfSet.add(fightingWolf);
					String wolfTypeName = args[4].toLowerCase();
					WolfType wolfType = null;
					switch(wolfTypeName) {
						case "wyatt":
							wolfType = WolfType.WYATT;
							fightingWolf = new Wyatt(player, wolf, allocatedPoints);
							break;
					}
					if(wolfType == null) {
						AoWAPI.sendGameMessage(player, ChatColor.RED + "エラー：" + args[4] + "は見つかりませんでした");
						return true;
					}
					FightingWolf.fightingWolfSet.add(fightingWolf);
					AoWAPI.sendGameMessage(player, ChatColor.AQUA + "オオカミの召喚に成功しました");
					String str = String.format("%2s", String.valueOf(fightingWolf.strength));
					String con = String.format("%2s", String.valueOf(fightingWolf.constitution));
					String inte = String.format("%2s", String.valueOf(fightingWolf.intelligence));
					String dex = String.format("%2s", String.valueOf(fightingWolf.dexterity));

					StringBuilder sb = new StringBuilder();
					sb.append(ChatColor.GREEN).append(ChatColor.BOLD).append("=================").append("\n")
							.append(" ").append(fightingWolf.getWolfType().getColoredName()).append("\n")
							.append("  ").append(ChatColor.RED).append(ChatColor.BOLD).append("STR   ").append(str).append("\n")
							.append("  ").append(ChatColor.GOLD).append(ChatColor.BOLD).append("CON   ").append(con).append("\n")
							.append("  ").append(ChatColor.LIGHT_PURPLE).append(ChatColor.BOLD).append("INT   ").append(inte).append("\n")
							.append("  ").append(ChatColor.DARK_AQUA).append(ChatColor.BOLD).append("DEX   ").append(dex).append("\n")
							.append(ChatColor.GREEN).append(ChatColor.BOLD).append("=================");
					sender.sendMessage(sb.toString());
					return true;
				}
			}
		}
		return false;
	}



}
