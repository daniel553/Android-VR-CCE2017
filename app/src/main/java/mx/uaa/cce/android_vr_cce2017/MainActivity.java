package mx.uaa.cce.android_vr_cce2017;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;

import mx.uaa.cce.android_vr_cce2017.service.AudioService;
import mx.uaa.cce.android_vr_cce2017.sketch.ShootingStarsSketch;
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
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                View v = findViewById(R.id.button);
                if (v != null)
                    v.performClick();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void onStop() {
        if (mService != null)
            mService.stop();
        super.onStop();
    }

    /**
     * Sets and shows a sketch
     * sketch = new TestVR();
     * setSketch(sketch);
     */
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showSketch() {
        sketch = new ShootingStarsSketch();
        setSketch(sketch);
        startAudioService();
    }

    /**
     * Starts audio service to play shooting stars
     */
    private void startAudioService() {
        Intent intent = new Intent(this, AudioService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private AudioService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioService.ServiceBinder binder = (AudioService.ServiceBinder) service;
            mService = binder.getAudioService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

}
