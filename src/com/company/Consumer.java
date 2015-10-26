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
        this.buffer = buffer;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        Random rand = new Random();

        while (true)
        {
            try
            {
                //Used this sleep time so that the output was correct.
                Thread.sleep(rand.nextInt(Constants.SLEEP_RND) + Constants.SLEEP_BASE);
            }
            catch(InterruptedException ex)
            {
                System.out.println("Thread interrupted");
            }

            int removed = 0;
            if ((removed = buffer.remove_item()) == -1)
                System.out.println("Error consuming");
            else
                System.out.println("consumer consumed " + removed);
        }
    }
}
