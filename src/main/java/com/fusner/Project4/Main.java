package com.fusner.Project4;

import java.lang.reflect.Type;
import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main
{

    static Scanner scanner = new Scanner(System.in);
    static ToDoList<Item> list = new ToDoList<>();
    static final String FILENAME = "data.json";
    static Type toDoListType = new TypeToken<ToDoList<Item>>(){}.getType();


    public static void main(String[] args)
    {

        Boolean isInputCorrect = true;
        int choice = -1;
        scanner.useDelimiter("\\n");
        try
        {
            list = load(FILENAME);
            System.out.println("File Loaded");
        } catch (IOException e)
        {
            System.out.println("File not Found");
            try
            {
                save(FILENAME, new ToDoList<Item>());
                System.out.println("File created");
            } catch (IOException e1)
            {
                System.out.println("Failed to create file");
                choice = 0;
            }
        }

        displayMenu();

        try
        {
            choice = scanner.nextInt();
        }
        catch (InputMismatchException e)
        {
            isInputCorrect = false;
        }

        while (choice != 0)
        {
            if (isInputCorrect)
            {
                switch (choice)
                {
                    case 1:
                        add();
                        break;
                    case 2:
                        displayByPriority();
                        break;
                    case 3:
                        displayAll();
                        break;
                }
            }
            scanner = new Scanner(System.in);
            scanner.useDelimiter("\\n");
            displayMenu();
            try
            {
                choice = scanner.nextInt();
                isInputCorrect = true;
            }
            catch (InputMismatchException e)
            {
                isInputCorrect = false;
            }
        }

        try
        {
            save(FILENAME, list);
        } catch (IOException e)
        {
            System.out.println("Failed to save");
        }
    }

    static public void add()
    {

        System.out.println("\nEnter a title:");
        String title = scanner.next();

        System.out.println("\nEnter a description:");
        String description = scanner.next();

        boolean isInputCorrect = false;
        int priority = 0;
        while (!isInputCorrect)
        {
            scanner = new Scanner(System.in);
            scanner.useDelimiter("\\n");
            try
            {
                System.out.println("\nEnter the priority (0-5):");
                priority = scanner.nextInt();
                isInputCorrect = true;
            }
            catch (InputMismatchException e)
            {
                isInputCorrect = false;
            }
        }


        list.add(new Item(title, description, priority));
    }

    static public void displayAll()
    {
        System.out.println("\nTo-Do:");

        ToDoList<Item> sortedList = sort(list);

        for (int i = 0; i < sortedList.size(); i++)
        {
            sortedList.get(i).display();
        }
        System.out.println();
    }

    static public void displayByPriority()
    {
        boolean isInputCorrect = false;
        int priority = 0;
        while (!isInputCorrect)
        {
            scanner = new Scanner(System.in);
            scanner.useDelimiter("\\n");
            try
            {
                System.out.println("\nEnter the priority:");
                priority = scanner.nextInt();
                isInputCorrect = true;
            }
            catch (InputMismatchException e)
            {
                isInputCorrect = false;
            }
        }

        if (priority > 5)
        {
            priority = 5;
        }
        else if (priority < 0)
        {
            priority = 0;
        }

        ToDoList<Item> sortedList = sort(list);

        System.out.println("\nTo-Do:");
        for (int i = 0; i < sortedList.size(); i++)
        {
            if (sortedList.get(i).getPriority() == priority)
            {
                sortedList.get(i).display();
            }
        }
        System.out.println();
    }

    static void displayMenu()
    {
        System.out.println("(1) Add an item.\n(2) List task by priority.\n(3) List all tasks.\n(0) Exit.\nPlease choose an option:");
    }

    static ToDoList<Item> sort(ToDoList<Item> list)
    {
        ToDoList<Item> oldList = new ToDoList<>();
        for (int i = 0; i < list.size(); i++)
        {
            oldList.add(list.get(i));
        }
        ToDoList<Item> newList = new ToDoList<>();
        while (oldList.size() > 0)
        {
            Item highest = oldList.get(0);
            int index = 0;
            for (int i = 0; i < oldList.size(); i++)
            {
                if (oldList.get(i).compareTo(highest) > 0)
                {
                    highest = oldList.get(i);
                    index = i;
                }
            }
            newList.add(highest);
            oldList.remove(index);
        }
        return newList;
    }

    public static ToDoList<Item> load(String filename) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        try {
            return gson.fromJson(reader, toDoListType);
        }
        finally {
            reader.close();
        }
    }


    public static void save(String filename, ToDoList<Item> data) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(filename);
        try {
            gson.toJson(data, writer);
        }
        finally {
            writer.close();
        }
    }

}

