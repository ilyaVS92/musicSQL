import model.SongArtist;
import model.dataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        dataSource dataSrc = new dataSource();
        if (!dataSrc.open()){
            System.out.println("Cannot open datasource");
            return;
        }
////        List<Artist> artists = dataSrc.queryArtist(dataSource.ORDER_BY_NONE); // returns all artists (currently hardcoded)
////        if (artists == null){
////            System.out.println("No artists!");
////            return;
////        }
////        for (Artist artist : artists){
////            System.out.println("ID = "+artist.getId()+", name = "+artist.getName());
////        }
////
////        List<String> albums = dataSrc.queryAlbumsForArtist("Carole King", dataSource.ORDER_BY_DESC);
////        if (albums == null){
////            System.out.println("No albums found!");
////            return;
////        }
////        for (String z : albums){
////            System.out.println("Album name = "+z);
////        }
//        String songTitle = "Go Your Own Way";
//        boolean exactMatchesOnly = false;
//        //another idea is to add a "does NOT contain" search type
//
//        List<SongArtist> artistNames = dataSrc.querySongArtist(songTitle, exactMatchesOnly, dataSource.sortOrder.ASC);
//
//        if (artistNames == null || artistNames.isEmpty()){
//            System.out.println("No artists or songs found for "+songTitle);
//            return;
//        }
//        System.out.println("Search for: \""+songTitle+"\" results");
//        for (SongArtist z : artistNames){
//            System.out.println("Artist: "+z.getArtistName()+" | Album: "+z.getAlbumName()+" | Track No."+ z.getTrackNo()+" | Song: "+z.getSongName());
//        }
//        dataSrc.querySongsMetadata();
//        String searchTerm = dataSource.TABLE_SONGS;
//        System.out.println("number of "+searchTerm+": "+dataSrc.getCount(dataSource.TABLE_SONGS));
//        System.out.println(dataSrc.createView()); //returns true if successful

//
        String query = getUserInput();

        List<SongArtist> local_resultsArr = dataSrc.querySongInfoView(query);
        dataSrc.close();

        for (SongArtist s : local_resultsArr){
            System.out.println(s.getArtistName());
        }
    }
    public static String getUserInput(){
        System.out.println("Enter song name to search for song information:");

        Scanner scanner = new Scanner(System.in);
        String queryFromConsole = "";

        if(scanner.hasNextLine()) {
            queryFromConsole = scanner.nextLine();
        }
        scanner.close();
        return queryFromConsole;
    }

}
//SELECT name, album, track FROM artist_list WHERE title="Go Your Own Way"
