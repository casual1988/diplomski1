/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import ServerFull.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Aleksandar
 */
public class Server extends Thread {
  
private Socket sock;
private int value;
private BufferedReader in;
private PrintWriter out;
public static final int TCP_PORT = 9000;
private static ArrayList<User> users= new ArrayList<User>();
private static ArrayList<User> clientList= new ArrayList<User>();

public static void main(String[] args) {
    
//    users.add(new User("aleksandar"));
//    users.add(new User("marko"));
//    users.add(new User("pero"));
//    users.add(new User("simo"));
   
try {
int clientCounter = 0;
// slušaj zahteve na datom portu
ServerSocket ss = new ServerSocket(TCP_PORT);
//System.out.println(ss.getInetAddress() +" "+ ss.getLocalSocketAddress());
System.out.println("Server running...");
while (true) {
Socket sock = ss.accept();
System.out.println("Client prihvacen: " + (++clientCounter));
Server st = new Server(sock, clientCounter);
//clientList.add(new User(sock));

}
} catch (Exception ex) {
ex.printStackTrace();
}}


public Server(Socket sock, int value) {
this.sock = sock;
this.value = value;
try {
// inicijalizuj ulazni stream
in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
// inicijalizuj izlazni stream
out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);    
} catch (Exception ex) {
ex.printStackTrace();
}
start();}


public void run() {
try {
  
    String request;
    while ((request = in.readLine()) != null) {
// procitaj zahtjev
// odgovori na zahtjev
if(request.equals("list")){
 // out.println(ServerMethods.listUsers(clientList,this.sock));
  out.println(clientList);
}
else if(request.contains("conect")){
out.println("200");

}else if(request.contains("saberi")){    //saebri 5 10
String[] niz = request.split(" ");
//int rezultat= Metode.saberi(niz[1], niz[2]);
//out.println("rezultat sabiranja je : " + rezultat);

}else if(request.contains("upis")){    //upis u fajl
//String[] niz = request.split(" ");
 //Metode.upis(request);
 out.println("Fajl uspjesno snimljen");
//out.println("rezultat sabiranja je : " + rezultat);

}else if(request.contains("ocitaj")){    //upis u fajl
//String[] niz = request.split(" ");
 //String odgovor= Metode.ocitaj(request);
 //out.println(odgovor);
//out.println("rezultat sabiranja je : " + rezultat);

}


else {
out.println("pogresna komanda");
}

    }
in.close();
out.close();
sock.close();

} catch (Exception ex) {
ex.printStackTrace();
}
}


}