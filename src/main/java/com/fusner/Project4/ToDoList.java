package com.fusner.Project4;

import java.util.*;

public class ToDoList<E> implements Iterable<E>
{
    private ArrayList<E> values;

    public ToDoList(ArrayList<E> values)
    {
        this.values = values;
    }

    public ToDoList()
    {
        this.values = new ArrayList<>();
    }

    class ArrayIterator implements Iterator<E>
    {
        int current = 0;

        public boolean hasNext()
        {
            if (current < ToDoList.this.values.size())
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public E next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            return values.get(current++);
        }
    }

    public E get(int index)
    {
        return values.get(index);
    }

    public void add(E item)
    {
        values.add(item);
    }

    public void set(int index, E value)
    {
        values.set(index, value);
    }

    public int size()
    {
        return values.size();
    }

    public Iterator<E> iterator()
    {
        return new ArrayIterator();
    }

    public void remove(int index)
    {
        values.remove(index);
    }


}
