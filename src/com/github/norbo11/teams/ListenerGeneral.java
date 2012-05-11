package com.github.norbo11.teams;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ListenerGeneral implements Listener
{
    public ListenerGeneral(Teams p)
    {
        this.p = p;
    }

    Teams p;

    @EventHandler
    public void onPlayerSpeak(PlayerChatEvent event)
    {
        Member member = p.methods.isAMember(event.getPlayer().getName());
        if (member != null)
        {
            event.setFormat(member.setTag(event.getFormat()));
        }
    }
}
