package de.mrcreativ3001.endrespawn;

import org.bukkit.plugin.java.JavaPlugin;

public class EndRespawn extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PluginListener(), this);
    }
}
