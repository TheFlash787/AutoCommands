package net.modrealms.autocommands.tasks;

import net.modrealms.autocommands.config.MainConfiguration;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import java.util.function.Consumer;

public class PlayerTask implements Consumer<Task> {

    private Player player;

    public PlayerTask(Player player){
        this.player = player;
    }

    @Override
    public void accept(Task task) {
        for(String command : MainConfiguration.Player.onJoinCommands){
            if(command.isEmpty()) continue;
            Sponge.getCommandManager().process(player, command.replace("/", "").replace("{player}", player.getName()));
        }
    }
}
