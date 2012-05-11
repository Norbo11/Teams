package com.github.norbo11.teams;

import org.bukkit.command.CommandSender;

public class MethodsError
{
    public MethodsError(Teams p)
    {
        this.p = p;
    }

    Teams p;

    public void displayHelp(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + "/team help " + p.aqua + "- Displays this help text");
        sender.sendMessage(p.pluginTag + "/team list " + p.aqua + "- Displays created teams.");
        sender.sendMessage(p.pluginTag + "/team new [name] " + p.aqua + "- Creates a team.");
        sender.sendMessage(p.pluginTag + "/team del [name] " + p.aqua + "- Deletes a team.");
        sender.sendMessage(p.pluginTag + "/team view [team] " + p.aqua + "- Views a team's details.");
        sender.sendMessage(p.pluginTag + "/team add [team] [member] " + p.aqua + "- Adds a member.");
        sender.sendMessage(p.pluginTag + "/team remove [team] [member] " + p.aqua + "- Removes a member.");
        sender.sendMessage(p.pluginTag + "/team points [team] [operation] [value] " + p.aqua + "- Adjusts a team's points.");
        sender.sendMessage(p.pluginTag + "/team tag [team] [tag] " + p.aqua + "- Adjusts a team's tag.");
        sender.sendMessage(p.pluginTag + "/team resetall " + p.aqua + "- Resets all teams (members + points).");
        sender.sendMessage(p.pluginTag + "/team reload " + p.aqua + "- Reloads all teams.");
        sender.sendMessage(p.pluginTag + p.aqua + "Teams v" + p.version + " by " + p.gold + "Norbo11");
    }

    public void memberAlreadyAdded(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "That member already exists!");
    }

    public void memberDoesntExist(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "That member does not exist!");
    }

    public void noPermission(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "You do not have permission to do that!");
    }

    public void notANumber(CommandSender sender, String value)
    {
        sender.sendMessage(p.pluginTag + p.gold + value + p.red + " is not a valid number!");
    }

    public void notATeam(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "That team doesn't exist!");
    }

    public void teamAlreadyExists(CommandSender sender)
    {
        sender.sendMessage(p.pluginTag + p.red + "That team already exists!");
    }

    public void usage(CommandSender sender, String command)
    {
        if (command.equals("help")) sender.sendMessage(p.pluginTag + "/team help" + p.aqua + "- Displays this help text");
        if (command.equals("list")) sender.sendMessage(p.pluginTag + "/team list " + p.aqua + "- Displays created teams.");
        if (command.equals("new")) sender.sendMessage(p.pluginTag + "/team new [name] " + p.aqua + "- Creates a team.");
        if (command.equals("del")) sender.sendMessage(p.pluginTag + "/team del [name] " + p.aqua + "- Deletes a team.");
        if (command.equals("view")) sender.sendMessage(p.pluginTag + "/team view [team] " + p.aqua + "- Views a team's details.");
        if (command.equals("points")) sender.sendMessage(p.pluginTag + "/team points [team] [operation] [value] " + p.aqua + "- Adjusts a team's points. Operations: '+' '-' '='");
        if (command.equals("reload")) sender.sendMessage(p.pluginTag + "/team reload " + p.aqua + "- Reloads all teams.");
        if (command.equals("add")) sender.sendMessage(p.pluginTag + "/team add [team] [member] " + p.aqua + "- Reloads all teams.");
        if (command.equals("remove")) sender.sendMessage(p.pluginTag + "/team remove [team] [member] " + p.aqua + "- Reloads all teams.");
        if (command.equals("resetall")) sender.sendMessage(p.pluginTag + "/team resetall " + p.aqua + "- Resets all teams (members + points).");
        if (command.equals("tag")) sender.sendMessage(p.pluginTag + "/team tag [team] [tag] " + p.aqua + "- Adjusts a team's tag.");
    }
}
