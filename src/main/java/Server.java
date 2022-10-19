import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This example is from: https://www.youtube.com/watch?v=ZIzoesrHHQo
 */

public class Server {
    private static String[] names = {"Luiz", "Joao", "Edilson", "Flavio"};
    private static String[] adjs = {"the ugly", "the adventurer", "the philosopher", "the father"};
    private static final int PORT = 9090;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);

        while (true) {
            System.out.println("[SERVER] Waiting for client connection...");
            Socket client = listener.accept();
            System.out.println("[SERVER] Connected to client!");
            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }

    }

    public static String getRandomName() {
        String name = names[(int)(Math.random()* names.length)];
        String adj = adjs[(int)(Math.random()* adjs.length)];

        return name + " " + adj;
    }
}
