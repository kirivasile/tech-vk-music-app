package technotrack.musicadviser.player;

import java.lang.ref.WeakReference;

public class PlayerPresenter implements IPlayerPresenter {
    private WeakReference<IPlayerView> mPlayerView;

    public PlayerPresenter(IPlayerView playerView) {
        mPlayerView = new WeakReference<>(playerView);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAudioPlay() {

    }

    @Override
    public void onAudioPause() {

    }

    @Override
    public void onAudioStop() {

    }

    @Override
    public void onAudioNext() {

    }

    @Override
    public void onAudioPrevious() {

    }
}
