package com.company;

import java.util.Random;

/**
 * Created by root on 8/10/15.
 */
public class Consumer implements Runnable
{
    private Buffer buffer;

    public Consumer(Buffer buffer)
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

            //Try and consume the item.
            int removed = 0;
            if ((removed = buffer.remove_item()) == -1)
                System.out.println("Error consuming");
            else
                System.out.println("consumer consumed " + removed);
        }
    }
}
