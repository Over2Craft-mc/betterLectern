package over2craft.betterlectern;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Lectern;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class BookListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock() != null && e.getClickedBlock().getState() instanceof Lectern) {
                if (!BetterLectern.getInstance().getConfig().getBoolean(Config.NEEDS_SIGN) || hasSignNear(e.getClickedBlock())) {
                    Lectern lectern = (Lectern) e.getClickedBlock().getState();

                    if (lectern.getInventory().getItem(0) != null) {
                        e.getPlayer().openBook(Objects.requireNonNull(lectern.getInventory().getItem(0)));
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    public boolean hasSignNear(Block block) {
        for(BlockFace blockFace : new BlockFace[] {BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH, BlockFace.NORTH}) {
            if (block.getRelative(blockFace).getState() instanceof Sign) {
                Sign sign = (Sign) block.getRelative(blockFace).getState();
                if (containsKey(sign)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsKey(Sign sign) {
        String key = BetterLectern.getInstance().getConfig().getString(Config.SIGN_TEXT,"BetterLectern");
        assert key != null;
        for (String line : sign.getLines()) {
            if(BetterLectern.getInstance().getConfig().getBoolean(Config.SIGN_TEXT_IS_CASE_SENSITIVE,false)) {
                if (line.contains(key)) {
                    return true;
                }
            } else {
                if(line.toLowerCase().contains(key.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}
