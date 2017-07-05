import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Music {

    private int trackId;
    private String trackTitle;
    private String artistName;
    private TrackDuration duration;
    private static final String FILENAME = "MusicInfo.txt";
    
    @Override
    public String toString(){
        return trackTitle;
    }
    
    public Music(int trackId, String trackTitle, String artistName, TrackDuration duration) {
        this.trackId = trackId;
        this.trackTitle = trackTitle;
        this.artistName = artistName;
        this.duration = duration;
    }
    
    public static void ReadAll(List<Music> list) {
        list.clear();
        
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] record = sCurrentLine.split(",");
                TrackDuration td = new TrackDuration(Integer.parseInt(record[3]),Integer.parseInt(record[4]));
                list.add(new Music(Integer.parseInt(record[0]), record[1], record[2], td));
                
            }
            
           br.close();
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    public static ObservableList<Music> readObs(){
        ObservableList<Music> list = FXCollections.observableArrayList(); 
        BufferedReader br = null;
        FileReader fr = null;
        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
                String[] record = sCurrentLine.split(",");
                TrackDuration td = new TrackDuration(Integer.parseInt(record[3]),Integer.parseInt(record[4]));
                list.add(new Music(Integer.parseInt(record[0]), record[1], record[2], td));
                
            }
            
           br.close();
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }        
        return list;
    }
    
    
    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public TrackDuration getDuration() {
        return duration;
    }

    public void setDuration(TrackDuration duration) {
        this.duration = duration;
    }



}