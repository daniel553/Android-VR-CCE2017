package mx.uaa.cce.android_vr_cce2017.sketch;

import processing.core.PApplet;

/**
 * Displays a 3d cube
 *
 * Created by Daniel on 06/09/2017.
 */

public class CubeSketch extends PApplet {
    @Override
    public void settings() {
        fullScreen(STEREO);
    }

    @Override
    public void draw() {
        background(150);
        lights();
        translate(width/2, height/2, -200);
        rotateX(frameCount * 0.01f);
        rotateY(frameCount * 0.01f);
        box(200);
    }
}
