import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Music {

    private int trackId;
    private String trackTitle;
    private String artistName;
    private String duration;
    private static final String FILENAME = "U:\\Computer Science\\GUITextFiles\\MusicInfo.txt";
    
    public Music(int trackId, String trackTitle, String artistName, String duration) {
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
                list.add(new Music(record[0], record[1], record[2], record[3]));
                
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

    public int getTrackDurationSeconds() {
        return trackDurationSeconds;
    }

    public void setTrackDurationSeconds(int trackDurationSeconds) {
        this.trackDurationSeconds = trackDurationSeconds;
    }

    public int getTrackDurationMins() {
        return trackDurationMins;
    }

    public void setTrackDurationMins(int trackDurationMins) {
        this.trackDurationMins = trackDurationMins;
    }

}