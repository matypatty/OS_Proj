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
                Thread.sleep(rand.nextInt(10));
            }
            catch(Exception ex)
            {
                System.out.println("report error condition");
            }
            int removed = 0;
            if ((removed = buffer.remove_item()) == -1)
                System.out.println("report error condition");
            else
                System.out.println("consumer consumed " + removed);
        }
    }
}
