import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Categories{
    // instance variables - replace the example below with your own
    private String categoryID;
    private String courseRef;
    private String category;

    /**
     * Constructor for objects of class Categories
     */
    public Categories(String categoryID, String courseRef, String category)
    {
        // initialise instance variables
        this.categoryID = categoryID;
        this.courseRef = courseRef;
        this.category = category;
    }

    @Override public String toString(){
        return category;
    }

    public String getCategory(){
        return category;
    }
    
    public static ArrayList showCategories(String courseCode)
    {
        ArrayList<Categories> categoriesList = new ArrayList();

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Main.database.newStatement("SELECT CategoryID, CourseRef, Category FROM Category WHERE CourseRef = ? ORDER BY Category");
        try{
            if (statement != null)      // Assuming the statement correctly initated...
            {
                statement.setString(1, courseCode);
                ResultSet results = Main.database.runQuery(statement);       // ...run the query!
                
                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            categoriesList.add( new Categories(results.getString("categoryID"), results.getString("courseRef"), results.getString("category")));
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
        return categoriesList;
    }
}
