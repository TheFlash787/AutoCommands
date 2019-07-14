package net.modrealms.autocommands;

import com.google.inject.Inject;
import lombok.Getter;
import net.modrealms.autocommands.config.MainConfiguration;
import net.modrealms.autocommands.manager.ConfigManager;
import net.modrealms.autocommands.tasks.DelayTask;
import net.modrealms.autocommands.tasks.IntervalTask;
import net.modrealms.autocommands.tasks.StartupTask;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import sun.applet.Main;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Plugin(id = "autocommands", name = "AutoCommands", authors = "TheFlash787")
public class AutoCommands {
    @Inject
    @Getter
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject @Getter
    private Logger logger;

    @Getter
    private ConfigManager configManager;

    private static AutoCommands instance;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        /* Setting the instance */
        instance = this;

        /* Setting up configuration */
        this.configManager = new ConfigManager(loader);

        /* Execute the startup tasks */
        if(MainConfiguration.Startup.enabled){
            Task.builder().execute(new StartupTask()).submit(this);
        }

        /* Start the delayed tasks if used */
        if(MainConfiguration.Delay.enabled){
            Task.builder().delay(MainConfiguration.Delay.delay, TimeUnit.MINUTES).execute(new DelayTask()).submit(this);
        }

        /* Start the interval tasks if used */
        if(MainConfiguration.Interval.enabled){
            Task.builder().interval(MainConfiguration.Interval.interval, TimeUnit.MINUTES).execute(new IntervalTask()).submit(this);
        }
    }

    @Listener
    public void onServerStop(GameStoppingServerEvent event) {

    }

    public static AutoCommands getInstance(){
        return instance;
    }
}
