package net.modrealms.autocommands.manager;

import com.google.common.reflect.TypeToken;
import lombok.Data;
import net.modrealms.autocommands.AutoCommands;
import net.modrealms.autocommands.config.MainConfiguration;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.io.IOException;

@Data
public class ConfigManager {
    private ConfigurationLoader<CommentedConfigurationNode> configurationLoader;
    private ConfigurationOptions options;
    private MainConfiguration mainConfiguration;

    private static final AutoCommands autoCommands = AutoCommands.getInstance();

    public ConfigManager(ConfigurationLoader<CommentedConfigurationNode> loader){
        configurationLoader = loader;
        options = ConfigurationOptions.defaults().setShouldCopyDefaults(true);
        update();
    }

    public void update(){
        try{
            CommentedConfigurationNode node = configurationLoader.load(options);
            MainConfiguration cfg = node.getValue(TypeToken.of(MainConfiguration.class), new MainConfiguration());
            configurationLoader.save(node);
            mainConfiguration = cfg;
            autoCommands.getLogger().info("Updated the configuration");
        } catch (IOException | ObjectMappingException ex){
            ex.printStackTrace();
        }
    }
}
