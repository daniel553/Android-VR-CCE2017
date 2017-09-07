package mx.uaa.cce.android_vr_cce2017.sketch;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * System grid display
 * Created by Daniel on 06/09/2017.
 */

public class GridSketch extends PApplet {

    private PShape grid;

    @Override
    public void settings() {
        fullScreen(STEREO);
    }

    @Override
    public void setup() {
        grid = createShape();
        grid.beginShape(LINES);
        grid.stroke(255);

        for (int x = -10000; x < 10000; x += 250) {
            //.vertex(x, y, z)
            grid.vertex(x, 1000, 10000);
            grid.vertex(x, 1000, -10000);
        }
        for (int z = -10000; z < 10000; z += 250) {
            grid.vertex(10000, 1000, z);
            grid.vertex(-10000, 1000, z);
        }

        grid.endShape();
    }

    int r = 0;

    @Override
    public void draw() {
        background(0);
        translate(width / 2, height / 2);
        shape(grid);

        //Hello world
        fill(255);
        textSize(150);
        rotateY(radians(r));
        text("Hola mundo", 0, height / 3, -200);

        r = r < 360 ? r + 1 : 0;
    }
}
