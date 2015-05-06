package com.github.norbo11.teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListenerCommandExecutor implements CommandExecutor
{
    public ListenerCommandExecutor(Teams p)
    {
        this.p = p;
    }

    Teams p;

    public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args)
    {
        if (command.getName().equalsIgnoreCase("tc"))
        {
            try
            {
                if (sender.hasPermission("teams.teamchat"))
                {
                    if (Methods.hasPickedGroup(sender))
                    {
                        if (args.length == 0) Methods.chatSwitch(sender);
                        else MethodsError.usage(sender, "tc");
                        return true;
                    } else MethodsError.notPickedGroup(sender);
                } else MethodsError.noPermission(sender);
            } catch (Exception e)
            {
                sender.sendMessage(p.red + "An error has occurred (please report on DevBukkit project 'Teams'): " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (command.getName().equalsIgnoreCase("teams"))
        {
            try
            {
                if (args.length > 0)
                {
                    String action = args[0];
                    if (action.equalsIgnoreCase("help"))
                    {
                        if (args.length == 2) MethodsError.usage(sender, args[1]);
                        else MethodsError.displayHelp(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("new"))
                    {
                        if (sender.hasPermission("teams.create"))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 2) Methods.teamCreate(sender, args[1]);
                                else MethodsError.usage(sender, "new");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("group"))
                    {
                        if (args.length > 2)
                        {
                            if (args[1].equalsIgnoreCase("switch"))
                            {
                                if (sender.hasPermission("teams.use"))
                                {
                                    if (args.length == 3) Methods.groupSwitch(sender, args[2]);
                                    else MethodsError.usage(sender, "groupswitch");
                                } else MethodsError.noPermission(sender);
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("new"))
                            {
                                if (sender.hasPermission("teams.create"))
                                {
                                    if (args.length == 3) Methods.groupCreate(sender, args[2]);
                                    else MethodsError.usage(sender, "groupnew");
                                } else MethodsError.noPermission(sender);
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("del"))
                            {
                                if (sender.hasPermission("teams.delete"))
                                {
                                    if (args.length == 3) Methods.groupDelete(sender, args[2]);
                                    else MethodsError.usage(sender, "groupdel");
                                } else MethodsError.noPermission(sender);
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("rename"))
                            {
                                if (sender.hasPermission("teams.modify"))
                                {
                                    if (args.length == 4) Methods.groupRename(sender, args[2], args[3]);
                                    else MethodsError.usage(sender, "grouprename");
                                    return true;
                                } else MethodsError.noPermission(sender);
                                return true;
                            }
                            MethodsError.usage(sender, "group");
                        } else MethodsError.usage(sender, "group");
                        return true;
                    }
                    if (action.equalsIgnoreCase("set"))
                    {
                        if (args.length >= 4)
                        {
                            if (sender.hasPermission("teams.modify") || Methods.isLeader(sender, args[2]))
                            {
                                if (args[1].equalsIgnoreCase("tag"))
                                {
                                    if (Methods.hasPickedGroup(sender))
                                    {
                                        if (args.length == 4) Methods.teamSetTag(sender, args[2], args[3]);
                                        else MethodsError.usage(sender, "settag");
                                    } else MethodsError.notPickedGroup(sender);
                                    return true;
                                }
                                if (args[1].equalsIgnoreCase("displayTag"))
                                {
                                    if (Methods.hasPickedGroup(sender))
                                    {
                                        if (args.length == 4) Methods.teamSetBoolean(sender, args[2], "displayTag", args[3]);
                                        else MethodsError.usage(sender, "setdisplaytag");
                                    } else MethodsError.notPickedGroup(sender);
                                    return true;
                                }
                                if (args[1].equalsIgnoreCase("noMemberPVP"))
                                {
                                    if (Methods.hasPickedGroup(sender))
                                    {
                                        if (args.length == 4) Methods.teamSetBoolean(sender, args[2], "noMemberPVP", args[3]);
                                        else MethodsError.usage(sender, "setnomemberpvp");
                                    } else MethodsError.notPickedGroup(sender);
                                    return true;
                                }
                                if (args[1].equalsIgnoreCase("leader"))
                                {
                                    if (Methods.hasPickedGroup(sender))
                                    {
                                        if (args.length == 4) Methods.teamSetLeader(sender, args[2], args[3]);
                                        else MethodsError.usage(sender, "setleader");
                                    } else MethodsError.notPickedGroup(sender);
                                    return true;
                                }
                            } else MethodsError.noPermission(sender);
                            MethodsError.usage(sender, "set");
                        } else MethodsError.usage(sender, "set");
                        return true;
                    }
                    if (action.equalsIgnoreCase("del"))
                    {
                        if (args.length == 2)
                        {
                            if (sender.hasPermission("teams.delete") || Methods.isLeader(sender, args[1]))
                            {
                                if (Methods.hasPickedGroup(sender)) Methods.teamDelete(sender, args[1]);
                                else MethodsError.notPickedGroup(sender);
                            } else MethodsError.noPermission(sender);
                        } else MethodsError.usage(sender, "del");
                        return true;
                    }
                    if (action.equalsIgnoreCase("addmanager"))
                    {
                        if (sender.hasPermission("teams.modify") || Methods.isLeader(sender, args[1]))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 3) Methods.teamAddManager(sender, args[1], args[2]);
                                else MethodsError.usage(sender, "addmanager");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("removemanager"))
                    {
                        if (sender.hasPermission("teams.modify") || Methods.isLeader(sender, args[1]))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 3) Methods.teamRemoveManager(sender, args[1], args[2]);
                                else MethodsError.usage(sender, "removemanager");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("leave"))
                    {
                        if (sender.hasPermission("teams.use"))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 1) Methods.teamLeave(sender);
                                else MethodsError.usage(sender, "leave");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("add"))
                    {
                        if (sender.hasPermission("teams.modify"))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 3) Methods.teamMemberAdd(sender, args[1], args[2]);
                                else MethodsError.usage(sender, "add");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("rename"))
                    {
                        if (sender.hasPermission("teams.modify"))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 3) Methods.teamRename(sender, args[1], args[2]);
                                else MethodsError.usage(sender, "rename");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("tpall"))
                    {
                        if (sender instanceof Player)
                        {
                            if (sender.hasPermission("teams.tpall"))
                            {
                                if (Methods.hasPickedGroup(sender))
                                {
                                    if (args.length == 1) Methods.teamTpAll((Player) sender);
                                    else MethodsError.usage(sender, "tpall");
                                } else MethodsError.notPickedGroup(sender);
                            } else MethodsError.noPermission(sender);
                        } else MethodsError.notAPlayer(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("invite"))
                    {
                        if (args.length == 3)
                        {
                            if (sender.hasPermission("teams.use"))
                            {
                                if (Methods.isManager(args[1], sender.getName()) != null)
                                {
                                    if (Methods.hasPickedGroup(sender)) Methods.teamMemberInvite(sender, args[1], args[2]);
                                    else MethodsError.notPickedGroup(sender);
                                } else MethodsError.notAManagerFP(sender);
                            } else MethodsError.noPermission(sender);
                        } else MethodsError.usage(sender, "invite");
                        return true;
                    }
                    if (action.equalsIgnoreCase("accept"))
                    {
                        if (sender.hasPermission("teams.use"))
                        {
                            if (args.length == 1) Methods.teamMemberAccept(sender);
                            else MethodsError.usage(sender, "accept");
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("listsettings"))
                    {
                        if (args.length == 1) Methods.settingsDisplay(sender);
                        else MethodsError.usage(sender, "listsettings");
                        return true;
                    }
                    if (action.equalsIgnoreCase("points"))
                    {
                        if (sender.hasPermission("teams.modify"))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 4) Methods.teamPointsAdjust(sender, args[1], args[2], args[3]);
                                else MethodsError.usage(sender, "points");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("view"))
                    {
                        if (sender.hasPermission("teams.use"))
                        {
                            if (args.length == 2)
                            {
                                if (Methods.hasPickedGroup(sender)) Methods.teamView(sender, args[1]);
                                else MethodsError.notPickedGroup(sender);
                            } else if (args.length == 1) Methods.teamView(sender, null);
                            else MethodsError.usage(sender, "view");
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("list"))
                    {
                        if (sender.hasPermission("teams.use"))
                        {
                            if (args.length == 1) Methods.teamsDisplay(sender);
                            else MethodsError.usage(sender, "list");
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("reset"))
                    {
                        if (sender.hasPermission("teams.reset"))
                        {
                            if (args.length == 1) Methods.teamsReset(sender, null);
                            else if (args.length == 2) Methods.teamsReset(sender, args[1]);
                            else MethodsError.usage(sender, "reset");
                            return true;
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("reload"))
                    {
                        if (sender.hasPermission("teams.reload"))
                        {
                            if (args.length == 1) Methods.teamsReload(sender);
                            else MethodsError.usage(sender, "reload");
                            return true;
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("remove"))
                    {
                        if (sender.hasPermission("teams.modify"))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length == 3) Methods.teamMemberRemove(sender, args[1], args[2]);
                                else MethodsError.usage(sender, "remove");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                    if (action.equalsIgnoreCase("distribute"))
                    {
                        if (sender.hasPermission("teams.modify"))
                        {
                            if (Methods.hasPickedGroup(sender))
                            {
                                if (args.length >= 2) Methods.groupDistribute(sender, args);
                                else MethodsError.usage(sender, "distribute");
                            } else MethodsError.notPickedGroup(sender);
                        } else MethodsError.noPermission(sender);
                        return true;
                    }
                }
                MethodsError.displayHelp(sender);
                return true;
            } catch (Exception e)
            {
                sender.sendMessage(p.red + "An error has occurred (please report on DevBukkit project 'Teams'): " + e.getMessage());
                e.printStackTrace();
            }
        }
        return true;
    }
}
