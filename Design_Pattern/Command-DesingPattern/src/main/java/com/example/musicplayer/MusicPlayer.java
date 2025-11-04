package com.example.musicplayer;

import com.example.api.Player;
import com.example.entity.State;
import com.example.entity.Track;
import com.example.exception.EndOfTracksException;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer implements Player {

    List<Track> tracks = new ArrayList<>();
    State state = State.PAUSED;
    int trackNumber = 0;
    boolean repeat = false;

    public MusicPlayer(List<String> tracks) {
        if(tracks.isEmpty())
            throw new IllegalArgumentException("No tracks found to start the player");
        for(String name : tracks)
            this.tracks.add(new Track(name));
    }

    @Override
    public void playOrPause() {
        if(state == State.PAUSED) {
            System.out.println("Playing : " + tracks.get(trackNumber).getName());
            state = State.PLAYING;
        }
        else{
            System.out.println("Stopping : " + tracks.get(trackNumber).getName());
            state = State.PAUSED;
        }
    }

    @Override
    public void stop() {
           System.out.println("Stopping the track : " + tracks.get(trackNumber).getName());
           state = State.PAUSED;
    }

    @Override
    public void next() {
        if(trackNumber < tracks.size() - 1){
            System.out.println("Playing next track : " + tracks.get(++trackNumber).getName());
        }
        else
        {
            if(repeat)
                throw new EndOfTracksException("Already on the last track");
            else {
                trackNumber = 0;
                System.out.println("Replaying first track : " + tracks.get(trackNumber).getName());
            }
        }
    }

    @Override
    public void previous() {
        if(trackNumber == 0)
            System.out.println("Already on first track");
        else
            System.out.println("Playing previous track : " + tracks.get(--trackNumber).getName());
    }

    @Override
    public void repeatOnOrOff() {
        if(repeat) {
            System.out.println("Repeat off");
            repeat = false;
        }
        else{
            System.out.println("Repeat On");
            repeat = true;
        }
    }
}
