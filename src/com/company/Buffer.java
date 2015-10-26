package com.company;

import java.util.concurrent.Semaphore;

/**
 * Created by root on 8/10/15.
 *
 * Group
 * Mathew Bielby 1316896
 * Trevor Hastelow 1304893
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

    public static void main(String[] args)
    {
        //Create the buffer instance.
        Buffer buff = new Buffer();

        //Create Producer instances based on 2nd argument.
        for(int i = 0; i < Integer.parseInt(args[1]); i++)
            new Producer(buff);

        //Create Consumer instances based on 3nd argument.
        for(int i = 0; i < Integer.parseInt(args[2]); i++)
            new Consumer(buff);

        //Sleep for the length of time in seconds of the 1st argument.
        try
        {
            Thread.sleep(Integer.parseInt(args[0]) * 1000);
        }
        catch(InterruptedException ex)
        {
            System.out.println("Thread interrupted.");
        }

        //Finish
        System.exit(0);
    }

    public Buffer()
    {
        //Setup array to hold items
        buffer_item = new int[Constants.BUFFER_SIZE];
        //Create empty semaphore so hold empty spaces.
        empty = new Semaphore(Constants.BUFFER_SIZE);
        //Create full semaphore to hold full spaces.
        full = new Semaphore(0);
        //Create the 0/1 mutex lock.
        mutex = new Semaphore(1);
    }

    //Insert item into the buffer.
    public int insert_item(int item)
    {
        //Wait for an empty spot in the array and take the mutex lock.
        try
        {
            empty.acquire();
            mutex.acquire();
        }
        catch (InterruptedException e)
        {
            //Return -1 for error.
            return -1;
        }

        //Insert the item
        buffer_item[insertPtr] = item;
        //Move the insertpointer
        insert();

        //Release the mutex and add to the full semaphore.
        mutex.release();
        full.release();

        //Return 0 if there was no error
        return 0;
    }

    //Remove item from buffer.
    public int remove_item()
    {
        //Wait for a space to be full and take the mutex lock.
        try
        {
            full.acquire();
            mutex.acquire();
        }
        catch (InterruptedException e)
        {
            //Return -1 for error
            return -1;
        }

        //Remove the item
        int remove = buffer_item[removePtr];
        buffer_item[removePtr] = 0;
        //Move removepointer
        remove();

        //Release the mutex and add to to the empty semaphore
        mutex.release();
        empty.release();

        //cant modify refs in java so return the item removed for displaying purposes.
        return remove;
    }

    //Modifies the insertpointer to move in a circle.
    private void insert()
    {
        if(insertPtr == Constants.BUFFER_SIZE - 1)
            insertPtr = 0;
        else
            insertPtr++;
    }

    //Modifies the removepointer to move in a circle.
    private void remove()
    {
        if (removePtr == Constants.BUFFER_SIZE - 1)
            removePtr = 0;
        else
            removePtr++;
    }
}

