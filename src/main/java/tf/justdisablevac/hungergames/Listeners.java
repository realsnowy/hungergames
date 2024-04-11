package tf.justdisablevac.hungergames;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import tf.justdisablevac.hungergames.commands.ParticipateCommand;

public class Listeners implements Listener {
    private FileConfiguration config = new Configuration();

    @EventHandler
    public void onPlayerDestroyBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
            player.sendMessage("You destroyed a block!");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (ParticipateCommand.instance != null && ParticipateCommand.instance.isPlayerInList(player)) {
            Location from = event.getFrom();
            Location to = event.getTo();
            double x=Math.floor(from.getX());
            double z=Math.floor(from.getZ());
            if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z)
            {
                x+=.5;
                z+=.5;
                event.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
            }
        }
    }
}
