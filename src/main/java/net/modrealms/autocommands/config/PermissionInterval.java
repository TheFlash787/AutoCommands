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
    @Setting(comment = "A list of commands that will run as each player who has the specified permission")
    private List<String> commands;
    @Setting(comment = "Specifies whether this interval is enabled for players or not")
    private boolean enabled;

    public PermissionInterval(String permission, int interval, List<String> commands){
        this.permission = permission;
        this.interval = interval;
        this.commands = commands;
        this.enabled = true;
    }
}
