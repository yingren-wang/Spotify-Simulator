import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Run();
        System.out.println("Done");
    }

    private static void Run() {
        // dummy data to start
        Song mySong1 = new Song("DoYou", "Troyboi");
        Song mySong2 = new Song("Bleach", "Brockhampton");
        Song mySong3 = new Song("Pink+White", "FrankOcean");

        ArrayList<Playlist> allList = new ArrayList<>();

        ArrayList<Song> dList = new ArrayList<>();
        Playlist defaultlist = new Playlist(dList, "defaultlist");
        allList.add(defaultlist);

        defaultlist.addSong(mySong1);
        defaultlist.addSong(mySong2);
        defaultlist.addSong(mySong3);

        promptUser();
        Scanner scan = new Scanner(System.in);
        String userChoice = scan.next();
        String songName;
        String artist;
        String theList;
        int index;
        Song song;

        while (userChoice != "q") {
            switch (userChoice) {
                case "p":
                    String playlistName = scan.next();
                    ArrayList<Song> list = new ArrayList<>();
                    Playlist playlist = new Playlist(list, playlistName);
                    allList.add(playlist);
                    System.out.println("Your new playlist is called " + playlistName);
                    System.out.println("\nWhat do you want to do?");
                    break;
                    
                case "r":
                    System.out.println("Enter the name of the playlist you would like to randomize");
                    String userPlaylist = scan.next();
                    index = searchList(userPlaylist, allList);
                    if (index == -1)
                    {
                        System.out.println("There's no playlist called '" + userPlaylist + "', please double check\n");
                    }
                    else
                        {
                            List<Song> tempList = allList.get(index).getPlaylist();
                            Random rand = new Random();

                            for (int i = 0; i < tempList.size(); i++)
                            {
                                int randomIndexToSwap = rand.nextInt(tempList.size());
                                Song temp = Song.copySong(tempList.get(randomIndexToSwap));
                                tempList.set(randomIndexToSwap, Song.copySong(tempList.get(i)));
                                tempList.set(i, temp);

                            }
                    }
                    System.out.println("Your new playlist order is: " );
                    allList.get(index).printList();
                    System.out.println("\nWhat do you want to do?");
                    break;

                case "s":
                    songName = scan.next();
                    artist = scan.next();
                    theList = scan.next();
                    System.out.println(songName + " " + artist + " " + theList);
                    song = new Song(songName, artist);

                    index = searchList(theList, allList);
                    if (index == -1) {
                        System.out.println("There's no playlist called '" + theList + "', please double check\n");
                    } else {
                        allList.get(index).addSong(song);
                        allList.get(index).printList();
                    }
                    System.out.println("\nWhat do you want to do?");
                    break;
                    
                case "t":
                    playlistName = scan.next();
                    list = new ArrayList<>();
                    index = searchList(playlistName, allList);
                    //if (playlistName.equals(playlist.getListName()))
                    //{
                        //System.out.println("Your playlist deleted is " + playlistName);//-----
                    if (index == -1)
                    {
                        System.out.println("There's no playlist called '" + list + "' , please double check\n");
                    }
                    else
                    {
                        System.out.println("Playlist named '" +playlistName+ "' deleted");
                        allList.remove(index);
                    }
                    System.out.println("\nWhat do you want to do?");
                    break;

                case "d":
                    songName = scan.next();
                    artist = scan.next();
                    theList = scan.next();
                    song = new Song(songName, artist);

                    index = searchList(theList, allList);
                    if (index == -1) {
                        System.out.println("There's no playlist called '" + theList + "' , please double check\n");
                    } else {
                        allList.get(index).deleteSong(song);
                        allList.get(index).printList();
                        System.out.println("Song deleted");
                    }
                    System.out.println("\nWhat do you want to do?");
                    break;
                    
                case "n":
                {
                    System.out.println("Enter the name of the playlist you're in");
                    String userPlaylistName = scan.next();


                    index = searchList(userPlaylistName, allList);
                    if (index == -1)
                    {
                        System.out.println("There's no playlist called '" + userPlaylistName + "', please double check\n");
                    }
                    else
                    {
                        int i = 0;
                        Playlist presentPlaylist =  allList.get(index);
                        Song presentSong = presentPlaylist.getSong( i + 1);

                        System.out.println("Now playing " + presentSong.getName()+ " by "+presentSong.getArtist());
                        System.out.println("Successfully skipped!!\n\n");
                    }
                }
                System.out.println("\nWhat do you want to do?");
                break;
                    
                case "q":
                    System.out.println("Thank you for using the program!");
            }
            userChoice = scan.next();
        }
    }

    // search a list in all lists
    private static int searchList(String list, ArrayList<Playlist> alllist) {
        int result = -1;
        for(int i = 0; i < alllist.size(); i++) {
            if(alllist.get(i).getListName().equals(list)) {
                result = i; // return the index of the list in all lists
            }
        }
        return result;
    }

    private static void promptUser() {
        // prompt user to add a song
        System.out.println("Welcome to the program!\n"
                + "Now Playing " 
                + "What do you want to do?\n"
                + "Add a playlist:			p <playlist_name>\n"
                + "Add a song: 			s <song_name> <artist_name> <playlist_name>\n"
                + "Delete a song:			d <song_name> <artist_name> <playlist_name>\n"
                + "Randomize songs:    		r \n"
                + "Skip to the next song:   	n \n"
                + "Delete a playlist		t <playlist_name>\n"
                + "Quit the program:		q\n");
    }
}

class Song {
    String sName;
    String sArtist;

    // get functions for private members
    public String getName() {
        return sName;
    }
    public String getArtist() {
        return sArtist;
    }
    // set functions for private members
    public Song(String name, String artist) {
        this.sName = name;
        this.sArtist = artist;
    }
    public void printSong() {
        System.out.println("Name: " + this.sName);
        System.out.println("Artist: " + this.sArtist);
    }

    public static Song copySong(Song songIn) {
        Song newSong = new Song(songIn.getName(), songIn.getArtist());
        return newSong;
    }

}

class Playlist {
    private ArrayList<Song> playlist = new ArrayList<Song>();
    private Integer size;
    private String pName;

    public String getListName() {
        return pName;
    }
    public void addSong(Song song) {
        playlist.add(song);
        size = playlist.size();
    }
    public void deleteSong(Song song) {
	    for(int i = 0; i < size; i++) {
	        Song search = playlist.get(i);
	        if(search.getName().equals(song.getName()) && search.getArtist().equals(song.getArtist())) {
	            // found the song to delete
	            playlist.remove(i);
	            size = playlist.size();
	        }
	    }
    }
    public Song getSong(int i) {
        return playlist.get(i);
    }
    public Playlist(ArrayList<Song> list, String listName) {
        this.playlist = list;
        this.pName = listName;
    }
    public Integer getSize() {
        return size;
    }

    public ArrayList<Song> getPlaylist()
    {
        return playlist;
    }

    public void printList() {
        for(int i = 0; i < size; i++) {
            System.out.println("\n" + i + ".\nName: " + playlist.get(i).getName() + "\nArtist: " + playlist.get(i).getArtist());
        }
    }
}

