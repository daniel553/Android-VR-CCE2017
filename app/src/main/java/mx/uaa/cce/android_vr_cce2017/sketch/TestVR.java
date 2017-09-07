package mx.uaa.cce.android_vr_cce2017.sketch;

import processing.core.PApplet;
import processing.vr.PGraphicsVR;
import processing.vr.PVR;

/**
 * Test pvr
 * <p>
 * Created by Daniel on 04/09/2017.
 */

public class TestVR extends PApplet {

    @Override
    public void settings() {
        fullScreen(STEREO);
    }

    @Override
    public void draw() {
        PGraphicsVR pvr = (PGraphicsVR) g;
        if (pvr.eyeType == PVR.LEFT) {
            background(200, 50, 50);
        } else if (pvr.eyeType == PVR.RIGHT) {
            background(50, 50, 200);
        } else if (pvr.eyeType == PVR.MONOCULAR) {
            background(50, 200, 50);
        }
    }
}
