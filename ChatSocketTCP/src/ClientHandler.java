import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClientHandler extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    ServerTCP server;
    String username;

    public ClientHandler(Socket aClientSocket, ServerTCP server) {
        try {
            clientSocket = aClientSocket;
            this.server = server;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Conexão:" + e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            System.out.println("Mensagem: " + e.getMessage());
        }
    }

    public void run() {
        try {
            username = in.readUTF();

            while (true) {
                String data = in.readUTF();
                System.out.println("Mensagem de " + username + ": " + data);

                if (data.startsWith("@")) {
                    String[] parts = data.split(" ", 2);
                    String targetUsername = parts[0].substring(1);
                    String message = parts[1];
                    server.sendMessageToClient(targetUsername, username + " às (" + getCurrentTime() + "h" + "): " + message);
                } else {
                    server.broadcastMessage(username + " às (" + getCurrentTime() + "h" + "): " + data, this);
            }
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Erro ao tentar fechar o Socket");
            }
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(new Date());
    }

    public void close() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar os recursos do cliente: " + e.getMessage());
        }
    }
}
