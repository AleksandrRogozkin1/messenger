package github.com.rogozkin.myed;


import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    Message message;


    public Client(Socket socket, Message message) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.message = message;


        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {


        try {

            bufferedWriter.write(message.getName());
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(message.getDate() + ":" + message.getName() + ": " + messageToSend+"\nEnter your message: ");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter chat password: ");
        String password = scanner.nextLine();

        if (password.equalsIgnoreCase("1234")) {
            System.out.println("Enter your name: ");
            String username = scanner.nextLine();

            System.out.println("Enter your status(sleep,eat,work): ");
            String status = scanner.nextLine();

            System.out.println("Enter your message: ");
            Socket socket = new Socket("localhost", 1234);

            String ip = socket.getLocalAddress().getHostAddress();

            String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
            Message message1 = new Message(username, status, ip, timeStamp);
            Client client = new Client(socket, message1);
            client.listenForMessage();
            client.sendMessage();

        }


    }
}