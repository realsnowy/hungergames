package tf.justdisablevac.hungergames;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("HungerGames plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("HungerGames plugin disabled!");
    }
}