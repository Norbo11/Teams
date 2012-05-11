package com.github.norbo11.teams;

public class Member
{
    public Member(String name, Team team, Teams p)
    {
        this.p = p;
        this.name = name;
        this.team = team;
    }

    Teams p;

    String name;
    Team team;

    public String setTag(String oldFormat)
    {
        return team.tag + " " + oldFormat;
    }
}
