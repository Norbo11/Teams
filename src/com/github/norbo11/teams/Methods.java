package com.github.norbo11.teams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Methods
{
    public Methods(Teams p)
    {
        this.p = p;
    }

    Teams p;

    public void addMember(CommandSender sender, String theTeam, String theMember)
    {
        Team team = isATeam(theTeam);
        if (team != null)
        {
            Member member = isAMember(theMember);
            if (member == null)
            {
                team.members.add(new Member(theMember, team, p));
                team.save();
                sender.sendMessage(p.pluginTag + p.green + "Member " + p.gold + theMember + p.green + " added to team " + team.name);
            } else p.methodsError.memberAlreadyAdded(sender);
        } else p.methodsError.notATeam(sender);
    }

    public void adjustPoints(CommandSender sender, String theTeam, String operation, String theValue)
    {
        double value = isDouble(theValue);
        if (value != -1 && value < Double.MAX_VALUE)
        {
            Team team = isATeam(theTeam);
            if (team != null)
            {
                if (operation.equalsIgnoreCase("+"))
                {
                    team.points = team.points + value;
                    team.save();
                    sender.sendMessage(p.pluginTag + p.green + "Added " + p.gold + theValue + p.green + " points to team " + p.gold + theTeam);
                    return;
                }

                if (operation.equalsIgnoreCase("-"))
                {
                    team.points = team.points - value;
                    team.save();
                    sender.sendMessage(p.pluginTag + p.green + "Subtracted " + p.gold + theValue + p.green + " points from team " + p.gold + theTeam);
                    return;
                }

                if (operation.equalsIgnoreCase("="))
                {
                    team.points = value;
                    team.save();
                    sender.sendMessage(p.pluginTag + p.green + "Set " + p.gold + theTeam + p.green + "'s points to " + p.gold + theValue);
                    return;
                }
                sender.sendMessage(p.pluginTag + p.gold + operation + p.green + " is not a valid operation! Please use '+' '-' or '=' only.");
            } else p.methodsError.notATeam(sender);
        } else p.methodsError.notANumber(sender, theValue);
    }

    public void createTeam(CommandSender sender, String team)
    {
        if (!new File(p.pluginDir, team + ".txt").exists())
        {
            p.teams.add(new Team(team, true, p));
            sender.sendMessage(p.pluginTag + p.green + "Team " + p.gold + team + p.green + " created.");
        } else p.methodsError.teamAlreadyExists(sender);
    }

    public void deleteTeam(CommandSender sender, String toDelete)
    {
        Team team = isATeam(toDelete);
        if (team != null)
        {
            File file = new File(p.pluginDir + "\\" + team.filename);
            file.delete();
            p.teams.remove(team);
            sender.sendMessage(p.pluginTag + p.green + "Team " + p.gold + toDelete + p.green + " deleted.");
        } else p.methodsError.notATeam(sender);
    }

    public void displayTeams(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.gold + "List of teams:");
        if (p.teams.size() > 0)
        {
            for (Team team : p.teams)
                sender.sendMessage(p.pluginTag + team.tag + " " + p.gold + team.name + " - " + p.aqua + team.points + " points.");
        } else sender.sendMessage(p.pluginTag + p.red + "No teams available.");
    }

    public void getTeams()
    {
        p.teams.clear();
        List<File> teams = new ArrayList<File>();

        for (File file : p.getPluginDir().listFiles())
            if (!file.getName().equals("config.yml")) teams.add(file);

        for (File file : teams)
            p.teams.add(new Team(file.getName().replace(".txt", ""), false, p));
    }

    public Member isAMember(String member)
    {
        for (Team team : p.teams)
        {
            for (Member temp : team.members)
                if (temp.name.equalsIgnoreCase(member)) return temp;
        }
        return null;
    }

    public Team isATeam(String toCheck)
    {
        for (Team team : p.teams)
            if (team.name.equalsIgnoreCase(toCheck)) return team;
        return null;
    }

    public double isDouble(String string)
    {
        try
        {
            double temp = Double.parseDouble(string);
            if (temp >= 0) return temp;
            else return -1;
        } catch (Exception e)
        {
            return -1;
        }
    }

    public void removeMember(CommandSender sender, String theTeam, String theMember)
    {
        Team team = isATeam(theTeam);
        if (team != null)
        {
            Member member = isAMember(theMember);
            if (member != null)
            {
                team.members.remove(member);
                team.save();
                sender.sendMessage(p.pluginTag + p.green + "Member " + p.gold + member.name + p.green + " removed.");
            } else p.methodsError.memberDoesntExist(sender);
        } else p.methodsError.notATeam(sender);
    }

    public void saveTeams()
    {
        for (Team team : p.teams)
            team.save();
    }

    public void viewTeam(CommandSender sender, String theTeam)
    {
        Team team = isATeam(theTeam);
        if (team != null)
        {
            sender.sendMessage(p.pluginTag + "The team of " + team.tag + " " + p.gold + team.name);
            sender.sendMessage(p.pluginTag + p.lineString);
            String members = "";

            for (Member member : team.members)
                members = members + member.name + ", ";

            if (team.members.size() >= 1) members = members.substring(0, members.length() - 2);

            sender.sendMessage(p.pluginTag + "Members: " + p.aqua + members);
            sender.sendMessage(p.pluginTag + "Points: " + p.aqua + team.points);
        } else p.methodsError.notATeam(sender);
    }

    public void resetAll(CommandSender sender)
    {
        for (Team team : p.teams)
        {
            getTeams();
            team.points = 0;
            team.members.clear();
            team.tag = convertCodesToColor("&b[" + team.name + "]");
            team.save();
            getTeams();
        }
        sender.sendMessage(p.pluginTag + "All teams, members and points reset!");
    }

    public void setTag(CommandSender sender, String theTeam, String tag)
    {
        Team team = isATeam(theTeam);
        if (team != null)
        {
            team.tag = convertCodesToColor(tag);
            team.save();
            sender.sendMessage(p.pluginTag + "Tag " + convertCodesToColor(tag) + p.gold + " assigned to team " + p.aqua + theTeam);
        } else p.methodsError.notATeam(sender);
    }
    
    public String convertCodesToColor(String string)
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
    
    public String convertCodesToColorToCodes(String string)
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
}
