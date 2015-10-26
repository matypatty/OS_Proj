package com.company;

import java.util.concurrent.Semaphore;

/**
 * Created by root on 8/10/15.
 */
public class Buffer
{
    private Semaphore empty;
    private Semaphore full;
    private Semaphore mutex;
    //...
    private int[] buffer_item;
    private int insertPtr = 0;
    private int removePtr = 0;

    public Buffer()
    {
        buffer_item = new int[Constants.BUFFER_SIZE];
        empty = new Semaphore(Constants.BUFFER_SIZE);
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
        if(insertPtr == Constants.BUFFER_SIZE - 1)
            insertPtr = 0;
        else
            insertPtr++;
    }

    private void remove()
    {
        if (removePtr == Constants.BUFFER_SIZE - 1)
            removePtr = 0;
        else
            removePtr++;
    }
}

