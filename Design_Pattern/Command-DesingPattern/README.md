# Command Design Pattern

Implement a Music Player.
Please implement a class for the following interface:
interface Player {
void playOrPause();
void stop();
void next();
void previous();
void repeatOnOrOff();
}
The interface imitates a set of buttons of a software or hardware player. Play/pause button should start or pause playback, depending on what the player is doing. Stop button should stop playback, so if Play button is pressed, the current track starts from the beginning. Next button should skip the current track to the next one, if available. It also should consider if repeat is enabled or disabled. Show warning messages if stop is pressed when track is already stopped, or next is pressed, and the player is already on the last track.
The constructor of implementation class should accept a list of track names (List).
Actions:

Pressing the "Play" button should display the message: "Playing: [current track name]".
Pressing the "Stop" button should display the message: "Stopping: [current track name]" or "Already stopped" (as appropriate).
Pressing the "Next" button should display the message: "Playing next track: [name of the next track]" or "Replaying first track: [name of the first track]" or "Already on the last track" (as appropriate).
Pressing the "Previous" button should display the message: "Playing previous track: [name of the previous track]" or "Already on the first track" (as appropriate).
Pressing the "Repeat" button should display the message: "Repeat on" or "Repeat off" (depending on the situation).

Scenarion example:
Skip to next track:

Pressing the "Next" button.
If there is a next track, it will play and display the message "Playing next track: [name of next track]".
If the current track is the last one and repeat is enabled, the first track is played and the message "Replaying first track: [name of the first track]" is displayed.
If the current track is the last one and repeat is turned off, the message "Already on the last track" is displayed.