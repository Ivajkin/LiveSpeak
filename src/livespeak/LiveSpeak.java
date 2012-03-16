package livespeak;

import processing.core.PApplet;
import JMyron.*;

/**
 *
 * @author Tim
 */
@SuppressWarnings("serial")
public class LiveSpeak extends PApplet {

    JMyron m;

    @Override
    public void setup() {
        size(320, 240);
        m = new JMyron();//make a new instance of the object
        m.start(width, height);//start a capture at 320x240
        m.findGlobs(0);//disable the intelligence to speed up frame rate
        println("Myron " + m.version());
        rectMode(CENTER);
        noStroke();
    }

    @Override
    public void draw() {
        background(0);
        m.update();//update the camera view
        int[] img = m.image(); //get the normal image of the camera
        float r, g, b;
        for(int y = 0; y < height; y += 8) { //loop through all the pixels
            for(int x = 0; x < width; x += 8) { //loop through all the pixels
                float av = (float) ((red(img[y * width + x]) + green(img[y * width + x]) + blue(img[y * width + x])) / 3.0);
                fill(red(img[y * width + x]), green(img[y * width + x]), blue(img[y * width + x]));
                fill(255);

                pushMatrix();
                translate(x, y);
                rotate(av / 32.0f);
                rect(0, 0, av / 16.0f, av / 16.0f);
                popMatrix();
            }
        }
    }

    @Override
    public void stop() {
        m.stop();//stop the object
        super.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{livespeak.LiveSpeak.class.getName()});
    }
}
