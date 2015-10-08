package com.company;

import javafx.application.Application;

public class Main {

    public static void main(String[] args)
    {
        Buffer buff = new Buffer();

        for(int i = 0; i < Integer.parseInt(args[1]); i++)
            new Producer(buff);

        for(int i = 0; i < Integer.parseInt(args[2]); i++)
            new Consumer(buff);

        try
        {
            Thread.sleep(Integer.parseInt(args[0]));
        }
        catch(Exception ex)
        {
            System.out.println("Error in main sleep");
        }

        System.exit(0);
    }
}
