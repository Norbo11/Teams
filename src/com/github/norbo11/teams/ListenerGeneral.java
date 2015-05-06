package com.github.norbo11.teams;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ListenerGeneral implements Listener
{
    public ListenerGeneral(Teams p)
    {
        this.p = p;
    }

    Teams p;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent event)
    {
        try
        {
            if (event.getEntity() instanceof Player)
            {
                Player player = (Player) event.getEntity();
                if (event.getDamager() instanceof Player)
                {
                    Player player2 = (Player) event.getDamager();
                    List<String> playerTeams = Methods.isMember(player.getName());
                    int i = 0;
                    for (String team : playerTeams)
                    {
                        String[] splitTeam = team.split("\\.");
                        playerTeams.set(i, splitTeam[0] + "." + splitTeam[1]);
                        i++;
                    }
                    for (String team2 : Methods.isMember(player2.getName()))
                    {
                        String[] splitTeam2 = team2.split("\\.");
                        if (playerTeams.contains(splitTeam2[0] + "." + splitTeam2[1]) && p.configTeams.getBoolean(splitTeam2[0] + "." + splitTeam2[1] + ".settings.noMemberPVP")) event.setCancelled(true);
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        try
        {
            String chatTeam = p.playerChatChoice.get(event.getPlayer().getName());
            if (chatTeam != null)
            {
                for (String member : p.configTeams.getStringList(chatTeam + ".members"))
                {
                    Player player = Bukkit.getPlayer(member);
                    if (player != null && player.isOnline()) player.sendMessage(p.gold + "(Team Chat) " + Methods.convertCodesToColor(p.configTeams.getString(chatTeam + ".settings.tag")) + " - " + event.getPlayer().getName() + ": " + event.getMessage());
                }
                event.setCancelled(true);
                return;
            }
            List<String> members;
            members = Methods.isMember(event.getPlayer().getName());

            if (members.size() > 0)
            {
                String newFormat = "";
                for (String member : members)
                {
                    String splitMember[] = member.split("\\.");
                    if (p.configTeams.getBoolean(splitMember[0] + "." + splitMember[1] + ".settings.displayTag")) newFormat = newFormat + Methods.convertCodesToColor(p.configTeams.getString(splitMember[0] + "." + splitMember[1] + ".settings.tag")) + " ";
                }
                event.setFormat(newFormat + ChatColor.RESET + event.getFormat());
            }
        } catch (Exception e){ e.printStackTrace(); }
    }
}
