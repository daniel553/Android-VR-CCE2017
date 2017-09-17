package mx.uaa.cce.android_vr_cce2017.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

import mx.uaa.cce.android_vr_cce2017.R;

public class AudioService extends Service {
    private final IBinder mBinder = new ServiceBinder();
    private MediaPlayer mMediaPlayer;

    public AudioService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        playShootingStars();
    }

    @Override
    public void onLowMemory() {
        if (mMediaPlayer != null)
            mMediaPlayer.stop();
        super.onLowMemory();
    }

    /**
     * Plays tututututut
     */
    private void playShootingStars() {
        try {
            mMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.shooting_stars);
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays song
     */
    public void play() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying())
            mMediaPlayer.start();
    }

    public void stop() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
            mMediaPlayer.stop();
    }

    /**
     * To bind service to main activity
     */
    public class ServiceBinder extends Binder {
        public AudioService getAudioService() {
            return AudioService.this;
        }
    }
}
