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
//
        String query = getUserInput();
//
        List<SongArtist> local_resultsArr = dataSrc.querySongArtistPREP(query);
//        dataSrc.close();

        int i = 1;
        System.out.println("Found "+local_resultsArr.size()+" records for "+query);

        for (SongArtist s : local_resultsArr){
            System.out.println("Record."+i+" Artist = "+s.getArtistName()+"; Album = "+s.getAlbumName()+"; Song = "+s.getSongName());
            i++;
        }
        dataSrc.close();

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
