package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dataSource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Taurus\\Applications\\sqLite\\test_databases\\"+DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";
    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTISTS_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK= "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_ALBUM_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final String space = " ";

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;


    public static final String col_ArtistName = TABLE_ARTISTS+"."+COLUMN_ARTISTS_NAME; //artists.name
    public static final String col_AlbumName = TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME; //albums.name
    public static final String col_albumXartist = TABLE_ALBUMS+"."+COLUMN_ALBUM_ARTIST; //albums.artist
    public static final String col_Album_ID = TABLE_ALBUMS+"."+COLUMN_ALBUM_ID; //albums._id
    public static final String col_SongsTrack = TABLE_SONGS+"."+COLUMN_SONG_TRACK;//songs.track
    public static final String col_SongTitle = TABLE_SONGS+"."+COLUMN_SONG_TITLE; //songs.title
    public static final String col_Artists_ID = TABLE_ARTISTS+"."+COLUMN_ARTISTS_ID; //artists._id
    public static final String col_Songs_ID = TABLE_SONGS+"."+ COLUMN_SONG_ID; //songs._id
    public static final String col_SongsAlbum = TABLE_SONGS+"."+COLUMN_SONG_ALBUM; //songs.album
    public static final String col_SongsTitle = TABLE_SONGS+"."+COLUMN_SONG_TITLE; //songs.title

    public static final String QUERY_ARTIST_FOR_SONG_SORT = " ORDER BY "+col_ArtistName+", "+col_AlbumName+" COLLATE NOCASE ";

    public static final String QUERY_ALBUMS_BY_ARTIST = "SELECT "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME+" FROM "+TABLE_ALBUMS+" INNER JOIN "+TABLE_ARTISTS+" ON "+TABLE_ALBUMS+"."+COLUMN_ALBUM_ARTIST+"="+TABLE_ARTISTS+"."+COLUMN_ARTISTS_ID
        +" WHERE "+TABLE_ARTISTS+"."+COLUMN_ARTISTS_NAME+"=\"";
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT = " ORDER BY "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME + " COLLATE NOCASE";

    public static final String query_ArtistsBySong_String ="SELECT "+col_ArtistName+", "+col_AlbumName+", "+col_SongsTrack+", "+col_SongTitle+" FROM "+TABLE_ARTISTS
                +" INNER JOIN "+TABLE_ALBUMS+" ON "+ col_albumXartist +"="+col_Artists_ID
                +" INNER JOIN "+TABLE_SONGS+" ON "+ col_SongsAlbum +"="+col_Album_ID;
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS "+TABLE_ARTIST_SONG_VIEW+
            " AS SELECT "+col_ArtistName+", "+col_AlbumName+" AS "+COLUMN_SONG_ALBUM+", "+col_SongsTrack+", "+col_SongsTitle+
            " FROM "+TABLE_SONGS+" INNER JOIN "+TABLE_ALBUMS+" ON "+col_SongsAlbum+"="+col_Album_ID+" INNER JOIN "+TABLE_ARTISTS+" ON "+col_albumXartist+"="+col_Artists_ID+
            " ORDER BY "+col_ArtistName+", "+col_AlbumName+", "+col_SongsTrack;
    public static final String QUERY_VIEW_SONG_INFO = "SELECT "+COLUMN_ARTISTS_NAME+", "+COLUMN_SONG_ALBUM+", "+COLUMN_SONG_TRACK+", "+COLUMN_SONG_TITLE+" FROM artist_list ";

    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT "+COLUMN_ARTISTS_NAME+", "+COLUMN_SONG_ALBUM+", "+COLUMN_SONG_TRACK+" FROM "+TABLE_ARTIST_SONG_VIEW+" WHERE "+COLUMN_SONG_TITLE+" LIKE ?";
    //1. create a STRING with the wildcard "?" - String strQuery = "SELECT name FROM albums WHERE title = ? ORDER BY ?, ?"
    //2. declare an instance variable for the prep stmnt - private PreparedStatmeent prepStmnt;
    //3. define the prepared statement as a function of the connection; usually in the open method - prepStmnt = conn.prepareStatement(strQuery)
    //4. set the replacement text for the wildcard in the prepared statement - prepStmnt.setString(1, input); SELECT name FROM albums WHERE title = ?(changed to contents of "title") ORDER BY ?, ?

    public static final String PREP_ARTIST_QUERY_retID = "SELECT "+COLUMN_ARTISTS_ID+" FROM "+TABLE_ARTISTS+" WHERE "+COLUMN_ARTISTS_NAME+" = ?";
    public static final String PREP_ALBUM_QUERY_retID = "SELECT "+COLUMN_ALBUM_ID+" FROM "+TABLE_ALBUMS+" WHERE "+COLUMN_ALBUM_NAME+" = ?";
    public static final String PREP_SONG_QUERY_retID = "SELECT "+COLUMN_SONG_ID+" FROM "+TABLE_SONGS+" WHERE "+COLUMN_SONG_TITLE+" = ?";

    public static final String PREP_QUERY_ARTIST_BY_SONG = query_ArtistsBySong_String + " WHERE "+col_SongTitle+" LIKE ?";
    private PreparedStatement prep_QueryArtistsBySong;

    public static final String PREP_ARTIST_QUERY = "SELECT * FROM "+TABLE_ARTISTS+" WHERE "+COLUMN_ARTISTS_NAME+" = ?";
    public static final String PREP_ALBUM_QUERY = "SELECT * FROM "+TABLE_ALBUMS+" WHERE "+COLUMN_ALBUM_NAME+" = ?";
    public static final String PREP_SONG_QUERY = "SELECT * FROM "+TABLE_SONGS+" WHERE "+COLUMN_SONG_TITLE+" = ?";

    public static final String INSERT_ARTISTS = "INSERT INTO "+TABLE_ARTISTS+"("+COLUMN_ARTISTS_NAME+") VALUES (?)";
    public static final String INSERT_ALBUMS = "INSERT INTO "+TABLE_ALBUMS+" ("+COLUMN_ALBUM_NAME+", "+COLUMN_ALBUM_ARTIST+") VALUES (?,?)";
    public static final String INSERT_SONG = "INSERT INTO "+TABLE_SONGS+" ("+COLUMN_SONG_TRACK+", "+COLUMN_SONG_TITLE+", "+COLUMN_SONG_ALBUM+") VALUES (?,?,?)";

    private PreparedStatement prepStmnt_querySongInfoView;

    public enum sortOrder {
        NONE (" ASC", 1),
        ASC (" ASC", 2),
        DESC (" DESC", 3);

        String sortOrderText;
        int id;

        sortOrder(String sortOrderText, int id) {
            this.sortOrderText = sortOrderText;
            this.id = id;
        }

    }

    public enum ID_type{
        ARTIST,
        ALBUM,
        SONG
    }

    private Connection conn;

    PreparedStatement prep_QueryArtists;
    PreparedStatement prep_QueryAlbums;
    PreparedStatement prep_QuerySongs;

    PreparedStatement insertIntoArtists;
    PreparedStatement insertIntoAlbums;
    PreparedStatement insertIntoSongs;

    public String produceQueryString(StringBuilder sb){
        return sb.toString();
    }

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            prepStmnt_querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);

            prep_QueryArtistsBySong = conn.prepareStatement(PREP_QUERY_ARTIST_BY_SONG);

            prep_QueryArtists = conn.prepareStatement(PREP_ARTIST_QUERY_retID);
            prep_QueryAlbums = conn.prepareStatement(PREP_ALBUM_QUERY_retID);
            prep_QuerySongs = conn.prepareStatement(PREP_SONG_QUERY_retID);

            insertIntoArtists = conn.prepareStatement(INSERT_ARTISTS, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONG);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " +e.getMessage());
            return false;
        }
    }

    public void close(){
        try {
            if (insertIntoArtists!=null){
                insertIntoAlbums.close();
            }
            if (insertIntoAlbums!=null){
                insertIntoAlbums.close();
            }
            if (insertIntoSongs!=null){
                insertIntoSongs.close();
            }
            if (prepStmnt_querySongInfoView!=null){
                prepStmnt_querySongInfoView.close();
            }
            if (prep_QueryArtists !=null){
                prep_QueryArtists.close();
            }
            if (prep_QueryAlbums !=null){
                prep_QueryAlbums.close();
            }
            if (prep_QuerySongs !=null){
                prep_QuerySongs.close();
            }
            if (conn != null) {
                conn.close();
                System.out.println("Connection conn - closed");
            }
        } catch (SQLException e){
            System.out.println("Could not CLOSE connection: "+e.getMessage());
        }
    }

    public List<Artist> queryArtist(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTISTS_NAME);
            sb.append(" COLLATE NOCASE ");

            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC ");
            } else {
                sb.append(" ASC ");
            }

        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                artists.add(new Artist(
                        (results.getInt(INDEX_ARTISTS_ID)),
                        (results.getString(INDEX_ARTISTS_NAME))
                )); // required type: int, string; provided: void, void;
            }

            return artists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    public List<String> queryAlbumsForArtist (String artistName, int sortOrder){ //not used
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT "+TABLE_ALBUMS+"."+COLUMN_ALBUM_NAME);
        sb.append(space);
        sb.append("FROM "+TABLE_ALBUMS+" INNER JOIN "+TABLE_ARTISTS);
        sb.append(space+"ON ");
        sb.append(TABLE_ALBUMS+"."+COLUMN_ALBUM_ARTIST+"="+TABLE_ARTISTS+"."+COLUMN_ARTISTS_ID);
        sb.append(space);
        sb.append("WHERE "+TABLE_ARTISTS+"."+COLUMN_ARTISTS_NAME+"=\""+artistName+"\"");
        sb.append(space);
        sb.append("ORDER BY "+TABLE_ALBUMS+"."+COLUMN_ARTISTS_NAME);
        sb.append(space+"COLLATE NOCASE");

        if (sortOrder == ORDER_BY_DESC) {
            sb.append(" DESC");
        } else {
            sb.append(" ASC");
        }
        try (Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {
            List <String> albumsForArtist = new ArrayList<>();
            while (results.next()) {
                albumsForArtist.add(results.getString(1));
            }
            return albumsForArtist;

        } catch (SQLException e) {
            System.out.println(sb);
            System.out.println("Error executing albumsForArtist() query: "+e.getMessage());
            return null;
        }
    }

    public List<SongArtist> querySongArtist(String songTitle, boolean requireExactMatch, sortOrder so){

        StringBuilder localStringBuilderQuery = new StringBuilder();
        localStringBuilderQuery.append(query_ArtistsBySong_String);

        if (requireExactMatch){
            localStringBuilderQuery.append(" WHERE "+col_SongTitle+" = "+"\""+songTitle+"\"");
        } else {
            localStringBuilderQuery.append(" WHERE "+col_SongTitle+" LIKE '%"+songTitle+"%'");
        }
        localStringBuilderQuery.append(QUERY_ARTIST_FOR_SONG_SORT);
        appendOrderByInstructions(localStringBuilderQuery, so.id);

        String localQuery = produceQueryString(localStringBuilderQuery);
        List <SongArtist> artistsFromSongs = new ArrayList<>();

        try (Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(localQuery)) {

            ResultSetMetaData meta = resultSet.getMetaData();
            int numColumns = meta.getColumnCount(); // from connection class

            while (resultSet.next()){
                artistsFromSongs.add(new SongArtist(resultSet.getString(1),
                                                    resultSet.getString(2),
                                                    resultSet.getInt(3),
                                                    resultSet.getString(4)));
            }
            System.out.println(localQuery);

        } catch (SQLException e) {
            System.out.println(localQuery+"\n");
            System.out.println("Unable to return artist for "+songTitle+" -error: "+e.getMessage());
        }
        return artistsFromSongs;
    }
    ////////////////////////////////////////////////////////////////////////
    public List<SongArtist> querySongArtistPREP(String songTitle) throws SQLException {

        List <SongArtist> artistsFromSongs = new ArrayList<>();

        prep_QueryArtistsBySong.setString(1,"%"+songTitle+"%");
        ResultSet resultSet = prep_QueryArtistsBySong.executeQuery();

        try {

            while (resultSet.next()){
                artistsFromSongs.add(new SongArtist(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)));
            }

        } catch (SQLException e) {
            System.out.println("Unable to return artist for "+songTitle+" -error: "+e.getMessage());
        }
        return artistsFromSongs;
    }
    ////////////////////////////////////////////////////////////////////////
    public StringBuilder appendOrderByInstructions (StringBuilder sb, int sortOrder){
        if (sortOrder != ORDER_BY_NONE){
            if(sortOrder == ORDER_BY_DESC){
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        return sb;
    }
    public void querySongsMetadata(){
        String sql = "SELECT * FROM "+TABLE_SONGS;
        try (Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            ResultSetMetaData meta = rs.getMetaData();
            int numColumns = meta.getColumnCount();

            for (int i = 1; i<=numColumns; i++){
                System.out.format("Column %d in the songs table is %s\n", i, meta.getColumnName(i));
            }
        }catch(SQLException e){
            System.out.println("Meta data query failed: "+e.getMessage());
        }
    }
    public int getCount (String table){
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM "+table; //good practice - assign names AS to resulting columns
        try(Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql)){
            return rs.getInt("count");
        } catch (SQLException e){
            System.out.println("Unable to run getCount for "+table+": "+e.getMessage());
            return -1;
        }
    }

    public boolean createView (){
        try(Statement statement = conn.createStatement()){
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;

        } catch (SQLException e){
            System.out.println("SQL Stmnt: "+CREATE_ARTIST_FOR_SONG_VIEW +"\n" +
                    "Create view failed: "+e.getMessage());
            return false;
        }
    }

    public List<SongArtist> querySongInfoView (String title){
        try{
            prepStmnt_querySongInfoView.setString(1, "%"+title+"%");
            ResultSet results = prepStmnt_querySongInfoView.executeQuery();

            List<SongArtist> songArtists = new ArrayList<>();

            while (results.next()){
                songArtists.add(new SongArtist(results.getString(COLUMN_ARTISTS_NAME),results.getString(COLUMN_SONG_ALBUM),results.getInt(COLUMN_SONG_TRACK)));
            }
            return songArtists;

        } catch (SQLException e) {
            System.out.println("querySongView failed: "+e.getMessage());
            return null;
        }
    }

    private int insertArtist(String artistName) { //setting method
        String type = "Artist";
        int artist_id = getIDVALUE(artistName,ID_type.ARTIST);
        if (artist_id>0){
            return artist_id;
        } else {
            try {
                insertIntoArtists.setString(1,artistName);
                int affectedRows = insertIntoArtists.executeUpdate(); //returns the number of rows affected
                if (affectedRows != 1){
                    throw new SQLException("Could not insert "+type);
                }
                ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
                if (generatedKeys.next()){
                    System.out.println("success generating _id"); //the code DOES get here!

                    artist_id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Could not get generated _id for "+type);
                }
            } catch (SQLException e) {
                System.out.println("Insert failure. Unable to create or find "+type+": " + e.getMessage());
            }
            return artist_id;
        }
    }

    private int insertAlbum(String albumName, int albumArtistID) {
        String type = "Album";
        int album_id = getIDVALUE(albumName,ID_type.ALBUM);
        if (album_id>0){
            return album_id;
        } else {
            try {
                insertIntoAlbums.setString(1,albumName);
                insertIntoAlbums.setInt(2,albumArtistID);
                int affectedRows = insertIntoAlbums.executeUpdate();
                if (affectedRows != 1){
                    throw new SQLException("Could not insert "+type);
                }
                ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
                if (generatedKeys.next()){//make sure that there is a key there
                    album_id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Could not get generated _id for "+type);
                }
            } catch (SQLException e) {
                System.out.println("ALBUM Insert failure. Unable to create or find "+type+": " + e.getMessage());
            }
            return album_id;
        }
    }

    public void insertSong(String songTitle, String artist, String album, int trackNo, boolean runTest) {
        // 20.08.2022 - artist created, and assigned _id; album created, assigned _id, and assigned -1 as album_artist_id; song created; assigned _id = 5351, track = 1; album = -1

        String type = "Song";
        try {
            //start new transaction - change default behavior for autoCommit function
            conn.setAutoCommit(false);

            int artist_ID_local = insertArtist(artist);
            System.out.println(">>> artist_id set to: " + artist_ID_local);
            int album_ID_local = insertAlbum(album, artist_ID_local);
            System.out.println(">>> album_id set to: " + album_ID_local);
            int song_ID_local = getIDVALUE(songTitle, ID_type.SONG);

            if (song_ID_local > 0) {
                //need code here
            }
                insertIntoSongs.setInt(1, trackNo);
                insertIntoSongs.setString(2, songTitle);
                insertIntoSongs.setInt(7, album_ID_local);

                int affectedRows = insertIntoSongs.executeUpdate();
                if (affectedRows == 1) {
                    if (runTest){
                        conn.rollback();
                        System.out.println("""
                                ...
                                code CAN be committed, but this is just a test - rollback() executed
                                ...""");
                    } else {
                        conn.commit();
                        System.out.println("Committed changes to database");
                    }
                } else {
                    throw new SQLException("1 row should have been affected - in reality there were +" + affectedRows + "; the problem is above line:535 - rollback() called");
                }
            } catch(Exception e){
                System.out.println("unable to add records, calling ROLLBACK: " + e.getMessage());
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    System.out.println("ROLLBACK UNSUCCESSFUL: " + e2.getMessage());
                }
            }
        finally{
                try {
                    System.out.println("resetting default autocommit behavior (true)");
                    conn.setAutoCommit(true);
                } catch (SQLException e3) {
                    System.out.println("Unable to reset Autocommit - not your lucky day " + e3.getMessage());
                }
            }

    }

    public int getIDVALUE(String name, ID_type type){ //returns -1 if ID was NOT found; correctly returns ID for EXISTING artists, EXISTING albums, EXISTING songs
        int id_value = -2;
        try {
            switch(type){
                case ARTIST -> {
                    System.out.println("...searching for Artist_id"); //sout test statement
                    prep_QueryArtists.setString(1, name);
                    ResultSet resultSet = prep_QueryArtists.executeQuery();
                    if (resultSet.next()){
                        id_value = resultSet.getInt(COLUMN_ARTISTS_ID);
                    } else {
                        id_value = -1;
                        System.out.println("getIDVALUE - NO "+type+" FOUND; _id set to: " + id_value);
                    }
                }
                case SONG -> {
                    System.out.println("...searching for Song_id"); //sout test statement
                    prep_QuerySongs.setString(1, name);
                    ResultSet resultSet = prep_QuerySongs.executeQuery();
                    if (resultSet.next()){
                        System.out.println("getIDVALUE - SONG FOUND; returning "+resultSet.getInt(COLUMN_ARTISTS_ID));
                        id_value = resultSet.getInt("_id"); //this is returning something strange
                    } else {
                        id_value = -1;
                        System.out.println("getIDVALUE - NO "+type+" FOUND; _id set to: " + id_value);
                    }
                }
                case ALBUM -> {
                    System.out.println("...searching for Album_id"); //sout test statement
                    prep_QueryAlbums.setString(1, name);
                    ResultSet resultSet = prep_QueryAlbums.executeQuery();
                    if (resultSet.next()){
                        System.out.println("getIDVALUE - ALBUM FOUND; returning "+resultSet.getInt(COLUMN_ARTISTS_ID));
                        id_value = resultSet.getInt("_id"); //this is returning something strange
                    } else {
                        id_value = -1;
                        System.out.println("getIDVALUE - NO "+type+" FOUND; _id set to: " + id_value);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("getIDVALUE - GENERIC FAILURE; returning -3: "+e.getMessage());
            id_value = -3;
        } finally {
            System.out.println("... exiting getIDVALUE; returning id_value=" + id_value);
        }
        return id_value;
    }
}
