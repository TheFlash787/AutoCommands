package net.modrealms.autocommands.tasks;

import net.modrealms.autocommands.AutoCommands;
import net.modrealms.autocommands.config.MainConfiguration;
import net.modrealms.autocommands.config.PermissionInterval;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class IntervalTask implements Consumer<Task> {
    private static final AutoCommands autoCommands = AutoCommands.getInstance();

    private UUID playerUuid;
    private PermissionInterval permissionInterval;

    public IntervalTask(Player player, PermissionInterval permissionInterval){
        this.playerUuid = player.getUniqueId();
        this.permissionInterval = permissionInterval;
    }

    public IntervalTask(){}

    @Override
    public void accept(Task task) {
        if(this.playerUuid == null){
            /* If a player isn't specified, it'll only run the console-commands */
            for(String command : MainConfiguration.Interval.consoleCommands){
                if(command.isEmpty()) continue;
                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command.replace("/", ""));
            }
        } else {
            Optional<Player> playerOptional = Sponge.getServer().getPlayer(this.playerUuid);
            if(playerOptional.isPresent()){
                /* If a player is specified and is online, run the player-commands and permission-commands */
                Player player = playerOptional.get();
                if(player.hasPermission(permissionInterval.getPermission())){
                    for(String commands : permissionInterval.getCommands()){
                        if(commands.isEmpty()) continue;
                        Sponge.getCommandManager().process(player, commands.replace("/", "").replace("{player}", player.getName()));
                    }
                } else {
                    autoCommands.getLogger().info(player.getName() + " no longer has the permission for task " + permissionInterval.getPermission() + " so it has been cancelled.");
                    task.cancel();
                }
            } else {
                /* If the player is offline, cancel the task */
                task.cancel();
            }
        }
    }
}
