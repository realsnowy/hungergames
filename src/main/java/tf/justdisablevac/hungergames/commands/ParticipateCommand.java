package tf.justdisablevac.hungergames.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tf.justdisablevac.hungergames.Configuration;

public class ParticipateCommand implements CommandExecutor {
    private Configuration config = new Configuration();
    private HashMap<Player, Location> participants = new HashMap<>();

    private static final int MAX_LOCATIONS = 24;
    public static ParticipateCommand instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        instance = this;
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(participants.containsKey(player)) {
                player.sendMessage(ChatColor.RED + "You are already participating!");
                return false;
            }

            Location randomLocation = getLocationFromString(getRandomLocation());
            participants.put(player, randomLocation);
            player.teleport(randomLocation);

            Bukkit.broadcastMessage(participants.toString()); // debug
            player.sendMessage(ChatColor.YELLOW + "You are now participating in the game!");
        }
        return true;
    }

    private Location getLocationFromString(String locationString) {
        String[] coordinates = locationString.split("-");
        World world = Bukkit.getWorld("world");
        double x = Double.parseDouble(coordinates[0]);
        double y = Double.parseDouble(coordinates[1]);
        double z = Double.parseDouble(coordinates[2]);
        return new Location(world, x, y, z);
    }

    private String getRandomLocation() {
        int locationCount = 0;
        for(int i = 0; i < MAX_LOCATIONS; i++) {
            String locationPath = String.format("locations.player.location%d", i);
            if(config.contains(locationPath)) {
                locationCount++;
            }
        }

        int randomIndex = (int) (Math.random() * locationCount) + 1;
        String locationPath = String.format("locations.player.location%d", randomIndex);
        return config.getString(locationPath);
    }

    public void removeParticipant(Player player) {
        participants.remove(player);
    }

    public boolean isPlayerInList(Player player) {
        return participants.containsKey(player);
    }
}
