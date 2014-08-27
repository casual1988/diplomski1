/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package Video;

import java.awt.BorderLayout;
import java.util.Vector;
import javax.media.MediaLocator;
import javax.media.*;
import javax.media.format.RGBFormat;
import javax.media.protocol.*;
import javax.swing.JFrame;
/**
 *
 * @author Aleksandar
 */
public class Kamera {
    
    Player web_cam_player;
  static  CaptureDeviceInfo cam;
MediaLocator locator;
Player player;
//FormatControl formatControl;
    public static void main(String[] args) {
        
         Kamera ourApp = new Kamera();  
   ourApp.getCameraApp();  
    
   
    
       try{
           
             Vector deviceList = CaptureDeviceManager.getDeviceList(new RGBFormat());
            System.out.println(deviceList.toString());
                    // List out available Devices to Capture Video.
        Vector<CaptureDeviceInfo> list = CaptureDeviceManager.getDeviceList ( null );
                    System.out.println(list);
        // Iterating list
        for(CaptureDeviceInfo temp : list){
            // Checking whether the current device supports VfW
            // VfW = Video for Windows
                        if(temp.getName().startsWith("vfw:"))
                        {
            System.out.println("Found : "+temp.getName().substring(4));
            // Selecting the very first device that supports VfW
            cam = temp;
            System.out.println("Selected : "+cam.getName().substring(4));
            break;
                        }
        }
   }catch(Exception ex){}  }
  
   public void getCameraApp(){  
   try{  
  
     /** 
     * get the system webcam media, the protool "vfw://0" worked well with windows 7 
     * but some times take a complete restart to reconnect to native camera source 
     **/ 
       String str1 = "vfw:Logitech USB Video Camera:0";
        String str2 = "vfw:Microsoft WDM Image Capture (Win32):0";
    // MediaLocator ml = new MediaLocator("vfw://0");  
     MediaLocator ml = new MediaLocator(str2);  
     // Create the data source  from which we will create our cam player  
     DataSource dataSource = Manager.createDataSource(ml);  
      
      
     //Now we create our camera data from the source & start the player  
     web_cam_player = Manager.createRealizedPlayer(dataSource);  
     web_cam_player.start();  
      
     //Lets have a a second delay as we dinamically grab the frames  
     Thread.currentThread().sleep(1000);  
      
     //Build the webcam visual frame, set the size & release the window on close  
     buildCameraFrame();  
      
   }catch(Exception e){  
       System.out.println("Unable to open the camera source. " + e.getMessage());  
       }  
   }   
   private void buildCameraFrame() {  
       JFrame cam_frame=new JFrame("Webcam");  
       cam_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
       //Do we have the media visual controls?  
       if(web_cam_player.getVisualComponent()!=null){  
          
       //Add the visual control to the component  
       cam_frame.getContentPane().add(web_cam_player.getVisualComponent());  
    }  
    if(web_cam_player.getControlPanelComponent()!=null){  
       cam_frame.getContentPane().add(web_cam_player  
         .getControlPanelComponent(), BorderLayout.SOUTH);  
       cam_frame.pack();  
       cam_frame.setLocationRelativeTo(null);  
       cam_frame.setVisible(true);  
       cam_frame.setSize(320,240);  
    }  
} 
        
 //  Webcam cam= new Webcam().getDefault();
    }

