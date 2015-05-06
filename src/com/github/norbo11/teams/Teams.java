package com.github.norbo11.teams;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Teams extends JavaPlugin
{

    // Listeners
    ListenerCommandExecutor commandExecutor;
    ListenerGeneral listenerGeneral;

    // Colours
    ChatColor red = ChatColor.RED;
    ChatColor green = ChatColor.GREEN;
    ChatColor aqua = ChatColor.AQUA;
    ChatColor gold = ChatColor.GOLD;
    ChatColor white = ChatColor.WHITE;

    Logger log;
    String lineString = "-------------------------";
    String pluginTag = ChatColor.GOLD + "[Teams] ";
    String version;
    File filePluginDir;
    File filePluginConfig;
    File fileTeamsConfig;
    YamlConfiguration configTeams;

    static Teams p;
    Map<String, String> playerGroupChoice = new HashMap<String, String>();
    Map<String, String> playerChatChoice = new HashMap<String, String>();
    Map<String, String> invites = new HashMap<String, String>();

    public void onDisable()
    {
        log.info("Teams v" + version + " disabled!");
    }

    public void onEnable()
    {
        version = getDescription().getVersion();
        // Create logger
        log = getLogger();

        filePluginDir = getDataFolder();
        filePluginConfig = new File(filePluginDir, "config.yml");
        fileTeamsConfig = new File(filePluginDir, "teams.yml");

        try
        {   
            // Make plugin directory
            if (!filePluginDir.exists()) filePluginDir.mkdir();
            
            // Make teams.yml
            if (!fileTeamsConfig.exists()) fileTeamsConfig.createNewFile();
            
            // Load teams.yml 
            configTeams = YamlConfiguration.loadConfiguration(fileTeamsConfig);

            // Make config
            if (!filePluginConfig.exists())
            {
                getConfig().options().copyDefaults();
                saveDefaultConfig();
            }
            
            //Inform the player to change the config if "Member1" exists as a default value in the config
            if (getConfig().getStringList("Default Team.managers").contains("Manager1")) log.info("Plugin has detected that you have not changed config.yml to suit your own needs! To get rid of this message, remove 'Manager1' as a default manager of each team.");
        } catch (Exception e)
        {
            terminate("An error has occurred while creating files!", e);
            return;
        }

        // Create listeners
        commandExecutor = new ListenerCommandExecutor(this);
        listenerGeneral = new ListenerGeneral(this);
        getServer().getPluginManager().registerEvents(listenerGeneral, this);

        // Set executors
        getCommand("teams").setExecutor(commandExecutor);
        getCommand("tc").setExecutor(commandExecutor);

        p = this;

        log.info("Teams v" + version + " enabled!");
    }

    public void terminate(String message, Exception e)
    {
        log.severe(message);
        if (e != null) e.printStackTrace();
        getServer().getPluginManager().disablePlugin(this);
    }
}
