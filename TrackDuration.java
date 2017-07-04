
public class TrackDuration
{
    private int trackMinutes;
    private int trackSeconds;
    
    @Override
    public String toString()
    {
        return trackMinutes + "'" + trackSeconds + Character.toChars(34);
    }
    
    public TrackDuration(int trackMinutes, int trackSeconds)
    {
        this.trackMinutes = trackMinutes;
        this.trackSeconds = trackSeconds;
    }
}
