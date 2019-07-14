package net.modrealms.autocommands.tasks;

import net.modrealms.autocommands.AutoCommands;
import net.modrealms.autocommands.config.MainConfiguration;
import org.spongepowered.api.Sponge;

public class IntervalTask implements Runnable {
    private static final AutoCommands autoCommands = AutoCommands.getInstance();

    @Override
    public void run() {
        for(String command :  MainConfiguration.Interval.commands){
            /* Execute each command, removing the / if it's present */
            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command.replace("/", ""));
        }
//        autoCommands.getLogger().info("Executed all of the specified interval-commands");
    }
}
