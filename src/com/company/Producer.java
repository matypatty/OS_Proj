package com.company;

import java.util.Random;

/**
 * Created by root on 8/10/15.
 */
public class Producer implements Runnable
{
    private Buffer buffer;

    public Producer(Buffer buffer)
    {
        //Set buffer instance
        this.buffer = buffer;
        //Start itself in new thread.
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        //Init random
        Random rand = new Random();

        while (true)
        {
            try
            {
                //Sleep for a random time (not less than 500 so that the output isnt jumbled)
                Thread.sleep(rand.nextInt(Constants.SLEEP_RND) + Constants.SLEEP_BASE);
            }
            catch(InterruptedException ex)
            {
                System.out.println("Thread interrupted");
            }


            //Try and produce an item
            int produce = rand.nextInt(Constants.PRODUCE_MAX);
            if (buffer.insert_item(produce) != 0)
                System.out.println("Error inserting item " + produce);
            else
                System.out.println("producer produced " + produce);
        }
    }
}
