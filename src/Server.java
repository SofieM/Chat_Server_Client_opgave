import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

// Server class 
public class Server
{
    public static void main(String[] args) throws IOException
    {
        //Serveren lyytter på port 5056
        ServerSocket ss = new ServerSocket(5056);

        // Infinite loop som venter på request fra klienter
        while (true)
        {
            Socket s = null;

            try
            {
                // socket objekt, der venter på klienter
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // Input og output streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                //Laver nyt thread-objekt, med den socket, klienten er tilknyttet
                // samt input og output stream (til at sende og modtage beskeder)
                Thread t = new ClientHandler(s, dis, dos);

                // Kalder trådens startmetode
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
} 