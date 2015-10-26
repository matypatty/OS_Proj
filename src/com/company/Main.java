package com.company;

/*
* Group
* Mathew Bielby 1316896
* Trevor Hastelow 1304893
 */
public class Main
{
    public static void main(String[] args)
    {
        Buffer buff = new Buffer();

        for(int i = 0; i < Integer.parseInt(args[1]); i++)
            new Producer(buff);

        for(int i = 0; i < Integer.parseInt(args[2]); i++)
            new Consumer(buff);

        try
        {
            Thread.sleep(Integer.parseInt(args[0]) * 1000);
        }
        catch(InterruptedException ex)
        {
            System.out.println("Thread interrupted.");
        }

        System.exit(0);
    }
}
