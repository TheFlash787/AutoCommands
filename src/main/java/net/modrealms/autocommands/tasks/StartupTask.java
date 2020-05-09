package net.modrealms.autocommands.tasks;

import net.modrealms.autocommands.AutoCommands;
import net.modrealms.autocommands.config.MainConfiguration;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

public class StartupTask implements Runnable {
    private static final AutoCommands autoCommands = AutoCommands.getInstance();

    @Override
    public void run() {
        for(String command : MainConfiguration.Startup.consoleCommands){
            if(command.isEmpty()) continue;
            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command.replace("/", ""));
        }
    }
}
