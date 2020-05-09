package net.modrealms.autocommands.config;

import lombok.Data;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ConfigSerializable @Data
public class MainConfiguration {
    @Setting(value = "startup")
    private MainConfiguration.Startup startup = new MainConfiguration.Startup();
    @Setting(value = "delay")
    private MainConfiguration.Delay delay = new MainConfiguration.Delay();
    @Setting(value = "interval")
    private MainConfiguration.Interval interval = new MainConfiguration.Interval();
    @Setting(value = "player")
    private MainConfiguration.Player player = new MainConfiguration.Player();

    @ConfigSerializable
    public static class Startup {
        @Setting(value = "enabled", comment = "This module will run the specified commands on startup")
        public static boolean enabled = false;
        @Setting(value = "console-commands", comment = "The commands specified will be run when the server has started up, through the console.")
        public static List<String> consoleCommands = new ArrayList<String>(){{
            add("say hello world!");
        }};
    }
    @ConfigSerializable
    public static class Delay {
        @Setting(value = "enabled", comment = "This module will run the specified commands after the specified delay in minutes")
        public static boolean enabled = false;
        @Setting(value = "delay-in-minutes", comment = "Specify how many minutes the server should wait before executing the commands")
        public static int delay = 2;
        @Setting(value = "console-commands", comment = "The commands will be run after the delay on startup,, through the console.")
        public static List<String> consoleCommands = new ArrayList<String>(){{
            add("say hello world!");
        }};
    }
    @ConfigSerializable
    public static class Interval {
        @Setting(value = "enabled", comment = "This module will run the specified commands at every interval that you have specified below in minutes")
        public static boolean enabled = false;
        @Setting(value = "interval-in-minutes", comment = "Specify how often the server should run these commands in minutes")
        public static int interval = 1;
        @Setting(value = "run-straightaway", comment = "Specify whether you want to run the commands for the first time AFTER the interval specified, or whether you want to run the commands straight away, and then again after the interval")
        public static boolean runBeforeInterval = true;
        @Setting(value = "console-commands", comment = "The commands will be run at each interval (from startup), through the console.")
        public static List<String> consoleCommands = new ArrayList<String>(){{
            add("say hello world!");
        }};
    }
    @ConfigSerializable
    public static class Player {
        @Setting(value = "intervals", comment = "We recommend restarting your server if you applied any changes here. Players who are already online will have old intervals/commands/permissions set!")
        public static List<PermissionInterval> permissionIntervals = new ArrayList<PermissionInterval>(){{
            add(new PermissionInterval("group.one", 5, new ArrayList<String>(){{
                add("permission interval command");
            }}));
            add(new PermissionInterval("group.two", 10, new ArrayList<String>(){{
                add("permission interval command");
            }}));
        }};

        @Setting(value = "on-join-commands", comment = "These commands will be run for all players upon login, as their player, and will respect permission plugins")
        public static List<String> onJoinCommands = new ArrayList<String>(){{
            add("join command");
        }};
    }
}
