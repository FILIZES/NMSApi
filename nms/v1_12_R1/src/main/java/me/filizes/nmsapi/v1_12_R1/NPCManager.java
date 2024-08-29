package me.filizes.nmsapi.v1_12_R1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPCManager implements me.filizes.nmsapi.api.NPCManager, Listener {

    private final Plugin plugin;
    private final List<EntityPlayer> npcs = new ArrayList<>();

    public NPCManager(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public Player createNpc(Location location, String name) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));

        npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        Bukkit.getOnlinePlayers().forEach(player -> {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
//            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
            }, 50);
        });

        npcs.add(npc);

        return npc.getBukkitEntity();
    }

    @Override
    public void setSkin(Player player, String skin) {
        String texture;
        String signature;
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + skin);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = new JsonParser().parse(reader)
                    .getAsJsonObject()
                    .get("id")
                    .getAsString();

            URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid
            + "?unsigned=false");
            InputStreamReader reader2 = new InputStreamReader(url2.openStream());

            JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties")
                    .getAsJsonArray().get(0).getAsJsonObject();

            texture = property.get("value").getAsString();
            signature = property.get("signature").getAsString();
        } catch (Exception e){
            EntityPlayer p = ((CraftPlayer) player).getHandle();
            GameProfile profile = p.getProfile();
            Property property = profile.getProperties().get("textures").iterator().next();
            texture = property.getValue();
            signature = property.getSignature();
        }

        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        GameProfile gameProfile = nmsPlayer.getProfile();

        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));

    }

    @Override
    public void removeNpc(Player playerNpc) {
        for (EntityPlayer npc : new ArrayList<>(npcs)) {
            if (npc.getUniqueID().equals(playerNpc.getUniqueId())) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
                    PlayerConnection connection = nmsPlayer.playerConnection;

                    connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
                });

                npcs.remove(npc);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        npcs.forEach(npc -> {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
//            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
//            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
            }, 50);
        });
    }

}
