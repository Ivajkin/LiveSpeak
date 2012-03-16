/*

 
*/

import JMyron.*;
import processing.net.*;

//a camera object
JMyron m;

final int cameraW = 640, cameraH = 480;

Server s;
Client c;

void setup(){
  size(1280,1024);
  m = new JMyron();//make a new instance of the object
  m.start(cameraW, cameraH);//start a capture at 320x240
  m.findGlobs(0);//disable the glob tracking to speed up frame rate
  println("Myron " + m.version());
  
  s = new Server(this, 7865);
  c = new Client(this, "127.0.0.1", 7865);
}

void draw(){
  m.update();//update the camera view
  int[] img = m.image(); //get the normal image of the camera
  String[] arr = new String[img.length];
  for(int i = 0; i < arr.length; ++i) {
    arr[i] = "" + img[i];
  }
  String sendMessage = join(arr, ",");
  println("sendMessage:" + sendMessage);
  c.write(sendMessage);
  s.write(sendMessage);
  c = s.available();
  if (c != null) {
    String input = c.readString();
    println("Строка от клиента: " + input);
    //input = input.substring(0, input.indexOf("\n")); // Only up to the newline
    // Изображение полученное от клиента.
    final int[] data = int(split(input, ',')); // Split values into an array
    
    loadPixels();
    for(int i = 0; i < width-4; ++i) {
      for(int j = 0; j < height-4; ++j) {
        final int index = i + j*width;
        final float aspectX = (float) cameraW / width;
        final float aspectY = (float) cameraH / height;
        final int small_index = (int)Math.round(Math.floor(i*aspectX) + Math.floor(j*aspectY)*cameraW);
        
        float r = red(data[small_index]) + red(data[small_index+1]);
        float g = green(data[small_index]) + green(data[small_index+1]);
        float b = blue(data[small_index]) + blue(data[small_index+1]);
        pixels[index] = color(r*0.5f,g*0.5f,b*0.5f); //draw each pixel to the screen
      }
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

void mousePressed(){
  m.settings();//click the window to get the settings
}

public void stop(){
  m.stop();//stop the object
  super.stop();
}

