package com.github.norbo11.teams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Methods
{
    static Teams p = Teams.p;

    public static String convertCodesToColor(String string) throws Exception
    {
        String returnValue = string;

        returnValue = returnValue.replaceAll("&0", ChatColor.BLACK + "");
        returnValue = returnValue.replaceAll("&1", ChatColor.DARK_BLUE + "");
        returnValue = returnValue.replaceAll("&2", ChatColor.DARK_GREEN + "");
        returnValue = returnValue.replaceAll("&3", ChatColor.DARK_AQUA + "");
        returnValue = returnValue.replaceAll("&4", ChatColor.DARK_RED + "");
        returnValue = returnValue.replaceAll("&5", ChatColor.DARK_PURPLE + "");
        returnValue = returnValue.replaceAll("&6", ChatColor.GOLD + "");
        returnValue = returnValue.replaceAll("&7", ChatColor.GRAY + "");
        returnValue = returnValue.replaceAll("&8", ChatColor.DARK_GRAY + "");
        returnValue = returnValue.replaceAll("&9", ChatColor.BLUE + "");
        returnValue = returnValue.replaceAll("&a", ChatColor.GREEN + "");
        returnValue = returnValue.replaceAll("&b", ChatColor.AQUA + "");
        returnValue = returnValue.replaceAll("&c", ChatColor.RED + "");
        returnValue = returnValue.replaceAll("&d", ChatColor.LIGHT_PURPLE + "");
        returnValue = returnValue.replaceAll("&e", ChatColor.YELLOW + "");
        returnValue = returnValue.replaceAll("&f", ChatColor.WHITE + "");

        returnValue = returnValue.replaceAll("&k", ChatColor.MAGIC + "");
        returnValue = returnValue.replaceAll("&l", ChatColor.BOLD + "");
        returnValue = returnValue.replaceAll("&m", ChatColor.STRIKETHROUGH + "");
        returnValue = returnValue.replaceAll("&n", ChatColor.UNDERLINE + "");
        returnValue = returnValue.replaceAll("&f", ChatColor.ITALIC + "");
        returnValue = returnValue.replaceAll("&r", ChatColor.RESET + "");

        return returnValue;
    }

    public static String convertColorsToCode(String string) throws Exception
    {
        String returnValue = string;

        returnValue = returnValue.replaceAll(ChatColor.BLACK + "", "&0");
        returnValue = returnValue.replaceAll(ChatColor.DARK_BLUE + "", "&1");
        returnValue = returnValue.replaceAll(ChatColor.DARK_GREEN + "", "&2");
        returnValue = returnValue.replaceAll(ChatColor.DARK_AQUA + "", "&3");
        returnValue = returnValue.replaceAll(ChatColor.DARK_RED + "", "&4");
        returnValue = returnValue.replaceAll(ChatColor.DARK_PURPLE + "", "&5");
        returnValue = returnValue.replaceAll(ChatColor.GOLD + "", "&6");
        returnValue = returnValue.replaceAll(ChatColor.GRAY + "", "&7");
        returnValue = returnValue.replaceAll(ChatColor.DARK_GRAY + "", "&8");
        returnValue = returnValue.replaceAll(ChatColor.BLUE + "", "&9");
        returnValue = returnValue.replaceAll(ChatColor.GREEN + "", "&a");
        returnValue = returnValue.replaceAll(ChatColor.AQUA + "", "&b");
        returnValue = returnValue.replaceAll(ChatColor.RED + "", "&c");
        returnValue = returnValue.replaceAll(ChatColor.LIGHT_PURPLE + "", "&d");
        returnValue = returnValue.replaceAll(ChatColor.YELLOW + "", "&e");
        returnValue = returnValue.replaceAll(ChatColor.WHITE + "", "&f");

        returnValue = returnValue.replaceAll(ChatColor.MAGIC + "", "&k");
        returnValue = returnValue.replaceAll(ChatColor.BOLD + "", "&l");
        returnValue = returnValue.replaceAll(ChatColor.STRIKETHROUGH + "", "&m");
        returnValue = returnValue.replaceAll(ChatColor.UNDERLINE + "", "&n");
        returnValue = returnValue.replaceAll(ChatColor.ITALIC + "", "&f");
        returnValue = returnValue.replaceAll(ChatColor.RESET + "", "&r");

        return returnValue;
    }

    public static void groupCreate(CommandSender sender, String group) throws Exception
    {
        if (!isGroup(group))
        {
            p.configTeams.createSection(group);
            p.configTeams.save(p.fileTeamsConfig);
            sender.sendMessage(p.pluginTag + p.green + "Group " + p.gold + group + p.green + " created.");
        } else MethodsError.groupAlreadyExists(sender, group);
    }

    public static void groupDelete(CommandSender sender, String group) throws Exception
    {
        if (isGroup(group))
        {
            p.configTeams.set(group, null);
            p.configTeams.save(p.fileTeamsConfig);
            sender.sendMessage(p.pluginTag + p.green + "Deleted group " + p.gold + group);
        } else MethodsError.notAGroup(sender, group);
    }

    public static void groupRename(CommandSender sender, String group, String newGroup) throws Exception
    {
        if (!isGroup(newGroup))
        {
            if (isGroup(group))
            {
                p.configTeams.createSection(newGroup, p.configTeams.getConfigurationSection(group).getValues(true));
                p.configTeams.set(group, null);
                p.configTeams.save(p.fileTeamsConfig);
                for (Entry<String, String> entry : p.playerGroupChoice.entrySet())
                    if (entry.getValue().equals(group)) entry.setValue(newGroup);
                sender.sendMessage(p.pluginTag + p.green + "Renamed group " + p.gold + group + p.green + " to " + p.gold + newGroup);
            } else MethodsError.notAGroup(sender, group);
        } else MethodsError.groupAlreadyExists(sender, newGroup);
    }

    public static void groupSwitch(CommandSender sender, String group) throws Exception
    {
        if (isGroup(group))
        {
            p.playerGroupChoice.put(sender.getName(), group);
            sender.sendMessage(p.pluginTag + p.green + "Swtiched control to group " + p.gold + group);
        } else MethodsError.notAGroup(sender, group);
    }

    public static boolean hasPickedGroup(CommandSender sender) throws Exception
    {
        return p.playerGroupChoice.get(sender.getName()) != null;
    }

    public static boolean isDouble(String string) throws Exception
    {
        try
        {
            double temp = Double.parseDouble(string);
            if (temp >= 0) return true;
            else return false;
        } catch (Exception e)
        {
            return false;
        }
    }

    public static boolean isGroup(String toCheck) throws Exception
    {
        for (String group : p.configTeams.getKeys(false))
            if (group.equals(toCheck)) return true;
        return false;
    }

    public static String isManager(String team, String manager) throws Exception
    {
        for (String group : p.configTeams.getKeys(false))
            for (String temp : p.configTeams.getConfigurationSection(group).getKeys(false))
                if (temp.equals(team)) for (String theManager : p.configTeams.getStringList(group + "." + temp + ".managers"))
                    if (theManager.equals(manager)) return group + "." + team + ".members." + manager;
        return null;
    }

    public static List<String> isMember(String member) throws Exception
    {
        List<String> returnValue = new ArrayList<String>();
        for (String group : p.configTeams.getKeys(false))
            for (String team : p.configTeams.getConfigurationSection(group).getKeys(false))
                for (String theMember : p.configTeams.getStringList(group + "." + team + ".members"))
                    if (theMember.equals(member)) returnValue.add(group + "." + team + ".members." + theMember);
        return returnValue;
    }

    public static String isMember(String group, String member) throws Exception
    {
        for (String team : p.configTeams.getConfigurationSection(group).getKeys(false))
            for (String theMember : p.configTeams.getStringList(group + "." + team + ".members"))
                if (theMember.equals(member)) return group + "." + team + ".members." + theMember;
        return null;
    }

    public static String isTeam(String group, String toCheck) throws Exception
    {
        for (String team : p.configTeams.getConfigurationSection(group).getKeys(false))
            if (team.equals(toCheck)) return group + "." + team;
        return null;
    }

    public static void teamAddManager(CommandSender sender, String team, String manager) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            String theManager = isManager(team, manager);
            if (theManager == null)
            {
                List<String> managers = p.configTeams.getStringList(theTeam + ".managers");
                managers.add(manager);
                p.configTeams.set(theTeam + ".managers", managers);
                p.configTeams.save(p.fileTeamsConfig);
                sender.sendMessage(p.pluginTag + p.green + "Added new manager " + p.gold + manager + p.green + " to team " + p.gold + team);
            } else MethodsError.alreadyAManager(sender, manager);
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamCreate(CommandSender sender, String team) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam == null)
        {
            teamCreateSections(sender.getName(), p.playerGroupChoice.get(sender.getName()), team);
            sender.sendMessage(p.pluginTag + p.green + "Team " + p.gold + team + p.green + " created in group " + p.gold + p.playerGroupChoice.get(sender.getName()));
        } else MethodsError.teamAlreadyExists(sender, team);
    }

    public static void teamCreateSections(String leader, String group, String team) throws IOException
    {
        ConfigurationSection section = p.configTeams.createSection(group + "." + team);
        section.set("points", p.getConfig().getDouble("Default Team.points"));
        section.createSection("members");
        
        List<String> managers = new ArrayList<String>();
        for (String manager : p.getConfig().getStringList("Default Team.managers"))
            managers.add(manager);
        section.set("managers", managers);
        
        section.createSection("settings");
        
        String configuredLeader = p.getConfig().getString("Default Team.settings.leader");
        if (configuredLeader.equalsIgnoreCase("-creator-")) section.set("settings.leader", leader);
        else section.set("settings.leader", configuredLeader);
        
        section.set("settings.tag", p.getConfig().getString("Default Team.settings.tag"));
        section.set("settings.displayTag", p.getConfig().getBoolean("Default Team.settings.displayTag"));
        section.set("settings.noMemberPVP", p.getConfig().getBoolean("Default Team.settings.noMemberPVP"));
        p.configTeams.save(p.fileTeamsConfig);
    }

    public static void teamDelete(CommandSender sender, String team) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            p.configTeams.set(theTeam, null);
            p.configTeams.save(p.fileTeamsConfig);
            sender.sendMessage(p.pluginTag + p.green + "Team " + p.gold + team + p.green + " deleted.");
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamDisplay(CommandSender sender, String theTeam) throws Exception
    {
        String members = "";
        for (String member : p.configTeams.getStringList(theTeam + ".members"))
            members = members + member + ", ";
        if (members.contains(",")) members = members.substring(0, members.length() - 2);

        String managers = "";
        for (String manager : p.configTeams.getStringList(theTeam + ".managers"))
            managers = managers + manager + ", ";
        if (managers.contains(",")) managers = managers.substring(0, managers.length() - 2);

        sender.sendMessage(p.pluginTag + p.lineString);
        sender.sendMessage(p.pluginTag + "The team of " + convertCodesToColor(p.configTeams.getString(theTeam + ".settings.tag")) + " " + p.gold + theTeam.split("\\.")[1]);
        sender.sendMessage(p.pluginTag + p.lineString);
        sender.sendMessage(p.pluginTag + "Group: " + p.aqua + theTeam.split("\\.")[0]);
        sender.sendMessage(p.pluginTag + "Members: " + p.aqua + members);
        sender.sendMessage(p.pluginTag + "Managers: " + p.aqua + managers);
        sender.sendMessage(p.pluginTag + "Points: " + p.aqua + p.configTeams.getDouble(theTeam + ".points"));
        sender.sendMessage(p.pluginTag + "Settings:");
        sender.sendMessage(p.pluginTag + p.lineString);
        sender.sendMessage(p.pluginTag + "Tag: " + convertCodesToColor(p.configTeams.getString(theTeam + ".settings.tag")));
        sender.sendMessage(p.pluginTag + "Display Tag: " + p.aqua + Boolean.toString(p.configTeams.getBoolean(theTeam + ".settings.displayTag")));
        sender.sendMessage(p.pluginTag + "No member PVP: " + p.aqua + Boolean.toString(p.configTeams.getBoolean(theTeam + ".settings.noMemberPVP")));

    }

    public static void teamLeave(CommandSender sender) throws Exception
    {
        String theTeam = null;
        for (String temp : isMember(sender.getName()))
            if (temp.split("\\.")[0].equals(p.playerGroupChoice.get(sender.getName()))) theTeam = temp;
        if (theTeam != null)
        {
            String[] splitTeam = theTeam.split("\\.");
            teamMemberActualRemove(p.configTeams.getStringList(splitTeam[0] + "." + splitTeam[1] + ".members"), splitTeam[0] + "." + splitTeam[1], sender.getName());
            sender.sendMessage(p.pluginTag + p.green + "You have succesfully left team " + p.gold + theTeam.split("\\.")[1]);
        } else MethodsError.playerHasNoTeamInGroup(sender);
    }

    public static void teamMemberAccept(CommandSender sender) throws Exception
    {
        if (p.invites.get(sender.getName()) != null)
        {
            String theTeam = p.invites.get(sender.getName());
            teamMemberActualAdd(p.configTeams.getStringList(theTeam + ".members"), theTeam, sender.getName());
            p.invites.remove(sender.getName());
            sender.sendMessage(p.pluginTag + "You have succesfully joined team " + p.gold + theTeam.split("\\.")[1]);
        } else MethodsError.noInvitesAvailable(sender);
    }

    public static void teamMemberActualAdd(List<String> members, String theTeam, String member) throws Exception
    {
        members.add(member);
        p.configTeams.set(theTeam + ".members", members);
        p.configTeams.save(p.fileTeamsConfig);
    }

    public static void teamMemberActualRemove(List<String> members, String theTeam, String member) throws Exception
    {
        members.remove(member);
        p.configTeams.set(theTeam + ".members", members);
        p.configTeams.save(p.fileTeamsConfig);
    }

    public static void teamMemberAdd(CommandSender sender, String team, String member) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            String theMember = isMember(theTeam.split("\\.")[0], member);
            if (theMember == null)
            {
                List<String> members = p.configTeams.getStringList(theTeam + ".members");
                if (!members.contains(member))
                {
                    teamMemberActualAdd(members, theTeam, member);
                    sender.sendMessage(p.pluginTag + p.green + "Member " + p.gold + member + p.green + " added to team " + p.gold + team);
                } else MethodsError.memberAlreadyAdded(sender, member);
            } else MethodsError.playerAlreadyInTeam(sender, theMember.split("\\.")[1], member);
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamMemberInvite(CommandSender sender, String team, String toInvite) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            List<String> members = p.configTeams.getStringList(theTeam + ".members");
            Player playerToInvite = Bukkit.getPlayer(toInvite);
            if (playerToInvite != null)
            {
                if (!members.contains(playerToInvite.getName()))
                {
                    p.invites.put(playerToInvite.getName(), theTeam);
                    sender.sendMessage(p.pluginTag + p.green + "You have succesfully invited " + p.gold + toInvite + p.green + " to team " + p.gold + team);
                    playerToInvite.sendMessage(p.pluginTag + p.gold + sender.getName() + p.green + " has invited you to team " + p.gold + team + p.green + "! Accept with " + p.gold + "/teams accept.");
                } else MethodsError.memberAlreadyAdded(sender, toInvite);
            } else MethodsError.playerNotFound(sender, toInvite);
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamMemberRemove(CommandSender sender, String team, String member) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            List<String> members = p.configTeams.getStringList(theTeam + ".members");
            if (members.contains(member))
            {
                teamMemberActualRemove(members, theTeam, member);
                sender.sendMessage(p.pluginTag + p.green + "Member " + p.gold + member + p.green + " removed.");
            } else MethodsError.memberDoesntExist(sender, member);
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamPointsAdjust(CommandSender sender, String team, String operation, String value) throws Exception
    {
        if (isDouble(value))
        {
            double theValue = Double.parseDouble(value);
            if (theValue <= Integer.MAX_VALUE)
            {
                String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
                if (theTeam != null)
                {
                    double oldPoints = p.configTeams.getDouble(theTeam + ".points");
                    if (operation.equalsIgnoreCase("="))
                    {
                        p.configTeams.set(theTeam + ".points", theValue);
                        p.configTeams.save(p.fileTeamsConfig);
                        sender.sendMessage(p.pluginTag + p.green + "Set " + p.gold + team + p.green + "'s points to " + p.gold + theValue);
                        return;
                    }

                    if (operation.equalsIgnoreCase("-"))
                    {
                        p.configTeams.set(theTeam + ".points", oldPoints - theValue);
                        p.configTeams.save(p.fileTeamsConfig);
                        sender.sendMessage(p.pluginTag + p.green + "Subtracted " + p.gold + theValue + p.green + " points from team " + p.gold + team);
                        return;
                    }

                    if (operation.equalsIgnoreCase("+"))
                    {
                        p.configTeams.set(theTeam + ".points", oldPoints + theValue);
                        p.configTeams.save(p.fileTeamsConfig);
                        sender.sendMessage(p.pluginTag + p.green + "Added " + p.gold + theValue + p.green + " points to team " + p.gold + team);
                        return;
                    }
                    sender.sendMessage(p.pluginTag + p.gold + operation + p.green + " is not a valid operation! Please use '+' '-' or '=' only.");
                } else MethodsError.notATeam(sender, team);
            } else MethodsError.notANumber(sender, value);
        } else MethodsError.notANumber(sender, value);
    }

    public static void teamRemoveManager(CommandSender sender, String team, String manager) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            String theManager = isManager(team, manager);
            if (theManager != null)
            {
                List<String> managers = p.configTeams.getStringList(theTeam + ".managers");
                managers.remove(manager);
                p.configTeams.set(theTeam + ".managers", managers);
                p.configTeams.save(p.fileTeamsConfig);
                sender.sendMessage(p.pluginTag + p.green + "Removed manager " + p.gold + manager + p.green + " from team " + p.gold + team);
            } else MethodsError.notAManagerTP(sender, manager);
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamRename(CommandSender sender, String team, String newTeam) throws Exception
    {
        String group = p.playerGroupChoice.get(sender.getName());
        if (isTeam(group, newTeam) == null)
        {
            String theTeam = isTeam(group, team);
            if (theTeam != null)
            {
                p.configTeams.getConfigurationSection(group).createSection(newTeam, p.configTeams.getConfigurationSection(theTeam).getValues(false));
                p.configTeams.set(theTeam, null);
                p.configTeams.save(p.fileTeamsConfig);
                sender.sendMessage(p.pluginTag + p.green + "Renamed team " + p.gold + team + p.green + " to " + p.gold + newTeam);
            } else MethodsError.notATeam(sender, team);
        } else MethodsError.teamAlreadyExists(sender, newTeam);
    }

    public static void teamsDisplay(CommandSender sender) throws Exception
    {
        sender.sendMessage(p.pluginTag + p.gold + "List of groups/teams:");
        Set<String> groups = p.configTeams.getKeys(false);
        if (groups.size() > 0)
        {
            for (String group : groups)
            {
                Set<String> teams = p.configTeams.getConfigurationSection(group).getKeys(false);
                sender.sendMessage(p.pluginTag + p.gold + p.lineString);
                sender.sendMessage(p.pluginTag + group + " - " + p.aqua + teams.size() + " teams.");
                sender.sendMessage(p.pluginTag + p.gold + p.lineString);
                if (teams.size() > 0)
                {
                    for (String team : teams)
                        sender.sendMessage(p.pluginTag + convertCodesToColor(p.configTeams.getString(group + "." + team + ".settings.tag")) + " " + team + p.aqua + " - " + p.configTeams.getString(group + "." + team + ".points") + " points.");

                } else MethodsError.noTeamsAvailable(sender);
            }
        } else MethodsError.noGroupsAvailable(sender);
    }

    public static void teamSetBoolean(CommandSender sender, String team, String setting, String state) throws Exception
    {
        boolean theState;
        if (state.equals("true") || state.equals("yes")) theState = true;
        else if (state.equals("false") || state.equals("no")) theState = false;
        else
        {
            MethodsError.notABooleanValue(sender, state);
            return;
        }

        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            if (setting.equalsIgnoreCase("displayTag")) p.configTeams.set(theTeam + ".settings.displayTag", theState);
            else if (setting.equalsIgnoreCase("noMemberPVP")) p.configTeams.set(theTeam + ".settings.noMemberPVP", theState);
            else { MethodsError.notASetting(sender, setting); return; }

            p.configTeams.save(p.fileTeamsConfig);
            sender.sendMessage(p.pluginTag + p.green + "Setting " + p.gold + setting + p.green + " of team " + p.gold + team + p.green + " set to " + p.gold + state);
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamSetTag(CommandSender sender, String team, String tag) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            p.configTeams.set(theTeam + ".settings.tag", tag);
            p.configTeams.save(p.fileTeamsConfig);
            sender.sendMessage(p.pluginTag + p.green + "Tag " + convertCodesToColor(tag) + p.green + " assigned to team " + p.gold + team);
        } else MethodsError.notATeam(sender, team);
    }

    public static void teamsReload(CommandSender sender) throws Exception
    {
        p.getConfig().load(p.filePluginConfig);
        p.configTeams = YamlConfiguration.loadConfiguration(p.fileTeamsConfig);
        sender.sendMessage(p.pluginTag + p.green + "All groups/teams/config reloaded!");
    }

    public static void teamsReset(CommandSender sender, String group) throws Exception
    {
        if (group == null)
        {
            for (String theGroup : p.configTeams.getKeys(false))
                for (String team : p.configTeams.getConfigurationSection(theGroup).getKeys(false))
                    teamCreateSections(p.configTeams.getString(theGroup + "." + team + ".settings.leader"), theGroup, team);
            sender.sendMessage(p.pluginTag + "All teams, members and points reset!");
        } else if (isGroup(group))
        {
            for (String team : p.configTeams.getConfigurationSection(group).getKeys(false))
                teamCreateSections(p.configTeams.getString(group + "." + team + ".settings.leader"), group, team);
            sender.sendMessage(p.pluginTag + "All teams, members and points in group " + p.aqua + group + p.gold + " reset!");
        } else MethodsError.notAGroup(sender, group);
       
    }

    public static void teamTpAll(Player sender) throws Exception
    {
        String member = isMember(p.playerGroupChoice.get(sender.getName()), sender.getName());
        if (member != null)
        {
            int number = 0;
            String[] splitMember = member.split("\\.");
            List<String> teleported = new ArrayList<String>();
            for (String teamMember : p.configTeams.getStringList(splitMember[0] + "." + splitMember[1] + ".members"))
            {
                Player player = Bukkit.getPlayerExact(teamMember);
                if (player != null && !teleported.contains(player.getName()) && player != sender)
                {
                    teleported.add(player.getName());
                    player.teleport(sender.getLocation());
                    number++;
                }
            }
            sender.sendMessage(p.pluginTag + p.green + "Teleported " + p.gold + number + p.green + " online team members to you!");
        } else MethodsError.playerHasNoTeam(sender);
    }

    public static void teamView(CommandSender sender, String team) throws Exception
    {
        if (team != null)
        {
            String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
            if (theTeam != null) teamDisplay(sender, theTeam);
            else MethodsError.notATeam(sender, team);

        } else
        {
            List<String> members = isMember(sender.getName());
            if (members.size() > 0)
            {
                for (String member : members)
                {
                    String[] splitMember = member.split("\\.");
                    teamDisplay(sender, splitMember[0] + "." + splitMember[1]);
                }
            } else MethodsError.playerHasNoTeam(sender);
        }
    }
 
    public static void teamSetLeader(CommandSender sender, String team, String leader) throws Exception
    {
        String theTeam = isTeam(p.playerGroupChoice.get(sender.getName()), team);
        if (theTeam != null)
        {
            p.configTeams.set(theTeam + ".settings.leader", leader);
            p.configTeams.save(p.fileTeamsConfig);
            sender.sendMessage(p.pluginTag + p.green + "Set " + p.gold + leader + p.green + " as the leader of team " + p.gold + team);
        } else MethodsError.notATeam(sender, team);
    }

    public static boolean isLeader(CommandSender sender, String team)
    {
        for (String group : p.configTeams.getKeys(false))
            for (String temp : p.configTeams.getConfigurationSection(group).getKeys(false))
                if (temp.equals(team) && p.configTeams.getString(group + "." + temp + ".settings.leader").equals(sender.getName())) return true;
        return false;
    }

    public static void chatSwitch(CommandSender sender) throws Exception
    {
        if (p.playerChatChoice.get(sender.getName()) != null)
        {
            p.playerChatChoice.remove(sender.getName());
            sender.sendMessage(p.pluginTag + p.green + "Turned off team chat. Start team chat again with " + p.gold + "/tc");
        } else
        {
            String member = isMember(p.playerGroupChoice.get(sender.getName()), sender.getName());
            if (member != null)
            {
                String[] splitMember = member.split("\\.");
                String theTeam = splitMember[0] + "." + splitMember[1];
                p.playerChatChoice.put(sender.getName(), theTeam);
                sender.sendMessage(p.pluginTag + p.green + "Now talking in team chat for team " + p.gold + splitMember[1]);
            } else MethodsError.playerHasNoTeam(sender);
        }
    }
    
    public static void settingsDisplay(CommandSender sender) throws Exception
    {
        sender.sendMessage(p.pluginTag + "Available settings:");
        sender.sendMessage(p.pluginTag + p.lineString);
        sender.sendMessage(p.pluginTag + "tag: " + p.aqua + "Tag to be displayed in front of player messages in this team.");
        sender.sendMessage(p.pluginTag + "displayTag: " + p.aqua + "True/False value which decides if the tag should be displayed or not.");
        sender.sendMessage(p.pluginTag + "noMemberPVP: " + p.aqua + "True/False value which decides if PVP between team members is allowed or not.");
        sender.sendMessage(p.pluginTag + p.lineString);
        sender.sendMessage(p.pluginTag + "Set settings with " + p.aqua + "/teams set [setting] [team] [value]");
        sender.sendMessage(p.pluginTag + "Example: " + p.aqua + "/teams set tag AwesomeTeam &b[AS]");
    }

    public static void groupDistribute(CommandSender sender, String[] args) throws Exception
    {
        String group = p.playerGroupChoice.get(sender.getName());
        HashMap<Integer, String> teams = getTeamsInGroup(group);
        HashMap<String, List<String>> members = new HashMap<String, List<String>>();
        HashMap<String, List<String>> added  = new HashMap<String, List<String>>();
        List<String> failed = new ArrayList<String>();
        
        for (Entry<Integer, String> team : teams.entrySet())
            added.put(team.getValue(), new ArrayList<String>());
        
        if (teams.size() > 0)
        {
            List<String> toDistribute = new ArrayList<String>();
            int i = 0;
            for (String player : args)
            {
                if (i != 0)
                    toDistribute.add(player);
                i++;
            }
            Random random = new Random();
            
            for (String player : toDistribute)
            {
                for (String team : teams.values())
                    members.put(team, getMembers(team));
                
                if (isMember(group, player) == null)
                { 
                    String theTeam = teams.get(0);
                    if (teams.size() > 1)
                    {
                        List<String> biggestTeam = new ArrayList<String>();
                        int biggestAdded = members.get(teams.get(0)).size();
                        for (Entry<String, List<String>> temp2 : members.entrySet())
                        {
                            if (temp2.getValue().size() > biggestAdded)
                            {
                                biggestAdded = temp2.getValue().size();
                                biggestTeam.clear();
                                biggestTeam.add(temp2.getKey());
                            } else if (temp2.getValue().size() == biggestAdded)
                                biggestTeam.add(temp2.getKey());
                        }
                        
                        if (biggestTeam.size() == teams.size()) theTeam = teams.get(random.nextInt(teams.size()));
                        else {
                            
                            theTeam = teams.get(random.nextInt(teams.size()));
                            while (biggestTeam.contains(theTeam)) theTeam = teams.get(random.nextInt(teams.size()));
                        }
                    }
                    teamMemberActualAdd(members.get(theTeam), theTeam, player);
                    added.get(theTeam).add(player);
                } else failed.add(player);
            }
            
            if (added.entrySet().size() > 0)
            {
                sender.sendMessage(p.pluginTag + p.green + "Changes to teams:");
                sender.sendMessage(p.pluginTag + p.lineString);
                for (Entry<String, List<String>> addedEntry : added.entrySet())
                {
                    if (addedEntry.getValue().size() > 0)
                    {
                        sender.sendMessage(p.pluginTag + p.aqua + "Team " + addedEntry.getKey().split("\\.")[1] + ":");
                        for (String addedPerson : addedEntry.getValue())
                            sender.sendMessage(p.pluginTag + p.gold + "    " + addedPerson + p.green + " added.");
                    }
                }
            }
            if (failed.size() > 0)
            {
                sender.sendMessage(p.pluginTag + p.green + "Errors:");
                sender.sendMessage(p.pluginTag + p.lineString);
                for (String failure : failed)
                    sender.sendMessage(p.pluginTag + failure + p.red + " already has a team in this group!");
            }

        } else MethodsError.noTeamsInGroup(sender, group);
    }
    
    public static List<String> getMembers(String theTeam)
    {
        List<String> returnValue = new ArrayList<String>();
        for (String member : p.configTeams.getStringList(theTeam + ".members"))
            returnValue.add(member);
        return returnValue;
    }

    public static HashMap<Integer, String> getTeamsInGroup(String group) throws Exception
    {
        HashMap<Integer, String> returnValue = new HashMap<Integer, String>();
        int i = 0;
        for (String team : p.configTeams.getConfigurationSection(group).getKeys(false))
        {
            returnValue.put(i, group + "." + team);
            i++;
        }
        return returnValue;
    }
}
