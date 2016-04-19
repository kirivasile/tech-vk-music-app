package technotrack.musicadviser.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import java.lang.ref.WeakReference;

public class RemoteControlReceiver extends BroadcastReceiver {
    private WeakReference<IPlayerPresenter> mPlayerPresenterWeak;

    public void setPresenter(IPlayerPresenter playerPresenter) {
        mPlayerPresenterWeak = new WeakReference<>(playerPresenter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())
                && mPlayerPresenterWeak.get() != null) {

            KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                    mPlayerPresenterWeak.get().onAudioPlay();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                    mPlayerPresenterWeak.get().onAudioPause();
                    break;
                case KeyEvent.KEYCODE_MEDIA_STOP:
                    mPlayerPresenterWeak.get().onAudioStop();
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    mPlayerPresenterWeak.get().onAudioNext();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    mPlayerPresenterWeak.get().onAudioPrevious();
                    break;
            }
        }
    }
}
