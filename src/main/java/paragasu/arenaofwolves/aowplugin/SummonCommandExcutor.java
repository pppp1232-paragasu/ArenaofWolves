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
					String wolfTypeName = args[4];
					WolfType wolfType = WolfType.WYATT;
					if(wolfType == null) {
						sender.sendMessage(ChatColor.RED + "エラー " +wolfTypeName + "は見つかりませんでした");
						return true;
					}
					this.allocatedPoints = new ArrayList<Integer>();
					for(int i = 0; i < 4; i++) {
						String s = args[i];
						if(s.matches("\\d+")) {
							this.allocatedPoints.add(Integer.parseInt(s)); //NullPointerException
						}
						else{
							sender.sendMessage(ChatColor.RED + "エラー 正しく数値を入力してください");
							return true;
						}
					}
					for(int i : this.allocatedPoints) {
						if(i > 30) {
							sender.sendMessage(ChatColor.RED + "エラー 1つのステータスには30までしか割り振れません");
							return true;
						}
					}
					int sum = allocatedPoints.get(0) + allocatedPoints.get(1) + allocatedPoints.get(2) + allocatedPoints.get(3);
					if(sum > 60) {
						sender.sendMessage(ChatColor.RED + "エラー 割り振られたポイントの合計が60を超えています");
						return true;
					}
					else if(sum < 60) {
						sender.sendMessage(ChatColor.YELLOW + "確認 割り振られたポイントの合計が60未満です。");
					}
					Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
					FightingWolf fightingWolf = new FightingWolf(player, wolf, wolfType, allocatedPoints);
					FightingWolf.fightingWolfSet.add(fightingWolf);
					sender.sendMessage(ChatColor.AQUA + "オオカミの召喚に成功しました");
					StringBuilder sb = new StringBuilder();
					sb.append(ChatColor.GREEN).append("=====================").append("\n")
							.append(fightingWolf.getWolfType().getTypeName()).append("\n")
							.append(ChatColor.RED).append("STR ").append(ChatColor.GOLD).append("CON ")
							.append(ChatColor.LIGHT_PURPLE).append("INT ").append(ChatColor.BLUE).append("DEX").append("\n")
							.append(fightingWolf.strength).append(" ").append(fightingWolf.constitution).append(" ")
							.append(fightingWolf.intelligence).append(" ").append(fightingWolf.dexterity).append("\n")
							.append(ChatColor.GREEN).append("=====================");
					sender.sendMessage(sb.toString()); //召喚したオオカミの情報を表示する
					return true;
				}
			}
		}
		return false;
	}



}
