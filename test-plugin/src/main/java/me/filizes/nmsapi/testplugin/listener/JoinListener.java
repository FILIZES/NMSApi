package me.filizes.nmsapi.testplugin.listener;

import me.filizes.nmsapi.api.NPCManager;
import me.filizes.nmsapi.testplugin.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final TestPlugin plugin;
    public JoinListener(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        NPCManager npcManager = plugin.getNmsApi().getNpcManager();

        Player player = event.getPlayer();

        Player npc = npcManager.createNpc(player.getLocation(), "Player123");
        npcManager.setSkin(npc, "IJustFortiLive");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            npcManager.removeNpc(npc);
        }, 20L * 20);
    }

}
