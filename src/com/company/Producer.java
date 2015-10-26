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

            int produce = rand.nextInt(Constants.PRODUCE_MAX);
            if (buffer.insert_item(produce) != 0)
                System.out.println("Error inserting item " + produce);
            else
                System.out.println("producer produced " + produce);
        }
    }
}
