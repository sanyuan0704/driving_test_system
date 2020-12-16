package com.whut.driving_test_system.ui.viewmodels;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;

import com.whut.driving_test_system.R;

import androidx.lifecycle.ViewModel;

public class ExamViewModel extends ViewModel {

    public void onStartExam(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.start);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

}
