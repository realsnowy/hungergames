package tf.justdisablevac.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import tf.justdisablevac.hungergames.Configuration;

public class SavelocCommand implements CommandExecutor {
    private FileConfiguration config = new Configuration();
    
    private static final String PLAYER_KEY = "player";
    private static final String CHEST_KEY = "chest";
    private static final String SUBPATH = "locations.%s.location%d";
    private static final int MAX_LOCATIONS = 24;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length != 2 && args.length != 3) {
                player.sendMessage(ChatColor.RED + "Invalid number of arguments! Usage: /saveloc <player|chest> <number> [overwrite]");
                return false;
            }
            if (!args[0].equalsIgnoreCase(PLAYER_KEY) && !args[0].equalsIgnoreCase(CHEST_KEY)) {
                player.sendMessage(ChatColor.RED + "Invalid argument! Usage: /saveloc <player|chest> <number> [overwrite]");
                return false;
            }
            if (!args[1].matches("\\d+")) {
                player.sendMessage(ChatColor.RED + "Invalid number format!");
                return false;
            }

            String key = args[0].equalsIgnoreCase(PLAYER_KEY) ? PLAYER_KEY : CHEST_KEY;
            int number = Integer.parseInt(args[1]);
            String path = String.format(SUBPATH, key, number);

            if (number > MAX_LOCATIONS) {
                player.sendMessage(ChatColor.RED + "Maximum number of locations reached! You can only have " + MAX_LOCATIONS + " locations.");
                return false;
            }

            if (config.contains(path) && args.length != 3) {
                player.sendMessage(ChatColor.RED + "Location already exists! Use /saveloc <player|chest> <number> overwrite to overwrite the existing location.");
                return false;
            }

            // Check if the previous location exists
            if (number > 1 && !config.contains(String.format(SUBPATH, key, number - 1))) {
                player.sendMessage(ChatColor.RED + "Previous location does not exist! Locations must be incremented by one.");
                player.sendMessage(ChatColor.GREEN + "Use location " + (number - 1) + " as the previous location.");
                return false;
            }

            Location location = player.getLocation();
            String coords = String.format("%.0f-%.0f-%.0f", location.getX(), location.getY(), location.getZ());

            if (args.length == 3 && args[2].equalsIgnoreCase("overwrite")) {
                config.set(path, coords);
                player.sendMessage(String.format("Overwrote location %d at %s for %s", number, coords, args[0]));
            } else {
                config.set(path, coords);
                player.sendMessage(String.format("Saved location %d at %s for %s", number, coords, args[0]));
            }
        }
        return true;
    }
}
