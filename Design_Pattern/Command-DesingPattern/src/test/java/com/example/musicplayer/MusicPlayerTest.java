package com.example.musicplayer;

import com.example.exception.EndOfTracksException;
import org.junit.jupiter.api.Test;

import com.example.entity.State;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {

    @Test
    void testMusicPlayerBasicFlow() {
        List<String> tracks = List.of("Song A", "Song B", "Song C");
        MusicPlayer player = new MusicPlayer(tracks);

        // Initially paused
        assertEquals(State.PAUSED, player.state);

        // Play and pause toggle
        player.playOrPause(); // Play Song A
        assertEquals(State.PLAYING, player.state);
        player.playOrPause(); // Pause Song A
        assertEquals(State.PAUSED, player.state);

        // Stop when paused
        player.stop();
        assertEquals(State.PAUSED, player.state);
    }

    @Test
    void testNextAndPreviousTracks() {
        List<String> tracks = List.of("Song A", "Song B", "Song C");
        MusicPlayer player = new MusicPlayer(tracks);

        // Next from 0 → 1
        player.next();
        assertEquals(1, player.trackNumber);

        // Next from 1 → 2
        player.next();
        assertEquals(2, player.trackNumber);

        // Try next at last track without repeat → should replay first
        player.next();
        assertEquals(0, player.trackNumber);

        // Try previous at first → should stay at 0
        player.previous();
        assertEquals(0, player.trackNumber);

        // Move next then previous → should go back
        player.next();
        assertEquals(1, player.trackNumber);
        player.previous();
        assertEquals(0, player.trackNumber);
    }

    @Test
    void testRepeatModeBehavior() {
        List<String> tracks = List.of("Song A", "Song B", "Song C");
        MusicPlayer player = new MusicPlayer(tracks);

        // Enable repeat
        player.repeatOnOrOff();
        assertTrue(player.repeat);

        // Go to last track
        player.next();
        player.next();

        // Next from last track when repeat = true → should throw exception
        assertThrows(EndOfTracksException.class, player::next);

        // Disable repeat
        player.repeatOnOrOff();
        assertFalse(player.repeat);
    }

    @Test
    void testStopFunctionality() {
        List<String> tracks = List.of("Song A", "Song B");
        MusicPlayer player = new MusicPlayer(tracks);

        // Start playing
        player.playOrPause();
        assertEquals(State.PLAYING, player.state);

        // Stop
        player.stop();
        assertEquals(State.PAUSED, player.state);
    }

    @Test
    void testInvalidEmptyTrackList() {
        assertThrows(IllegalArgumentException.class, () -> new MusicPlayer(new ArrayList<>()));
    }

    @Test
    void testSequentialPlayNavigation() {
        List<String> tracks = List.of("Track 1", "Track 2", "Track 3", "Track 4");
        MusicPlayer player = new MusicPlayer(tracks);

        // Play through all tracks
        for (int i = 0; i < 3; i++) {
            player.next();
            assertTrue(player.trackNumber >= 0 && player.trackNumber < tracks.size());
        }

        // Try previous repeatedly
        for (int i = 0; i < 4; i++) {
            player.previous();
            assertTrue(player.trackNumber >= 0 && player.trackNumber < tracks.size());
        }
    }

    @Test
    void testRepeatToggle() {
        List<String> tracks = List.of("A", "B");
        MusicPlayer player = new MusicPlayer(tracks);

        // Toggle repeat multiple times
        assertFalse(player.repeat);
        player.repeatOnOrOff();
        assertTrue(player.repeat);
        player.repeatOnOrOff();
        assertFalse(player.repeat);
    }

    @Test
    void testMultiplePlayPauseAndStop() {
        List<String> tracks = List.of("X", "Y");
        MusicPlayer player = new MusicPlayer(tracks);

        player.playOrPause(); // play
        assertEquals(State.PLAYING, player.state);

        player.playOrPause(); // pause
        assertEquals(State.PAUSED, player.state);

        player.stop(); // stop again (no effect)
        assertEquals(State.PAUSED, player.state);
    }
}
