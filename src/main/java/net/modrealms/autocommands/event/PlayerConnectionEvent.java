package net.modrealms.autocommands.event;

import net.modrealms.autocommands.AutoCommands;
import net.modrealms.autocommands.config.MainConfiguration;
import net.modrealms.autocommands.config.PermissionInterval;
import net.modrealms.autocommands.tasks.IntervalTask;
import net.modrealms.autocommands.tasks.PlayerTask;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.scheduler.Task;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerConnectionEvent {
    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event){
        Player player = event.getTargetEntity();
        Set<Task> tasks = Sponge.getScheduler().getScheduledTasks(AutoCommands.getInstance()).stream().filter(t -> t.getName().contains(player.getName())).collect(Collectors.toSet());
        if(tasks.isEmpty()){
            /* Add a new interval task for the player-commands/permission-commands if one doesn't already exist */
            for(PermissionInterval interval : MainConfiguration.Player.permissionIntervals){
                if(interval.isEnabled()){
                    if(interval.getPermission().isEmpty() || player.hasPermission(interval.getPermission())) {
                        Task.builder().execute(new IntervalTask(player, interval)).delay(interval.getInterval(), TimeUnit.MINUTES).interval(MainConfiguration.Interval.interval, TimeUnit.MINUTES).name(player.getName() + "_" + interval.getPermission()).submit(AutoCommands.getInstance());
                        AutoCommands.getInstance().getLogger().info("Started a new command-intervals task for " + player.getName() + " (" + interval.getPermission() + ")");
                    }
                }
            }
        }
        Task.builder().execute(new PlayerTask(player)).name(player.getName() + "_join").submit(AutoCommands.getInstance());
    }
}
