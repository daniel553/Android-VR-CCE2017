package mx.uaa.cce.android_vr_cce2017;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import mx.uaa.cce.android_vr_cce2017.sketch.CubeSketch;
import mx.uaa.cce.android_vr_cce2017.sketch.TestVR;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import processing.core.PApplet;
import processing.vr.PVR;

/**
 * Processing VR activity class.
 */
@RuntimePermissions
public class MainActivity extends PVR {

    private PApplet sketch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSketch();
            }
        });
    }

    /**
     * Sets and shows a sketch
     * sketch = new TestVR();
     * setSketch(sketch);
     */
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showSketch() {
        sketch = new CubeSketch();
        setSketch(sketch);
    }
}
