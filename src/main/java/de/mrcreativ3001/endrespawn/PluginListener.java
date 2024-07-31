package de.mrcreativ3001.endrespawn;

import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.util.Vector;

public class PluginListener implements Listener {
    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        // We're a player
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) entity;

        // We're taking damage in the end
        if (player.getWorld().getEnvironment() != Environment.THE_END) {
            return;
        }

        // We're taking damage from the void
        if (event.getCause() != DamageCause.VOID) {
            return;
        }

        // We're dying from the void damage
        if (player.getHealth() - event.getFinalDamage() > 0.0) {
            return;
        }

        // cancel that: heal and send to obsidian end spawn
        event.setCancelled(true);

        player.setHealth(20.0);
        // Note: obsidian island always at these coords:
        // https://minecraft.fandom.com/wiki/Obsidian_platform
        player.teleport(new Location(player.getWorld(), 100, 49, 0));
        player.setVelocity(new Vector());
        player.setFallDistance(0.0f);
    }
}
