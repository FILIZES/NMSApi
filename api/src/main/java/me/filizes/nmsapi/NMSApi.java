package me.filizes.nmsapi;

import lombok.Getter;
import me.filizes.nmsapi.api.NBTManager;
import me.filizes.nmsapi.api.NPCManager;
import org.bukkit.plugin.Plugin;

@Getter
public class NMSApi {

    private NBTManager nbtManager;
    private NPCManager npcManager;
    private final String version;

    public NMSApi(Plugin plugin) {
        version = plugin
                .getServer()
                .getClass()
                .getPackage()
                .getName()
                .split("\\.")[3];

        if (version.equals("v1_12_R1")) {
            nbtManager = new me.filizes.nmsapi.v1_12_R1.NBTManager();
            npcManager = new me.filizes.nmsapi.v1_12_R1.NPCManager(plugin);
        } else if (version.equals("v1_13_R2")) {
            nbtManager = new me.filizes.nmsapi.v1_13_R2.NBTManager();
            npcManager = new me.filizes.nmsapi.v1_13_R2.NPCManager(plugin);
        }

    }

}
