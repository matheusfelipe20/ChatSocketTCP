import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTCP {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public void start(int port) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            clients = new ArrayList<>();

            while (serverSocket.isBound()) {
                System.out.println("Aguardando a conexão de um cliente...");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente Conectado: " + socket.getRemoteSocketAddress());

                ClientHandler clientHandler = new ClientHandler(socket, this);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (serverSocket != null) serverSocket.close();
            for (ClientHandler client : clients) {
                client.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    public void sendMessageToClient(String clientName, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(clientName)) {
                client.sendMessage(message);
                return;
            }
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void main(String[] args) {
        int serverPort = 6666;
        try {
            ServerTCP server = new ServerTCP();
            server.start(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
