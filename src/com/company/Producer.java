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
                Thread.sleep(rand.nextInt(10));
            }
            catch(Exception ex)
            {
                System.out.println("report error condition");
            }

            int produce = rand.nextInt(100);
            if (buffer.insert_item(produce) != 0)
                System.out.println("report error condition");
            else
                System.out.println("producer produced " + produce);
        }
    }
}
