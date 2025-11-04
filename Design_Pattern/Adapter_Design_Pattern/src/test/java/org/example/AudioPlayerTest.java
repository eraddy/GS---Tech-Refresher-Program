package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AudioPlayerTest {
    @Test
    void testMp3Playback() {
        AudioPlayer player = new AudioPlayer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        player.play("mp3", "track.mp3");
        assertTrue(out.toString().contains("mp3 file"), "Should play mp3 files correctly");
    }

    @Test
    void testMp4Playback() {
        AudioPlayer player = new AudioPlayer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        player.play("mp4", "movie.mp4");
        assertTrue(out.toString().contains("mp4 file"), "Should play mp4 files via adapter");
    }

    @Test
    void testVlcPlayback() {
        AudioPlayer player = new AudioPlayer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        player.play("vlc", "video.vlc");
        assertTrue(out.toString().contains("vlc file"), "Should play vlc files via adapter");
    }

    @Test
    void testInvalidFormat() {
        AudioPlayer player = new AudioPlayer();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        player.play("avi", "unsupported.avi");
        assertTrue(out.toString().contains("Invalid media format"), "Should show invalid format error");
    }
}
