package com.github.norbo11.teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListenerCommandExecutor implements CommandExecutor
{
    public ListenerCommandExecutor(Teams p)
    {
        this.p = p;
    }

    Teams p;

    public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args)
    {
        if (command.getName().equalsIgnoreCase("team"))
        {
            if (args.length > 0)
            {
                String action = args[0];
                if (action.equalsIgnoreCase("new"))
                {
                    if (sender.hasPermission("teams.create"))
                    {
                        if (args.length == 2) p.methods.createTeam(sender, args[1]);
                        else p.methodsError.usage(sender, "new");
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("del"))
                {
                    if (sender.hasPermission("teams.delete"))
                    {
                        if (args.length == 2) p.methods.deleteTeam(sender, args[1]);
                        else p.methodsError.usage(sender, "del");
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("add"))
                {
                    if (sender.hasPermission("teams.modify") || p.getConfig().getBoolean("allowExistingMembersToAdd"))
                    {
                        if (args.length == 3) p.methods.addMember(sender, args[1], args[2]);
                        else p.methodsError.usage(sender, "add");
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("remove"))
                {
                    if (sender.hasPermission("teams.modify"))
                    {
                    }
                    if (args.length == 3) p.methods.removeMember(sender, args[1], args[2]);
                    else p.methodsError.usage(sender, "remove");
                    return true;
                }
                if (action.equalsIgnoreCase("points"))
                {
                    if (sender.hasPermission("teams.modify"))
                    {
                        if (args.length == 4) p.methods.adjustPoints(sender, args[1], args[2], args[3]);
                        else p.methodsError.usage(sender, "points");
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("list"))
                {
                    if (sender.hasPermission("teams.view"))
                    {
                        if (args.length == 1) p.methods.displayTeams(sender);
                        else p.methodsError.usage(sender, "list");
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("view"))
                {
                    if (sender.hasPermission("teams.view"))
                    {
                        if (args.length == 2) p.methods.viewTeam(sender, args[1]);
                        else p.methodsError.usage(sender, "view");
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("reload"))
                {
                    if (sender.hasPermission("teams.reload"))
                    {
                        if (args.length == 1)
                        {
                            p.methods.getTeams();
                            sender.sendMessage(p.pluginTag + "All teams reloaded.");
                        } else p.methodsError.usage(sender, "reload");
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("resetall"))
                {
                    if (sender.hasPermission("teams.modify"))
                    {
                        if (args.length == 1) p.methods.resetAll(sender);
                        else p.methodsError.usage(sender, "resetall");
                        return true;
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
                if (action.equalsIgnoreCase("tag"))
                {
                    if (sender.hasPermission("teams.modify"))
                    {
                        if (args.length == 3) p.methods.setTag(sender, args[1], args[2]);
                        else p.methodsError.usage(sender, "tag");
                        return true;
                    } else p.methodsError.noPermission(sender);
                    return true;
                }
            }
            p.methodsError.displayHelp(sender);
            return true;
        }
        return true;
    }
}
