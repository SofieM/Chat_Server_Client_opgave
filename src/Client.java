// Java implementation for a client
// Save file as Client.java

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner scn = new Scanner(System.in);

            // Sætter ip til "local host"
            InetAddress ip = InetAddress.getByName("localhost");

            // til at oprette forbindelse til serveren på port 5056
            Socket s = new Socket(ip, 5056);

            //Input og output streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // Loop der håndterer udveklslingen af beskeder
            // mellem Client og ClientHandler
            while (true)
            {
                System.out.println(dis.readUTF());
                String tosend = scn.nextLine();
                dos.writeUTF(tosend);

                // Hvis klienten sender beskeden exit
                //så afsluttes loopet
                if(tosend.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Lukker forbindelse : " + s);
                    s.close();
                    System.out.println("Forbindelse lukket");
                    break;
                }

                //Skriver den modtagne besked, ud i konsollen
                String received = dis.readUTF();
                System.out.println(received);
            }

            scn.close();
            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}