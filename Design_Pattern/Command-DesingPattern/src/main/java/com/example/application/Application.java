package com.example.application;

import com.example.exception.EndOfTracksException;
import com.example.musicplayer.MusicPlayer;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> musicTracks = new ArrayList<>();
        musicTracks.add("Shape of You");
        musicTracks.add("Blinding Lights");
        musicTracks.add("Levitating");
        musicTracks.add("Perfect");
        musicTracks.add("Someone Like You");
        musicTracks.add("Believer");
        musicTracks.add("Uptown Funk");
        musicTracks.add("Stay");
        musicTracks.add("Peaches");
        musicTracks.add("Senorita");
        MusicPlayer musicPlayer = new MusicPlayer(musicTracks);

        System.out.println("🎧 Welcome to the Music Player Demo \n");

        // Step 3: Play the first track
        musicPlayer.playOrPause();

        // Step 4: Pause the track
        musicPlayer.playOrPause();

        // Step 5: Play again
        musicPlayer.playOrPause();

        // Step 6: Move to next track
        musicPlayer.next();

        // Step 7: Move to next few tracks
        musicPlayer.next();
        musicPlayer.next();

        // Step 8: Go back to the previous track
        musicPlayer.previous();

        // Step 9: Stop the music
        musicPlayer.stop();

        // Step 10: Turn repeat ON
        musicPlayer.repeatOnOrOff();

        // Step 11: Try moving next until end (to trigger repeat behavior)
        for (int i = 0; i < musicTracks.size(); i++) {
            try {
                musicPlayer.next();
            } catch (EndOfTracksException e) {
                System.out.println(e.getMessage());
            }
        }

        // Step 12: Turn repeat OFF
        musicPlayer.repeatOnOrOff();

        // Step 13: Try next at end again — should replay from first
        for (int i = 0; i < musicTracks.size(); i++) {
            musicPlayer.next();
        }

        // Step 14: Try going previous from first track
        musicPlayer.previous();

        // Step 15: Stop at the end
        musicPlayer.stop();

        System.out.println("\n Music player demo complete!");


    }
}
