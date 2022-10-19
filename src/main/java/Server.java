import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
    private static String[] names = {"Luiz", "Joao", "Edilson", "Flavio"};
    private static String[] adjs = {"the ugly", "the adventurer", "the philosopher", "the father"};
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        System.out.println("[SERVER] Waiting for client connection...");
        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        try {
            while (true) {
                String request = in.readLine();
                if (request.contains("name")) {
                    out.println(getRandomName());
                } else {
                    out.println("Type 'tell me a name' to get a random name");
                }
            }
        } finally {
            client.close();
            in.close();
        }

        //String date = (new Date()).toString();
        //System.out.println("[SERVER] Sending date " + date);
        //out.println(date);
        //out.println(getRandomName());

        //System.out.println("[SERVER] Data sent. Closing...");
        //client.close();
        //listener.close();
    }

    public static String getRandomName() {
        String name = names[(int)(Math.random()* names.length)];
        String adj = adjs[(int)(Math.random()* adjs.length)];

        return name + " " + adj;
    }
}
