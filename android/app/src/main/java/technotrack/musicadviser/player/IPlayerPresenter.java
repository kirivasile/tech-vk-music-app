package technotrack.musicadviser.player;

public interface IPlayerPresenter {
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();

    void onAudioPlay();
    void onAudioPause();
    void onAudioStop();
    void onAudioNext();
    void onAudioPrevious();
}
