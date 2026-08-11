import java.util.ArrayList;
import java.util.List;

class Artist {
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Song {
    private String title;
    private Artist artist;
    private int duration;

    public Song(String title, Artist artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() { return title; }
    public Artist getArtist() { return artist; }
    public int getDuration() { return duration; }

    @Override
    public String toString() {
        return title + " by " + artist.getName() + " (" + duration + "s)";
    }
}

class Playlist {
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public int getSongCount() { return songs.size(); }

    public int getTotalDuration() {
        int total = 0;
        for (Song song : songs) {
            total += song.getDuration();
        }
        return total;
    }

    public String getName() { return name; }
    public List<Song> getSongs() { return songs; }
}

class User {
    private String name;
    private List<Playlist> playlists = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public Playlist createPlaylist(String playlistName) {
        Playlist playlist = new Playlist(playlistName);
        playlists.add(playlist);
        return playlist;
    }

    public void deletePlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    public String getName() { return name; }
    public List<Playlist> getPlaylists() { return playlists; }
}

class Library {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public int getSongCount() { return songs.size(); }
    public List<Song> getSongs() { return songs; }
}

public class MusicLibrarySystem {
    public static void main(String[] args) {
        // Create artists and songs (independent of any playlist)
        Artist coldplay = new Artist("Coldplay");
        Artist adele = new Artist("Adele");

        Song yellow = new Song("Yellow", coldplay, 269);
        Song clocks = new Song("Clocks", coldplay, 307);
        Song hello = new Song("Hello", adele, 295);
        Song someone = new Song("Someone Like You", adele, 285);

        // Add all songs to the master library
        Library library = new Library();
        library.addSong(yellow);
        library.addSong(clocks);
        library.addSong(hello);
        library.addSong(someone);

        // User creates playlists and adds songs
        User alice = new User("Alice");
        Playlist workout = alice.createPlaylist("Workout Mix");
        Playlist chill = alice.createPlaylist("Chill Vibes");

        // Same songs shared across playlists
        workout.addSong(yellow);
        workout.addSong(clocks);
        workout.addSong(hello);

        chill.addSong(hello);
        chill.addSong(someone);

        System.out.println("Library has " + library.getSongCount() + " songs");
        System.out.println();

        System.out.println(workout.getName() + " (" + workout.getSongCount() + " songs, "
            + workout.getTotalDuration() + "s):");
        for (Song s : workout.getSongs()) {
            System.out.println("  - " + s);
        }
        System.out.println();

        System.out.println(chill.getName() + " (" + chill.getSongCount() + " songs, "
            + chill.getTotalDuration() + "s):");
        for (Song s : chill.getSongs()) {
            System.out.println("  - " + s);
        }
        System.out.println();

        // Delete a playlist - songs survive
        alice.deletePlaylist(workout);
        System.out.println("After deleting '" + workout.getName() + "':");
        System.out.println("  Library still has " + library.getSongCount() + " songs");
        System.out.println("  '" + chill.getName() + "' still has " + chill.getSongCount() + " songs");
        System.out.println("  'Yellow' still exists: " + yellow.getTitle());
    }
}
