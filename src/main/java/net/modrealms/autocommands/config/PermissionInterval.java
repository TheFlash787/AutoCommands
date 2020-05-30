package net.modrealms.autocommands.config;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable @Data
public class PermissionInterval {
    @Setting(comment = "The permission required in order to start this interval task for the player")
    private String permission;
    @Setting(comment = "The interval in which these commands will run in minutes")
    private int interval;
    @Setting(value = "console-commands", comment = "A list of commands that will run for each player online who has the specified permission (with console permission)")
    private List<String> consoleCommands;
    @Setting(value = "player-commands", comment = "A list of commands that will run as the player who has the specified permission (using the players permission)")
    private List<String> playerCommands;

    @Setting(comment = "Specifies whether this interval is enabled for players or not")
    private boolean enabled;

    public PermissionInterval(String permission, int interval){
        this.permission = permission;
        this.interval = interval;
        this.consoleCommands = new ArrayList<String>(){{
            add("adminpay ");
        }};
        this.playerCommands = new ArrayList<String>(){{
            add("pay ");
        }};
        this.enabled = true;
    }

    public PermissionInterval(){

    }
}
