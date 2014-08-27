/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientFull;

/**
 *
 * @author student
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Aleksandar
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine; 



public class QuoteClient {
    
    
    static ByteArrayOutputStream out = new ByteArrayOutputStream();;
    
    //pokusaj za pustanej zvuka
      byte audio[] ;
      InputStream input;
      final AudioFormat format ;
      final AudioInputStream ais ;
      DataLine.Info info ;
      final SourceDataLine line;
    private static QuoteClient instance = null;
  
    
    public static void main(String[] args)  {
 int bufferSize=2000 ;
        if (args.length != 1) {
             System.out.println("Usage: java QuoteClient <hostname>");
      //       return;
        }
        int i = 0;
 try{
            // get a datagram socket
        DatagramSocket socket = new DatagramSocket();
 
            // send request
        byte[] buf = new byte[bufferSize];  //8000
     //   InetAddress address = InetAddress.getByName(args[0]);
        InetAddress address = InetAddress.getByName("192.168.1.8");
      
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
       
        socket.send(packet);
     //ovde malo treba srediti cod da se dobije bffer
            // get response
     
       
       QuoteClient q= getInstancePlay(packet.getData());
       
        while(true){
         packet = new DatagramPacket(buf, buf.length);
         

        socket.receive(packet);
       q.playRealTimeAudio(packet.getData(),bufferSize);
  //      out.write(packet.getData());
   //  playAudio(packet.getData());  //ovo radi
    i++;
         }
     //   playAudio();
    //    System.out.println("greskaaa");
        // display response
       // String received = new String(packet.getData(), 0, packet.getLength());
      //  System.out.println("Quote of the Moment: " + received);
 }catch(Exception ex){
     System.out.println("greska");
    
 }
        
      //  socket.close();
    }
    
     
    private static void playAudio() {
    try {
      byte audio[] = out.toByteArray();
      InputStream input = 
        new ByteArrayInputStream(audio);
      final AudioFormat format = getFormat();
      final AudioInputStream ais = 
        new AudioInputStream(input, format, 
        audio.length / format.getFrameSize());
      DataLine.Info info = new DataLine.Info(
        SourceDataLine.class, format);
      final SourceDataLine line = (SourceDataLine)
        AudioSystem.getLine(info);
      line.open(format);
      line.start();

      Runnable runner = new Runnable() {
        int bufferSize = (int) format.getSampleRate() 
          * format.getFrameSize();
        byte buffer[] = new byte[bufferSize];
 
        public void run() {
          try {
            int count;
            while ((count = ais.read(
                buffer, 0, buffer.length)) != -1) {
              if (count > 0) {
                line.write(buffer, 0, count);
                
              }
            }
            line.drain();
            line.close();
          } catch (IOException e) {
            System.err.println("I/O problems: " + e);
            System.exit(-3);
          }
        }
      };
      Thread playThread = new Thread(runner);
      playThread.start();
    } catch (LineUnavailableException e) {
      System.err.println("Line unavailable: " + e);
      System.exit(-4);
    } 
  }

 //----------------------------------------------------
    
    
      public QuoteClient(byte audio[]) throws LineUnavailableException{   //konstruktor sa inicalizacijom play
        format = getFormat();
       this.audio=audio;
       input = new ByteArrayInputStream(audio);
        ais = new AudioInputStream(input, format,audio.length / format.getFrameSize());
       info = new DataLine.Info(SourceDataLine.class, format);
       line = (SourceDataLine)AudioSystem.getLine(info);
       line.open(format);
      line.start();
     
    }
    
    public static QuoteClient getInstancePlay(byte audio[]) throws LineUnavailableException{
   
      if(instance == null) {
         instance = new QuoteClient(audio);
      }
      return instance;
    }
    
        private  void playRealTimeAudio( byte buffer[],int bufferSize) {
                line.write(buffer, 0, bufferSize );
  }
    
    
    
    
    
  private static AudioFormat getFormat() {
    float sampleRate = 8000;
    int sampleSizeInBits = 8;
    int channels = 1;
    boolean signed = true;
    boolean bigEndian = true;
    return new AudioFormat(sampleRate, 
      sampleSizeInBits, channels, signed, bigEndian);
  }
    
    
}
