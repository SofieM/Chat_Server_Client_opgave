import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


// ClientHandler class
class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run()
    {
        String received;
        String toreturn;
        while (true)
        {
            try {

                // Startbesked til klienten
                dos.writeUTF("Hvad vil du?[Chatte? Indtast 'Chat' | Afslutte? Indtast 'Exit']");

                //Modtager besked fra klienten
                received = dis.readUTF();

                if(received.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Klient " + this.s + " sendte 'Exit'...");
                    System.out.println("Lukker forbindelse");
                    this.s.close();
                    System.out.println("Forbindelse lukket");
                    break;
                }

                // Sender en besked, ud fra klientens svar
                switch (received) {

                    case "Chat" :
                        toreturn = "Velkommen";
                        dos.writeUTF(toreturn);
                        break;

                    default:
                        dos.writeUTF("Ukendt input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            //lukker in- og output streams
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
} 