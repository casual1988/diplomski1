/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;


import Server.Server;
import static Server.Server.TCP_PORT;
import ServerFull.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Aleksandar
 */
public class ClientTest  extends Thread{
    
    private BufferedReader in;
     private PrintWriter out;
    public static final int TCP_PORT = 9000;

public static void main(String[] args) {
try {
// odredi adresu racunara sa kojim se povezujemo
// (povezujemo se sa nasim racunarom)
//InetAddress addr = InetAddress.getByName("127.0.0.1");
InetAddress addr = InetAddress.getByName("192.168.1.9");
//InetAddress addr = InetAddress.getByName("192.168.92.128");
// otvori socket prema drugom racunaru
Socket sock = new Socket(addr, TCP_PORT);
// inicijalizuj ulazni stream
BufferedReader in = new BufferedReader(new InputStreamReader(
sock.getInputStream()));
// inicijalizuj izlazni stream
PrintWriter out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(
sock.getOutputStream())), true);
//System.out.println("[Client]: HELLO");
String pom="";
String response;
//InetAddress thisIp = InetAddress.getLocalHost();
 //       System.out.println("IP:" + thisIp.getHostAddress());

while(!pom.equals("kraj")){
System.out.println("[Client]: HELLO");
Scanner ulaz =new Scanner(System.in);
pom=ulaz.nextLine();
out.println(pom);
// procitaj odgovor
 response = in.readLine();
 if(!response.equals("")){
     System.out.println("[Server]: " + response);

// connectionWithClient(response);
 }
System.out.println("[Server]: " + response);
}
// zatvori konekciju
in.close();
out.close();
sock.close();
} catch (UnknownHostException e1) {
e1.printStackTrace();
} catch (IOException e2) {
e2.printStackTrace();
}
}


private static void connectionWithClient(String socket) throws UnknownHostException, IOException{
//String[] niz = socket.split("/.");

   
    String response="";
//acceptClient(socket);
//System.out.println("[niz]: " + niz[0]);
    
    InetAddress addr1 = InetAddress.getByName("192.168.1.4");
//Socket sock1 = new Socket(addr1, Integer.parseInt(niz[1]));
Socket sock1 = new Socket(addr1, Integer.parseInt(socket));
BufferedReader in = new BufferedReader(new InputStreamReader(
sock1.getInputStream()));
// inicijalizuj izlazni stream
PrintWriter out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(
sock1.getOutputStream())), true);
out.println("proba");
in.readLine();
System.out.println("[CL]: " + response);
}

public void acceptClient(String port,int poziv){

  
try {
      if(poziv==0){
int clientCounter = 0;
// slušaj zahteve na datom portu
int Port=9001;
ServerSocket ss = new ServerSocket(Port);
System.out.println("Klijentski server pokreut ...");
while (true) {
Socket sock = ss.accept();
// inicijalizuj ulazni stream
in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
// inicijalizuj izlazni stream
out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
 String request;
    while ((request = in.readLine()) != null) {
        System.out.println(request);
     out.println("haloo klijenntu 2 ,ovde kec");
    }
}
      }
      if(poziv==1){
      
   //   InetAddress addr = InetAddress.getByName("192.168.92.128");
          InetAddress addr = InetAddress.getByName("192.168.1.7");
// otvori socket prema drugom racunaru
Socket sock = new Socket(addr, 9001);
// inicijalizuj ulazni stream
BufferedReader in = new BufferedReader(new InputStreamReader(
sock.getInputStream()));
// inicijalizuj izlazni stream
PrintWriter out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(
sock.getOutputStream())), true);
      out.println("djes cova");
// procitaj odgovor
String response = in.readLine();
          System.out.println(response);
      
      
      }
} catch (Exception ex) {
ex.printStackTrace();
}
}
}
