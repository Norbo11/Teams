package com.github.norbo11.teams;

import org.bukkit.command.CommandSender;

public class MethodsError
{

    static Teams p = Teams.p;

    public static void alreadyAManager(CommandSender sender, String manager)
    {
        sender.sendMessage(p.pluginTag + p.gold + manager + p.red + " is already a manager in this team!");
    }

    public static void displayHelp(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + "/tc");
        sender.sendMessage(p.pluginTag + "/teams help " + p.aqua + "(command)");
        sender.sendMessage(p.pluginTag + "/teams list");
        sender.sendMessage(p.pluginTag + "/teams view " + p.aqua + "(team)");
        sender.sendMessage(p.pluginTag + "----/teams group----");
        sender.sendMessage(p.pluginTag + "/teams group switch " + p.aqua + "[group]");
        sender.sendMessage(p.pluginTag + "/teams group new " + p.aqua + "[group]");
        sender.sendMessage(p.pluginTag + "/teams group del " + p.aqua + "[group]");
        sender.sendMessage(p.pluginTag + "/teams group rename " + p.aqua + "[group] [name]");
        sender.sendMessage(p.pluginTag + p.lineString);
        sender.sendMessage(p.pluginTag + "/teams new " + p.aqua + "[name]");
        sender.sendMessage(p.pluginTag + "/teams del " + p.aqua + "[name]");
        sender.sendMessage(p.pluginTag + "/teams rename " + p.aqua + "[team] [name]");
        sender.sendMessage(p.pluginTag + "/teams add " + p.aqua + "[team] [member]");
        sender.sendMessage(p.pluginTag + "/teams remove " + p.aqua + "[team] [member]");
        sender.sendMessage(p.pluginTag + "/teams distribute " + p.aqua + "Player1 Player2 Player3...");
        sender.sendMessage(p.pluginTag + "/teams points " + p.aqua + "[team] [operation] [value]");
        sender.sendMessage(p.pluginTag + "/teams addmanager " + p.aqua + "[team] [name]");
        sender.sendMessage(p.pluginTag + "/teams removemanager " + p.aqua + "[team] [name]");
        sender.sendMessage(p.pluginTag + "/teams tpall");
        sender.sendMessage(p.pluginTag + "/teams set " + p.aqua + "[setting] [team] [value]");
        sender.sendMessage(p.pluginTag + "/teams listsettings");
        sender.sendMessage(p.pluginTag + "/teams invite " + p.aqua + "[team] [name]");
        sender.sendMessage(p.pluginTag + "/teams leave");
        sender.sendMessage(p.pluginTag + "/teams reset " + p.aqua + "(group)");
        sender.sendMessage(p.pluginTag + "/teams reload");
        sender.sendMessage(p.pluginTag + p.aqua + "[required] (optional)");
        sender.sendMessage(p.pluginTag + p.aqua + "Teams " + p.green + "v" + p.version + p.aqua + " by " + p.gold + "Norbo11");
    }

    public static void groupAlreadyExists(CommandSender sender, String group)
    {
        sender.sendMessage(p.pluginTag + p.red + "Group " + p.gold + group + p.red + " already exists!");
    }

    public static void memberAlreadyAdded(CommandSender sender, String member)
    {
        sender.sendMessage(p.pluginTag + p.red + "Member " + p.gold + member + p.red + " already exists!");
    }

    public static void memberDoesntExist(CommandSender sender, String member)
    {
        sender.sendMessage(p.pluginTag + p.red + "Member " + p.gold + member + p.red + " does not exist!");
    }

    public static void noGroupsAvailable(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "No groups available!");
    }

    public static void noInvitesAvailable(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "You have not been invited to any team!");
    }

    public static void noPermission(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "You do not have permission to do that!");
    }

    public static void notABooleanValue(CommandSender sender, String state)
    {
        sender.sendMessage(p.pluginTag + p.gold + state + p.red + " is not a valid true/false state! Please only use yes|true or no|false values.");
    }

    public static void notAGroup(CommandSender sender, String group)
    {
        sender.sendMessage(p.pluginTag + p.red + "Group " + p.gold + group + p.red + " doesnt exist!");
    }

    public static void notAManagerFP(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "You are not a manager of this team, or the team doesn't exist!");
    }

    public static void notAManagerTP(CommandSender sender, String manager)
    {
        sender.sendMessage(p.pluginTag + p.gold + manager + p.red + " is not a manager of this team!");
    }

    public static void notANumber(CommandSender sender, String value)
    {
        sender.sendMessage(p.pluginTag + p.gold + value + p.red + " is not a valid number!");
    }

    public static void notAPlayer(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "Sorry, that command is only available for players!");
    }

    public static void notATeam(CommandSender sender, String team)
    {
        sender.sendMessage(p.pluginTag + p.red + "Team " + p.gold + team + p.red + " doesn't exist!");
    }

    public static void noTeamsAvailable(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "No teams available!");
    }

    public static void notPickedGroup(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "You are not controlling a group! Control a group with " + p.gold + "/teams group [group name]");
    }

    public static void playerAlreadyInTeam(CommandSender sender, String team, String member)
    {
        sender.sendMessage(p.pluginTag + p.gold + member + p.red + " is already a member of " + p.gold + team + p.red + " in this group!");
    }

    public static void playerHasNoTeam(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "You dont belong to a team!");
    }

    public static void playerHasNoTeamInGroup(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "You do not belong to a team in group " + p.gold + p.playerGroupChoice.get(sender.getName()));
    }

    public static void playerNotFound(CommandSender sender, String toInvite)
    {
        sender.sendMessage(p.pluginTag + p.red + "Player not found: " + p.gold + toInvite);
    }

    public static void teamAlreadyExists(CommandSender sender, String team)
    {
        sender.sendMessage(p.pluginTag + p.red + "Team " + p.gold + team + p.red + " already exists!");
    }

    public static void usage(CommandSender sender, String command)
    {

        if (command.equalsIgnoreCase("help")) sender.sendMessage(p.pluginTag + "Usage: /teams help " + p.aqua + "- Displays this help text");
        if (command.equalsIgnoreCase("list")) sender.sendMessage(p.pluginTag + "Usage: /teams list " + p.aqua + "- Displays created teams.");
        if (command.equalsIgnoreCase("new")) sender.sendMessage(p.pluginTag + "Usage: /teams new [group] [name] " + p.aqua + "- Creates a team.");
        if (command.equalsIgnoreCase("del")) sender.sendMessage(p.pluginTag + "Usage: /teams del [name] " + p.aqua + "- Deletes a team.");
        if (command.equalsIgnoreCase("view")) sender.sendMessage(p.pluginTag + "Usage: /teams view [team] " + p.aqua + "- Views a team's details.");
        if (command.equalsIgnoreCase("points")) sender.sendMessage(p.pluginTag + "Usage: /teams points [team] [operation] [value] " + p.aqua + "- Adjusts a team's points. Operations: '+' '-' '='");
        if (command.equalsIgnoreCase("reload")) sender.sendMessage(p.pluginTag + "Usage: /teams reload " + p.aqua + "- Reloads all teams.");
        if (command.equalsIgnoreCase("add")) sender.sendMessage(p.pluginTag + "Usage: /teams add [team] [member] " + p.aqua + "- Adds a member to a team.");
        if (command.equalsIgnoreCase("remove")) sender.sendMessage(p.pluginTag + "Usage: /teams remove [team] [member] " + p.aqua + "- Removes a member from a team.");
        if (command.equalsIgnoreCase("reset")) sender.sendMessage(p.pluginTag + "Usage: /teams reset (group)" + p.aqua + "- Resets all teams (members + points + settings), or teams in the specified group.");
        if (command.equalsIgnoreCase("set")) sender.sendMessage(p.pluginTag + "Usage: /teams set tag | displaytag | noMemberPVP " + p.aqua + "- Controls a team's settings.");
        if (command.equalsIgnoreCase("settag")) sender.sendMessage(p.pluginTag + "Usage: /teams set tag [team] [tag] " + p.aqua + "- Adjusts a team's tag.");
        if (command.equalsIgnoreCase("setdisplaytag")) sender.sendMessage(p.pluginTag + "Usage: /teams set displaytag [team] [true|false] " + p.aqua + "- Enabes/disables tag displaying for a team.");
        if (command.equalsIgnoreCase("setleader")) sender.sendMessage(p.pluginTag + "Usage: /teams set leader [team] [name] " + p.aqua + "- Assigns a new leader to a team.");
        if (command.equalsIgnoreCase("setnomemberpvp")) sender.sendMessage(p.pluginTag + "Usage: /teams set displaytag [team] [true|false] " + p.aqua + "- Enables/disables non-pvp for members in a team.");
        if (command.equalsIgnoreCase("team")) sender.sendMessage(p.pluginTag + "/team " + p.aqua + "- Displays your team, if you belong to one.");
        if (command.equalsIgnoreCase("group")) sender.sendMessage(p.pluginTag + "Usage: /teams group new | del | rename | switch [group] " + p.aqua + "- Controls groups.");
        if (command.equalsIgnoreCase("groupswitch")) sender.sendMessage(p.pluginTag + "Usage: /teams group switch [group] " + p.aqua + "- Switches control to another group.");
        if (command.equalsIgnoreCase("groupnew")) sender.sendMessage(p.pluginTag + "Usage: /teams group new [group] " + p.aqua + "- Creates a new group.");
        if (command.equalsIgnoreCase("groupdel")) sender.sendMessage(p.pluginTag + "Usage: /teams group del [group] " + p.aqua + "- Deletes a group and all of its teams.");
        if (command.equalsIgnoreCase("grouprename")) sender.sendMessage(p.pluginTag + "Usage: /teams group rename [group] " + p.aqua + "- Renames a group.");
        if (command.equalsIgnoreCase("addmanager")) sender.sendMessage(p.pluginTag + "Usage: /teams addmanager [team] [name] " + p.aqua + "- Adds a manager to a team.");
        if (command.equalsIgnoreCase("removemanager")) sender.sendMessage(p.pluginTag + "Usage: /teams removemanager [team] [name] " + p.aqua + "- Removes a manager from a team.");
        if (command.equalsIgnoreCase("accept")) sender.sendMessage(p.pluginTag + "Usage: /teams accept " + p.aqua + "- Accepts an invite to a team.");
        if (command.equalsIgnoreCase("invite")) sender.sendMessage(p.pluginTag + "Usage: /teams invite [team] [name] " + p.aqua + "- Invites a player to a team (you must be manager in the team).");
        if (command.equalsIgnoreCase("leave")) sender.sendMessage(p.pluginTag + "Usage: /teams leave " + p.aqua + "- Leaves the team you belong to inside your current controlled group.");
        if (command.equalsIgnoreCase("rename")) sender.sendMessage(p.pluginTag + "Usage: /teams rename " + p.aqua + "- Renames a team.");
        if (command.equalsIgnoreCase("tpall")) sender.sendMessage(p.pluginTag + "Usage: /teams tpall " + p.aqua + "- Teleports all team members to you.");
        if (command.equalsIgnoreCase("tc")) sender.sendMessage(p.pluginTag + "Usage: /tc " + p.aqua + "- Toggles team chat (with the team you are member in, inside the current controlled group).");
        if (command.equalsIgnoreCase("listsettings")) sender.sendMessage(p.pluginTag + "Usage: /teams listsettings " + p.aqua + "- List all available settings that you can set for each team.");
        if (command.equalsIgnoreCase("distribute")) sender.sendMessage(p.pluginTag + "Usage: /teams distribute Player1 Player2 Player3... " + p.aqua + "- Randomly distributes all specified players into all teams in the current controlled group.");
    }

    public static void notASetting(CommandSender sender, String setting)
    {
        sender.sendMessage(p.pluginTag + p.gold + setting + p.red + " is not a valid setting! Valid settings: " + p.gold + "noMemberPVP, displayTag, tag");
    }

    public static void playerDoesntBelongToTeam(CommandSender sender, String team)
    {
        sender.sendMessage(p.pluginTag + p.red + "You are not a member of team " + p.gold + team);
    }

    public static void noTeamsInGroup(CommandSender sender, String group)
    {
        sender.sendMessage(p.pluginTag + p.red + "There are currently 0 teams in group " + p.gold + group);
    }

    public static void memberAlreadyHasTeam(CommandSender sender, String player)
    {
        sender.sendMessage(p.pluginTag + p.gold + player + p.red + " already has a team in this group!");
    }
}
