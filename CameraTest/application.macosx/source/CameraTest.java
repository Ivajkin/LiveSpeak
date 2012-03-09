import processing.core.*; 
import processing.xml.*; 

import JMyron.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class CameraTest extends PApplet {

/*
  very basic example that draws what the camera sees, except the colors are inverted.

 last tested to work in Processing 0090
 
 JTNIMOY
 
*/



JMyron m;//a camera object

final int cameraW = 640, cameraH = 480;

public void setup(){
  size(1280,1024);
  m = new JMyron();//make a new instance of the object
  m.start(cameraW, cameraH);//start a capture at 320x240
  m.findGlobs(0);//disable the glob tracking to speed up frame rate
  println("Myron " + m.version());
}

public void draw(){
  m.update();//update the camera view
  int[] img = m.image(); //get the normal image of the camera
  loadPixels();
  for(int i = 0; i < width-4; ++i) {
    for(int j = 0; j < height-4; ++j) {
      final int index = i + j*width;
      final float aspectX = (float) cameraW / width;
      final float aspectY = (float) cameraH / height;
      final int small_index = (int)Math.round(Math.floor(i*aspectX) + Math.floor(j*aspectY)*cameraW);
      
      float r = red(img[small_index]) + red(img[small_index+1]);
      float g = green(img[small_index]) + green(img[small_index+1]);
      float b = blue(img[small_index]) + blue(img[small_index+1]);
      pixels[index] = color(r*0.5f,g*0.5f,b*0.5f); //draw each pixel to the screen
    }
  }
  /*for(int i=0;i<width*height;i+=2){ //loop through all the pixels
  
      // in order to invert a color value, subtract it from the maximum.
      r = red(img[i/2]); 
      g = green(img[i/2]);
      b = blue(img[i/2]);
      
      pixels[i] = color(r,g,b); //draw each pixel to the screen
      
  }*/
  updatePixels();
}

public void mousePressed(){
  m.settings();//click the window to get the settings
}

public void stop(){
  m.stop();//stop the object
  super.stop();
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "CameraTest" });
  }
}
