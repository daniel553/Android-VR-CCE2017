package mx.uaa.cce.android_vr_cce2017.sketch;

import android.util.Log;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

/**
 * Earth texture
 * Created by Daniel on 06/09/2017.
 */

public class EarthSketch extends PApplet {

    private PImage earth;
    private PShape earthShape;
    private int r = 0;

    @Override
    public void settings() {
        fullScreen(STEREO);
    }

    @Override
    public void setup() {
        earth = loadImage("earth.jpg");
        earthShape = loadShape("earth.obj");
        noSmooth();
    }


    @Override
    public void draw() {
        background(0);
        translate(width / 2, height / 2, -400);
        rotateY(radians(r++));
        shape(earthShape);
        if (r > 360)
            r = 0;
    }

}