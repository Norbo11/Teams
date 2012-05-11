package com.github.norbo11.teams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MethodsFile
{
    public MethodsFile(Teams p)
    {
        this.p = p;
    }

    Teams p;

    public void addToFile(String filename, String lineToAdd)
    {
        try
        {
            File file = new File(p.pluginDir, "\\" + filename);
            FileWriter writer = new FileWriter(file, true);
            writer.append(lineToAdd);
            writer.flush();
            writer.close();
        } catch (Exception e)
        {
            p.log.info("An error has occured when adding to a file " + filename);
            e.printStackTrace();
        }
    }

    public void createFile(String filename)
    {
        try
        {
            File file = new File(p.pluginDir, "\\" + filename);
            file.createNewFile();
        } catch (Exception e)
        {
            p.log.info("An error has occured when creating a new file " + filename);
            e.printStackTrace();
        }
    }

    public List<String> readFile(String filename)
    {
        try
        {
            File file = new File(p.pluginDir, "\\" + filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> contents = new ArrayList<String>();
            String temp = "";

            while ((temp = reader.readLine()) != null)
                contents.add(temp);
            reader.close();

            return contents;
        } catch (Exception e)
        {
            p.log.info("An error has occured when reading a file " + filename);
            e.printStackTrace();
        }
        return null;
    }
}
