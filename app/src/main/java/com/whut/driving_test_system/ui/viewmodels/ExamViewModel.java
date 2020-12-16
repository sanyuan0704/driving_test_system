package com.whut.driving_test_system.ui.viewmodels;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.models.eneities.Examinee;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExamViewModel extends ViewModel {
    public MutableLiveData<Examinee> examinee;

    public ExamViewModel() {
        this.examinee = new MutableLiveData<>();
    }

    /**
     * 起步按钮函数
     * @param v
     */
    public void onStartExam(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.start);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 左转按钮函数
     * @param v
     */
    public void onTurnLeft(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.turn_left);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 右转按钮函数
     * @param v
     */
    public void onTurnRight(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.turn_right);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 超车按钮函数
     * @param v
     */
    public void onOvertaking(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.overtaking);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 掉头按钮函数
     * @param v
     */
    public void onTurnAround(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.turn_around);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 变更车道按钮函数
     * @param v
     */
    public void onChangeLane(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.change_lane);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 靠边停车按钮函数
     * @param v
     */
    public void onStop(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.stop);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 重新起步按钮函数
     * @param v
     */
    public void onRestart(View v){
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.restart);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
    }

    /**
     * 结束考试按钮函数
     * @param v
     */
    public void onEnd(View v){

    }
}
