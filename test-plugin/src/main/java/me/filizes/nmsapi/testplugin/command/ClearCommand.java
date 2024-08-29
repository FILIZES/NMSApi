package me.filizes.nmsapi.testplugin.command;

import me.filizes.nmsapi.testplugin.TestPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearCommand implements CommandExecutor {

    private final TestPlugin plugin;
    public ClearCommand(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return true;
    }
}
