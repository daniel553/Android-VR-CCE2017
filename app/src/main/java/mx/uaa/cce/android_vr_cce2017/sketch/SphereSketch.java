package mx.uaa.cce.android_vr_cce2017.sketch;

import processing.core.PApplet;
import processing.opengl.PShader;

/**
 * A sphere
 * Created by Daniel on 06/09/2017.
 */

public class SphereSketch extends PApplet {

    private PShader toon;
    private boolean enabled = true;

    @Override
    public void settings() {
        fullScreen(STEREO);
    }

    @Override
    public void setup() {
        noStroke();
        fill(200);
        //Shader of a sphere
        toon = loadShader("shaders/toonfrag.glsl", "shaders/toonvert.glsl");
    }

    @Override
    public void draw() {
        if (enabled)
            shader(toon);
        translate(width / 2, height / 2, -200);
        background(80);
        directionalLight(204, 204, 204, 1, 1, -1);
        sphere(100);
    }

    @Override
    public void mousePressed() {
        //Set or remove shader
        enabled = !enabled;
        resetShader();
    }
}
