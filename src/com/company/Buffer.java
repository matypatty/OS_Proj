package com.company;

import java.util.concurrent.Semaphore;

/**
 * Created by root on 8/10/15.
 */
public class Buffer
{
    // remember to initialize the semaphores in the constructor
    private Semaphore empty;
    private Semaphore full;
    private Semaphore mutex;
    //...
    private int[] buffer_item;
    private final int BUFFER_SIZE = 5;
    private int insertPtr = 0;
    private int removePtr = 0;


    public Buffer()
    {
        buffer_item = new int[BUFFER_SIZE];
        empty = new Semaphore(BUFFER_SIZE);
        full = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public int insert_item(int item)
    {

        try
        {
            empty.acquire();
            mutex.acquire();
        }
        catch (InterruptedException e)
        {
            return -1;
        }

        buffer_item[insertPtr] = item;
        insert();

        mutex.release();
        full.release();

        return 0;
    }

    public int remove_item()
    {
        try
        {
            full.acquire();
            mutex.acquire();
        }
        catch (InterruptedException e)
        {
            return -1;
        }

        int remove = buffer_item[removePtr];
        buffer_item[removePtr] = 0;
        remove();

        mutex.release();
        empty.release();

        //cant modify refs in java rip
        return remove;
    }

    private void insert()
    {
        if(insertPtr == BUFFER_SIZE - 1)
            insertPtr = 0;
        else
            insertPtr++;
    }

    private void remove()
    {
        if (removePtr == BUFFER_SIZE - 1)
            removePtr = 0;
        else
            removePtr++;
    }
}

