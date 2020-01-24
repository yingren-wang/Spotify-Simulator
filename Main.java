import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Run();
		System.out.println("Done");
	}
	
	private static void Run() {
		// dummy data to start
		Song mySong1 = new Song("Do you", "Troyboi");
		Song mySong2 = new Song("Bleach", "Brockhamton");
		Song mySong3 = new Song("Pink + White", "Frank Ocean");
		
		ArrayList<Playlist> allList = new ArrayList<>();

		ArrayList<Song> dList = new ArrayList<>();
		Playlist defaultlist = new Playlist(dList, "defaultlist");
		allList.add(defaultlist);
		
		defaultlist.addSong(mySong1);
		defaultlist.addSong(mySong2);
		defaultlist.addSong(mySong3);

		for(int i = 0; i < defaultlist.getSize(); i++) {
			defaultlist.getSong(i).printSong();
		}
		
		// prompt user to add a song
		System.out.println("Welcome to the program!\n"
				+ "What do you want to do?\n"
				+ "Add a playlist:		p <playlist_name>\n"
				+ "Add a song: 		s <song_name> <artist_name> <playlist_name>\n"
				+ "Delete a song:		d <song_name> <artist_name>\n"
				+ "* <playlist_name>s in 's' and 'd' are optional, if there's no playlist specified, changes will be applied to default playlist");
		Scanner scan = new Scanner(System.in);
		String userChoice = scan.next();
		String songName;
		String artist;
		String theList;
		int index;
		String targetList;
		Song song;
		
		
		switch(userChoice) {
		case "p":
			String playlistName = scan.next();
			ArrayList<Song> list = new ArrayList<>();
			Playlist playlist = new Playlist(list, playlistName);
			break;
		case "s":
			songName = scan.next();
			artist = scan.next();
			System.out.println(scan.hasNext());
			// TODO: system doesn't stop with no playlist input during s and d
			if(scan.hasNext() == true)
			{
				theList = scan.next();
				targetList = theList;
			}
			else {
				targetList = "defaultlist";
			}
			System.out.println(songName + " " + artist + " " + targetList);
			song = new Song(songName, artist);
			
			index = searchList(targetList, allList);
			if(index == -1) {
				System.out.println("There's no playlist called '" + targetList + "', please double check\n");
			}
			else {
				allList.get(index).addSong(song);
				allList.get(index).printList();
			}
		case "d":
			songName = scan.next();
			artist = scan.next();
			theList = scan.next();
			song = new Song(songName, artist);
			
			if(scan.hasNext() == true)
			{
				theList = scan.next();
				targetList = theList;
			}
			else {
				targetList = "defaultlist";
			}
			
			index = searchList(targetList, allList);
			if(index == -1) {
				System.out.println("There's no playlist called '" + targetList + "' , please double check\n");
			}
			else {
				allList.get(index).deleteSong(song);
				allList.get(index).printList();
			}
		}
		
	}	
	
	// search a list in all lists
	public static int searchList(String list, ArrayList<Playlist> alllist) {
		int result = -1;
		for(int i = 0; i < alllist.size(); i++) {
			if(alllist.get(i).getListName() == list) {
				result = i; // return the index of the list in all lists
			}
		}
		return result;
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
			if(search.getName() == song.getName() && search.getArtist() == song.getArtist()) {
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
	public void printList() {
		for(int i = 0; i < size; i++) {
			System.out.println("\n" + i + ".\nName: " + playlist.get(i).getName() + "\nArtist: " + playlist.get(i).getArtist());
		}
	}
}

