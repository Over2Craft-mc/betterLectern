package over2craft.betterlectern;

import io.papermc.paper.event.player.PlayerLecternPageChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterLectern extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BookListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
