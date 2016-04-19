package technotrack.musicadviser.player;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import technotrack.musicadviser.R;

public class PlayerActivity extends AppCompatActivity implements IPlayerView,
        AudioManager.OnAudioFocusChangeListener {

    private static final String TAG = "PlayerActivity";

    private RemoteControlReceiver mRemoteControlReceiver;
    private ComponentName mComponentName;
    private PlayerPresenter mPlayerPresenter;
    private AudioManager mAudioManager;

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                if (mComponentName == null) {
                    mComponentName = new ComponentName(getPackageName(),
                            RemoteControlReceiver.class.getName());
                    mAudioManager.registerMediaButtonEventReceiver(mComponentName);
                }
                //resume or raise volume
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
                mAudioManager.abandonAudioFocus(this);
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                //pause
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                //Lower the volume
                break;
        }
    }

    private boolean requestAudioFocus() {
        int result = mAudioManager.requestAudioFocus(this,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN);

        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_player);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        mPlayerPresenter = new PlayerPresenter(this);
        mRemoteControlReceiver = new RemoteControlReceiver(mPlayerPresenter);

        mAudioManager = (AudioManager) getApplicationContext()
                .getSystemService(Context.AUDIO_SERVICE);

        requestAudioFocus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

        mPlayerPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        mPlayerPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

        mPlayerPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

        mPlayerPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void showTrackInfo() {

    }
}
