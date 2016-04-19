package technotrack.musicadviser.player;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import technotrack.musicadviser.R;

public class PlayerActivity extends AppCompatActivity implements IPlayerView {
    private RemoteControlReceiver mRemoteControlReceiver;
    private ComponentName mComponentName;
    private PlayerPresenter mPlayerPresenter;
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mAudioManager = (AudioManager) getApplicationContext()
                .getSystemService(Context.AUDIO_SERVICE);

        mPlayerPresenter = new PlayerPresenter(this);
        mRemoteControlReceiver = new RemoteControlReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mComponentName = new ComponentName(getPackageName(),
                RemoteControlReceiver.class.getName());
        mAudioManager.registerMediaButtonEventReceiver(mComponentName);
        mRemoteControlReceiver.setPresenter(mPlayerPresenter);

        mPlayerPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPlayerPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mPlayerPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);

        mPlayerPresenter.onStop();
    }

    @Override
    public void showTrackInfo() {

    }
}
