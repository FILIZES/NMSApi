package me.filizes.nmsapi.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface NPCManager {
    public Player createNpc(Location location, String name);
    public void setSkin(Player player, String skin);
    public void removeNpc(Player player);
}
