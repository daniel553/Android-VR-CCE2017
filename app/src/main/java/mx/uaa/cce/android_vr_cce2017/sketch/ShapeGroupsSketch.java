package mx.uaa.cce.android_vr_cce2017.sketch;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * Shapes and grid sketch
 * <p>
 * Created by Daniel on 06/09/2017.
 */

public class ShapeGroupsSketch extends PApplet {

    private PShape grid;
    private PShape cubes;

    @Override
    public void settings() {
        fullScreen(STEREO);
    }

    @Override
    public void setup() {
        //Create a grid
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

        //Create the group of cubes!!!
        cubes = createShape(GROUP);
        for (int i = 0; i < 100; i++) {
            cubes.addChild(getCube());
        }

    }

    /**
     * Builds a Cube with random dimens
     *
     * @return a cube
     */
    private PShape getCube() {
        float x = random(-1000, 1000);
        float y = random(-1000, 1000);
        float z = random(-1000, 1000);
        float r = random(50, 150);
        PShape cube = createShape(BOX, r, r, r);
        cube.setStroke(false);
        //Fun
        cube.setFill(color(random(255), random(255), random(255)));
        cube.translate(x, y, z);
        return cube;
    }

    @Override
    public void draw() {
        background(0);
        lights();
        translate(width / 2, height / 2, -200);
        shape(cubes);
        shape(grid);
    }
}
