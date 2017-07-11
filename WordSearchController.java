import javafx.application.Application;
import java.util.Hashtable;
import javafx.beans.property.SimpleStringProperty;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;

public class WordSearchController{
    // instance variables - replace the example below with your own
    public static Group root;
    public static Scene scene; 
    public static BorderPane borderPane;
    public static MenuBar menuBar;
    public static Menu game;
    public static Menu options;
    public static Menu help;
    public static MenuItem newGame;
    public static MenuItem exit;
    public static MenuItem editWords;
    public static MenuItem defaultSettings;
    public static MenuItem topics;
    public static MenuItem about;
    public static MenuItem courseItem;
    public static SplitMenuButton courses;
    public static SplitMenuButton categories;
    public static GridPane gridPane;
    public static VBox vBox;
    public static Button create;
    public static ListView wordList;
    public static ArrayList<Courses> courseList;
    public static char[][] grid; 
    public static String topic;
    public static String cID;

    public WordSearchController(){
        borderPane = new BorderPane();
        scene = new Scene(borderPane,1200,900);
        menuBar = new MenuBar();
        game = new Menu("Game");
        options = new Menu("Options");
        help = new Menu("Help");
        newGame = new MenuItem("New Game");
        exit = new MenuItem("Exit");
        editWords = new MenuItem("Add / Amend Words List");
        defaultSettings = new MenuItem("Change Default Settings");
        topics = new MenuItem("Help Topics");
        about = new MenuItem("About Word Search");
        game.getItems().add(newGame);
        game.getItems().add(exit);
        options.getItems().add(editWords);
        options.getItems().add(defaultSettings);
        help.getItems().add(topics);
        help.getItems().add(about); 
        menuBar.getMenus().add(game);
        menuBar.getMenus().add(options);
        menuBar.getMenus().add(help);
        courses = new SplitMenuButton();
        categories = new SplitMenuButton();
        create = new Button("Create");
        courseList = new ArrayList();
        courseList = Courses.showCourses();
        for(Courses course : courseList){
            courseItem = new MenuItem(course.toString());
            courses.getItems().add(courseItem); 
            courseItem.setOnAction(courseEvent ->{
               courses.setText(course.getCourseRef()); 
                    categories.getItems().clear();                                                      // clears the current options in the categories split menu button ready for the new options
                    ArrayList<Categories> categoryList = new ArrayList();
                    categoryList = Categories.showCategories(course.getCourseRef().toString());         // gets the categories from the database using the course ref field as the search criteria
                    for(Categories category : categoryList){                                            // for each category in the array list
                        MenuItem categoryItem = new MenuItem(category.getCategory().toString());        // create a new menu item
                        categoryItem.setOnAction(categoryEvent ->{                                      // and give each item the same instructions to run when clicked
                                System.out.println(category.getCategory().toString() + " clicked");
                                topic = category.getCategory().toString();
                                categories.setText(category.getCategory().toString());
                            });
                        categories.getItems().add(categoryItem);                                        // add the item to the categories split menu button
                    }
            });
        }        
        gridPane = new GridPane();
        create.setOnAction(ActionEvent ->{
            gridPane.getChildren().clear();    
        });
        courses.setMinWidth(150);
        courses.setText("Course");
        categories.setMinWidth(150);
        categories.setText("Category");        
        create = new Button("Find Words");
        vBox = new VBox();
        vBox.getChildren().addAll(courses,categories,create);
        
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPrefSize(700,700);
        grid = new char[20][20];
        for(int r = 0; r < 20; r++){
            for(int c = 0; c < 20; c++){
                Button button = new Button(" ");
                button.setMaxWidth(30);
                button.setMaxHeight(30);
                gridPane.add(button, c, r);            
            }
        }
        
        wordList = new ListView();
        
        borderPane.setTop(menuBar);
        borderPane.setCenter(gridPane);
        borderPane.setLeft(vBox);
        borderPane.setRight(wordList);

    }

    public static char[][] CreateGrid(){
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
        grid = new char[20][20];
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
}
