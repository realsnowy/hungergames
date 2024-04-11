package tf.justdisablevac.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            ParticipateCommand.instance.removeParticipant(player);
            player.sendMessage(ChatColor.YELLOW + "You have left the game.");
        }
        return true;
    }
}
