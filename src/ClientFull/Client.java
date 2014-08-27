/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientFull;

import ServerFull.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author student
 */
public class Client {

    private BufferedReader in;
    private PrintWriter out;
    public static final int TCP_PORT = 9000;
    private MessageData msg;
    private ArrayList<User> onlineUser;

    public boolean login() {

        try {
            // InetAddress addr = InetAddress.getByName("192.168.1.9");
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            Socket sock = new Socket(addr, TCP_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    sock.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    sock.getOutputStream())), true);
            System.out.println("loguj se");
            String username = writeMessage();
         //   String password = writeMessage();
            //   System.out.println("loguj se");
            out.println(username);

            String response = in.readLine();
            if (response.equals("OK")) {
                out.println("list");
                System.out.println(in.readLine());
            }
            System.out.println("login uspjesan");
            msg = new MessageData();
            ReaderThread reader = new ReaderThread(sock, in, msg);
            WriterThread writer = new WriterThread(out, msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

//metoda za  pisanje poruke,poziv konzole
    public String writeMessage() {
        Scanner ulaz = new Scanner(System.in);
        String message = ulaz.nextLine();
        return message;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.login();
    }

}
