import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Courses
{
    // instance variables - replace the example below with your own
    private String courseRef;
    private String courseName;

    public Courses(String courseRef, String courseName)
    {
        // initialise instance variables
        this.courseRef = courseRef;
        this.courseName = courseName;
    }

    public String getCourseRef(){
        return courseRef;
    }
    
    @Override public String toString()
    {
        return courseName + ": " + courseRef;
    }
    
    public static ArrayList showCourses()
    {
        ArrayList<Courses> coursesList = new ArrayList();

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Main.database.newStatement("SELECT CourseRef, CourseName FROM Course ORDER BY CourseName"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Main.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        coursesList.add( new Courses(results.getString("courseRef"), results.getString("courseName")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }
        
        return coursesList;
    }
}
