package com.github.norbo11.teams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Team
{

    public Team(String name, boolean newTeam, Teams p)
    {
        try
        {
            this.p = p;
            this.name = name;
            filename = name + ".txt";

            if (newTeam)
            {
                this.name = name;
                points = 0;
                tag = p.methods.convertCodesToColor("&b[" + name + "]");
                save();
            } else
            {
                List<String> contents = p.methodsFile.readFile(filename);

                // Add members
                if (contents.get(0).endsWith(","))
                {
                    String[] members = contents.get(0).split(": ");
                    String[] actualMembers = members[1].split(",");
                    for (String member : actualMembers)
                        this.members.add(new Member(member, this, p));
                }

                // Set points
                String[] points = contents.get(1).split(": ");
                if (p.methods.isDouble(points[1]) == -1) this.points = 0;
                else this.points = p.methods.isDouble(points[1]);

                // Set tag
                String[] tag = contents.get(2).split(": ");
                this.tag = p.methods.convertCodesToColor(tag[1]);

            }
        } catch (Exception e)
        {
            p.log.info("The team " + name + " has a corrupted file!");
        }
    }

    Teams p;

    public String filename;
    public String name;
    public String tag;
    public double points;
    public List<Member> members = new ArrayList<Member>();

    public void save()
    {
        File file = new File(p.pluginDir, filename);
        if (file.exists()) file.delete();
        String temp = "";

        for (Member member : members)
            temp = temp + member.name + ",";

        p.methodsFile.createFile(filename);
        p.methodsFile.addToFile(filename, "Members: " + temp + "\n");
        p.methodsFile.addToFile(filename, "Points: " + points + "\n");
        p.methodsFile.addToFile(filename, "Tag: " + p.methods.convertCodesToColorToCodes(tag));
    }

}
