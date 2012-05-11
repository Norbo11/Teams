package com.github.norbo11.teams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Teams extends JavaPlugin
{

    // Methods
    ListenerCommandExecutor commandExecutor;
    Methods methods;
    MethodsError methodsError;
    MethodsFile methodsFile;
    ListenerGeneral listenerGeneral;

    Logger log;
    String pluginTag = ChatColor.GOLD + "[Teams] ";
    File pluginDir;
    File pluginConfig;
    String version = "1.0";
    public List<Team> teams = new ArrayList<Team>();

    // Colours
    ChatColor red = ChatColor.RED;
    ChatColor green = ChatColor.GREEN;
    ChatColor aqua = ChatColor.AQUA;
    ChatColor gold = ChatColor.GOLD;
    ChatColor white = ChatColor.WHITE;

    public String lineString = "-------------------------";

    public Logger getLog()
    {
        return log;
    }

    public File getPluginDir()
    {
        return pluginDir;
    }

    public String getPluginTag()
    {
        return pluginTag;
    }

    public void onDisable()
    {
        log.info("Teams v" + version + " disabled!");
    }

    public void onEnable()
    {
        // Create logger
        log = getLogger();

        pluginDir = getDataFolder();
        pluginConfig = new File(pluginDir, "config.yml");

        // Create classes
        commandExecutor = new ListenerCommandExecutor(this);
        methods = new Methods(this);
        methodsError = new MethodsError(this);
        methodsFile = new MethodsFile(this);
        listenerGeneral = new ListenerGeneral(this);
        getServer().getPluginManager().registerEvents(listenerGeneral, this);

        // Set executors
        getCommand("team").setExecutor(commandExecutor);

        // Make config
        if (!pluginConfig.exists())
        {
            log.info("Creating config file...");
            getConfig().options().copyDefaults();
            saveDefaultConfig();
        }

        methods.getTeams();

        log.info("Teams v" + version + " enabled!");
    }
}
