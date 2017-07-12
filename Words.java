import java.util.Hashtable;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Words{
    // instance variables - replace the example below with your own
    private String word;
    private boolean used;
    
    public Words(String word, boolean used){
        this.word = word;
        this.used = used;
    }
    
    public String getWord(){
        return word;
    }    
    
    @Override public String toString(){
        return word + ": " + used;
    }
    
    public static char[][] Filler(char[][] grid){
        Hashtable letters = new Hashtable();
        int ref = 0;
        char letter = '\u0000';
        letters.put(1,'A');
        letters.put(2,'B');
        letters.put(3,'C');
        letters.put(4,'D');
        letters.put(5,'E');
        letters.put(6,'F');
        letters.put(7,'G');
        letters.put(8,'H');
        letters.put(9,'I');
        letters.put(10,'J');
        letters.put(11,'K');
        letters.put(12,'L');
        letters.put(13,'M');
        letters.put(14,'N');
        letters.put(15,'O');
        letters.put(16,'P');
        letters.put(17,'Q');
        letters.put(18,'R');
        letters.put(19,'S');
        letters.put(20,'T');
        letters.put(21,'U');
        letters.put(22,'V');
        letters.put(23,'W');
        letters.put(24,'X');
        letters.put(25,'Y');
        letters.put(26,'Z'); 
        for(int x = 0; x <= 19; x++){
            for(int y = 0; y <= 19; y++){
                do{
                    ref = (int)(Math.ceil(Math.random()*26));
                }while(ref < 1 || ref > 26);
                if(grid[x][y] == '\u0000') grid[x][y] = (char)letters.get(ref);
            }
        }  
        return grid;  
    }

    public static char[][] AddWords(char[][] grid, String word){
        int hz = 0;
        int vt = 0;
        int sHz = hz;
        int sVt = vt;
        int dr = 0;
        int x = 0;
        int y = 0;
        boolean clash = false;
        //checks if the current word will fit onto the grid
        do{
            // generates the direction the word will take
            do{
                dr = (int)Math.ceil(Math.random()*8);
            }while(dr < 1 || dr > 8); 

            // initialises incremental x,y values for the correct direction

            switch(dr){
                case 1:     // up
                x = 0;
                y = -1;
                break;
                case 2:      // down
                x = 0;
                y = 1;
                break;           
                case 3:      // left
                x = -1;
                y = 0;
                break;       
                case 4:      // right
                x = 1;
                y = 0;
                break;           
                case 5:      // up/right
                x = 1;
                y = -1;
                break;           
                case 6:      // up/left
                x = -1;
                y = -1;
                break;           
                case 7:      // down/right
                x = 1;
                y = 1;
                break;       
                case 8:      // down/left
                x = -1;
                y = 1;
                break;           
            }

            hz = (int)Math.floor((Math.random()*20));
            if(hz + word.length() > 19){
                hz -= (hz+word.length()-19);
            }else if(hz - word.length() < 0){
                hz -= hz-word.length();
            }

            vt = (int)Math.floor((Math.random()*20));
            if(vt + word.length() > 19){
                vt -= (vt+word.length()-19);
            }else if(vt - word.length() < 0){
                vt -= vt-word.length();
            }            

            sVt = vt;
            sHz = hz;        
            // checks for word clashes

            for(int i = 0; i < word.length(); i++){
                if(grid[vt][hz] != '\u0000'){
                    clash = true;
                    break;
                }else{
                    if(i < word.length()){
                        vt += y;
                        hz += x;
                    }
                }
            }
        }while(clash == true);
        // add word to array
        hz = sHz;
        vt = sVt;
        for(int j = 0; j < word.length(); j++){
            grid[vt][hz] = word.charAt(j);
            if(j < word.length()){
                vt += y;
                hz += x;
            }
        }
        return grid;
    }
    
    public static ArrayList selectWords(String courseCode, String topic)
    {
        ArrayList<Words> wordList = new ArrayList();

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Main.database.newStatement("SELECT Word, Used FROM Words WHERE CourseRef = ? AND topic = ? LIMIT 10");
        try{
            if (statement != null)      // Assuming the statement correctly initated...
            {
                statement.setString(1, courseCode);
                statement.setString(2, topic);
                ResultSet results = Main.database.runQuery(statement);       // ...run the query!
                
                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            wordList.add( new Words(results.getString("word"), results.getBoolean("used")));
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }
        
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
        return wordList;
    }
    
    public static ArrayList<String> GetCourses(){
        ArrayList<String> courses = new ArrayList<String>();
        return courses;
    }
    
}
