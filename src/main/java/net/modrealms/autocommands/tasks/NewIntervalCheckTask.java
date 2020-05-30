package net.modrealms.autocommands.tasks;

import net.modrealms.autocommands.AutoCommands;
import net.modrealms.autocommands.config.MainConfiguration;
import net.modrealms.autocommands.config.PermissionInterval;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NewIntervalCheckTask implements Consumer<Task> {

    private final AutoCommands autoCommands = AutoCommands.getInstance();

    @Override
    public void accept(Task task) {
        for(Player player : Sponge.getServer().getOnlinePlayers()){
            Set<Task> tasks = Sponge.getScheduler().getScheduledTasks(autoCommands).stream().filter(t -> t.getName().contains(player.getName())).collect(Collectors.toSet());
            List<Task> invalidTasks = getInvalidTasks(tasks);
            for(Task invalid : invalidTasks) invalid.cancel();
            //if(!invalidTasks.isEmpty()) autoCommands.getLogger().info("Removed " + invalidTasks.size() + " invalid interval-tasks from " + player.getName());

            for(PermissionInterval interval : MainConfiguration.Player.permissionIntervals){
                if(tasks.stream().noneMatch(t -> t.getName().contains(interval.getPermission()))){
                    Task.builder().execute(new IntervalTask(player, interval)).delay(interval.getInterval(), TimeUnit.MINUTES).interval(MainConfiguration.Interval.interval, TimeUnit.MINUTES).name(player.getName() + "_" + interval.getPermission()).submit(AutoCommands.getInstance());
                }
            }
        }
    }

    public List<Task> getInvalidTasks(Set<Task> tasks){
        List<Task> invalidTasks = new ArrayList<>();
        List<PermissionInterval> intervalList = MainConfiguration.Player.permissionIntervals;
        for(Task task : tasks){
            if(intervalList.stream().noneMatch(i -> task.getName().contains(i.getPermission()))){
                invalidTasks.add(task);
            }
        }
        return invalidTasks;
    }
}
