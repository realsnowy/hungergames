package tf.justdisablevac.hungergames;

import org.bukkit.plugin.java.JavaPlugin;

import tf.justdisablevac.hungergames.commands.LeaveCommand;
import tf.justdisablevac.hungergames.commands.ParticipateCommand;
import tf.justdisablevac.hungergames.commands.SavelocCommand;
import tf.justdisablevac.hungergames.commands.TestCommand;

public class Main extends JavaPlugin {
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getCommand("test").setExecutor(new TestCommand());
        getCommand("saveloc").setExecutor(new SavelocCommand());
        getCommand("participate").setExecutor(new ParticipateCommand());
        getCommand("leave").setExecutor(new LeaveCommand());
    }
}