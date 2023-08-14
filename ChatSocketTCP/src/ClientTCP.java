import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public void start(String serverIp, int serverPort) throws IOException {
        System.out.println("[C1] Conectado ao serve " + serverIp + ":" + serverPort);
        socket = new Socket(serverIp, serverPort);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        System.out.println("[C2] Conexão estabelecida, eu sou o cliente: " + socket.getLocalSocketAddress());

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite seu Username: ");
            String username = scanner.nextLine();
            output.writeUTF(username);

            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = input.readUTF();
                        System.out.print("Mensagem de ");
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Server Desconectado: " + e.getMessage());
                }
            });
            receiveThread.start();

            while (true) {
                String msg = scanner.nextLine();
                output.writeUTF(msg);
            }
        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String serverIp = "0.0.0.0";
        int serverPort = 6666;
        try {
            ClientTCP client = new ClientTCP();
            client.start(serverIp, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
