package com.fusner.Project4;

public class Item implements Comparable<Item>
{
    private String title;
    private String description;
    private int priority;

    public Item(String title, String description, int priority)
    {
        this.title = title;
        this.description = description;

        if (priority > 5)
        {
            this.priority = 5;
        }
        else if (priority < 0)
        {
            this.priority = 0;
        }
        else
        {
            this.priority = priority;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public int getPriority()
    {
        return priority;
    }

    public void display()
    {
        System.out.println("(" + this.priority + ") " + this.title + ": " + this.description);
    }

    @Override
    public int compareTo(Item o)
    {
        if (this.priority > o.priority)
        {
            return 1;
        }
        else if (this.priority < o.priority)
        {
            return -1;
        }
        else
        {
            if(this.title.compareTo(o.title) > 0)
            {
                return -1;
            }
            else if(this.title.compareTo(o.title) < 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}
