package net.modrealms.autocommands.config;

import lombok.Data;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable @Data
public class MainConfiguration {
    @Setting(value = "startup")
    private MainConfiguration.Startup startup = new MainConfiguration.Startup();
    @Setting(value = "delay")
    private MainConfiguration.Delay delay = new MainConfiguration.Delay();
    @Setting(value = "interval")
    private MainConfiguration.Interval interval = new MainConfiguration.Interval();

    @ConfigSerializable @Data
    public static class Startup {
        @Setting(value = "enabled", comment = "This module will run the specified commands on startup")
        public static boolean enabled = false;
        @Setting(value = "startup-commands", comment = "The commands specified will be run when the server has started up.")
        public static List<String> commands = new ArrayList<>();
    }

    @ConfigSerializable @Data
    public static class Delay {
        @Setting(value = "enabled", comment = "This module will run the specified commands after the specified delay in minutes")
        public static boolean enabled = false;
        @Setting(value = "delayed-commands", comment = "The commands will be run after the delay")
        public static List<String> commands = new ArrayList<>();
        @Setting(value = "delay-in-minutes", comment = "Specify how many minutes the server should wait before executing the commands")
        public static int delay = 2;
    }

    @ConfigSerializable @Data
    public static class Interval {
        @Setting(value = "enabled", comment = "This module will run the specified commands at every interval that you have specified below in minutes")
        public static boolean enabled = false;
        @Setting(value = "interval-commands", comment = "The commands will be run at each interval")
        public static List<String> commands = new ArrayList<>();
        @Setting(value = "interval-in-minutes", comment = "Specify how often the server should run these commands in minutes")
        public static int interval = 1;
    }
}
