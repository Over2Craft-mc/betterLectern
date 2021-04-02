package over2craft.betterlectern;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Lectern;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.Objects;

public class BookListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock() != null && e.getClickedBlock().getState() instanceof Lectern) {

                if (hasSignNear(e.getClickedBlock())) {
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
        if (block.getRelative(BlockFace.EAST).getState() instanceof Sign) {
            Sign sign = (Sign) block.getRelative(BlockFace.EAST).getState();
            if (containsKey(sign)) {
                return true;
            }
        }

        if (block.getRelative(BlockFace.NORTH).getState() instanceof Sign) {
            Sign sign = (Sign) block.getRelative(BlockFace.NORTH).getState();
            if (containsKey(sign)) {
                return true;
            }
        }

        if (block.getRelative(BlockFace.SOUTH).getState() instanceof Sign) {
            Sign sign = (Sign) block.getRelative(BlockFace.SOUTH).getState();
            if (containsKey(sign)) {
                return true;
            }
        }

        if (block.getRelative(BlockFace.WEST).getState() instanceof Sign) {
            Sign sign = (Sign) block.getRelative(BlockFace.WEST).getState();
            return containsKey(sign);
        }

        return false;
    }

    public boolean containsKey(Sign sign) {
        for (String line : sign.getLines()) {
            if (line.contains("betterLectern")) {
                return true;
            }
        }
        return false;
    }
}
