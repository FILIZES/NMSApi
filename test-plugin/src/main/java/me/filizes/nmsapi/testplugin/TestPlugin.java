package me.filizes.nmsapi.testplugin;

import lombok.Getter;
import me.filizes.nmsapi.NMSApi;
import me.filizes.nmsapi.testplugin.command.ClearCommand;
import me.filizes.nmsapi.testplugin.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class TestPlugin extends JavaPlugin {

    private NMSApi nmsApi;

    @Override
    public void onEnable() {
        nmsApi = new NMSApi(this);

        this.getLogger().info("VERSION OF SERVER " + nmsApi.getVersion());

        Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
        getCommand("clear").setExecutor(new ClearCommand(this));
    }

}
