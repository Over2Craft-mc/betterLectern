package over2craft.betterlectern;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lectern;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class BookListener implements Listener {
    private static final BlockFace[] SIDES = {BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST};
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!(e.getClickedBlock().getState() instanceof Lectern)) return;
        if (!hasSignNear(e.getClickedBlock())) return;
        Lectern lectern = (Lectern) e.getClickedBlock().getState();
        if (lectern.getInventory().getItem(0) == null) return;
        
    	e.setCancelled(true);
        e.getPlayer().openBook(Objects.requireNonNull(lectern.getInventory().getItem(0)));
    }

    public boolean hasSignNear(Block block) {
        for (BlockFace side : SIDES) {
            BlockState state = block.getRelative(side).getState();
            if (!(state instanceof Sign)) continue;
            Sign sign = (Sign) state;
            for (String line : sign.getLines()) {
                if (line.contains("betterLectern")) {
                    return true;
                }
            }
        }
        return false;
    }
}
