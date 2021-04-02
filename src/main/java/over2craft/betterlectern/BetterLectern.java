package over2craft.betterlectern;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterLectern extends JavaPlugin {

    private static BetterLectern instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new BookListener(), this);
    }

    protected static BetterLectern getInstance() {
        return instance;
    }
}
