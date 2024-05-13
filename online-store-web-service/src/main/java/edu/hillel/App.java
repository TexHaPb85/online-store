package edu.hillel;

import java.io.IOException;

import edu.hillel.socket.MainServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            MainServer mainServer = new MainServer(8081);
            mainServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
