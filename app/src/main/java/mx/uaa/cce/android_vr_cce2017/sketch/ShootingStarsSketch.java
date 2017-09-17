package mx.uaa.cce.android_vr_cce2017.sketch;

import java.util.ArrayList;
import java.util.List;

import mx.uaa.cce.android_vr_cce2017.sensor.AccelerometerManager;
import processing.core.PApplet;

/**
 * Tu ru tu tu tu
 * Created by Daniel on 07/09/2017.
 */

public class ShootingStarsSketch extends PApplet {
    private static final String TAG = ShootingStarsSketch.class.getSimpleName();

    private AccelerometerManager accelerometerManager;
    float ax, ay, az;
    private StarField starField;
    private int r;

    @Override
    public void settings() {
        fullScreen(STEREO);
    }

    @Override
    public void setup() {
        starField = new StarField();
        accelerometerManager = new AccelerometerManager(this);
    }

    @Override
    public void draw() {

        //crazy background colors
        crazyBackgrounds();
        lights();


        beginCamera();
        camera();
        if (ay > 1)
            r++;
        else if (ay < -1)
            r--;
        if (r < 0 || r > 360)
            r = 0;
        rotateY(radians(r));
        endCamera();
        translate(width / 2, height / 2, -200);
        fill(250);
        box(45);

        translate(width / 2, height / 2);
        starField.update();
        starField.show();
    }

    /**
     * Generate a crazy background colors Careful with epilepsy
     */
    int gradientIterator = 0;

    private void crazyBackgrounds() {
        colorMode(HSB);
        gradientIterator = gradientIterator + 5;
        if (gradientIterator <= color(0, 0, 0) || gradientIterator == 0)
            gradientIterator = color(random(255), random(255), random(255));

        background(gradientIterator);
    }

    @Override
    public void resume() {
        super.resume();
        if (accelerometerManager != null)
            accelerometerManager.resume();
    }

    @Override
    public void onPause() {
        if (accelerometerManager != null)
            accelerometerManager.pause();
        super.onPause();
    }

    public void accelerationEvent(float x, float y, float z) {
        //Log.d(TAG, x + "," + y + "," + z);
        ax = x;
        ay = y;
        az = z;
        //redraw();
    }


    //-----------------------------
    // Objects

    class StarField {

        int maxStars = 30;
        List<Star> stars = new ArrayList<>(maxStars);

        public StarField() {
            init();
        }

        public void init() {
            for (int i = 0; i < maxStars; i++) {
                Star star = new Star();
                star.randomizeStar();
                stars.add(star);
            }
        }

        public void update() {
            for (Star s : stars) {
                if (s.isOut())
                    s.reset();
                s.move();
            }
        }

        public void show() {
            for (final Star s : stars) {
                //initial point
                pushMatrix();
                translate(0, 0, 0);
                smooth();
                //Go to specific start position
                translate(s.x, s.y, s.z);
                noStroke();
                fill(255);
                sphereDetail(2);
                sphere(s.size);
                popMatrix();
            }
        }
    }

    class Star {
        int x;
        int y;
        int z;
        int vel = 15;

        //To move
        int xOff;
        int yOff;
        float size;

        public Star() {
        }

        public Star getRandomStar() {
            Star star = new Star();
            star.randomizeStar();
            return star;
        }

        public void randomizeStar() {
            this.x = (int) random(100, width - 100); //X
            this.y = (int) random(100, height - 100); //Y
            this.z = (int) -500; //Z
            this.vel = (int) random(10, 20); //Vel

            this.xOff = (int) random(-5, 5);
            this.yOff = (int) random(-5, 5);

            //We dont want a 0
            if (this.xOff == 0)
                this.xOff = 5;
            if (this.yOff == 0)
                this.yOff = 5;

            this.size = (int) random(20, 50);
        }

        public void acelerate() {
            this.z = this.z + this.vel;
        }

        public void move() {
            this.x = this.x + xOff;
            this.y = this.y + yOff;
            acelerate();
        }

        public void reset() {
            randomizeStar();
        }

        public boolean isOut() {
            return abs(this.x) > width + 30 && abs(this.y) > height + 30;
        }

        @Override
        public String toString() {
            return "(" + this.x + "," + this.y + "," + this.z + ")";
        }
    }
}
