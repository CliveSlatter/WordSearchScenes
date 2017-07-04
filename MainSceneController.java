import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
public class MainSceneController{
    Menu file;
    Menu help;
    Menu gameOptions;
    Menu options;
    MenuBar menuBar;
    Menu wordSearch;
    MenuItem musicPlayer;
    MenuItem exit;
    MenuItem topics;
    MenuItem about;
    MenuItem twenty;
    MenuItem twentyFive;
    MenuItem thirty;
    BorderPane borderPane;
    Scene scene;
    public MainSceneController(){
        borderPane = new BorderPane();
        scene = new Scene(borderPane,1000,800);
        menuBar = new MenuBar();
        file = new Menu("File");
        gameOptions = new Menu("Game Options...");
        options = new Menu("Options");
        musicPlayer = new MenuItem("Music Player");
        wordSearch = new Menu("Word Search Options...");
        twenty = new MenuItem("20 x 20");
        twentyFive = new MenuItem("25 x 25");
        thirty = new MenuItem("30 x 30");
        exit = new MenuItem("Exit Application");
        topics = new MenuItem("Topics");
        about = new MenuItem("About");
        gameOptions.getItems().addAll(wordSearch,musicPlayer);
        wordSearch.getItems().addAll(twenty,twentyFive,thirty);
        file.getItems().addAll(gameOptions,exit);
        options.getItems().addAll(topics,about);
        menuBar.getMenus().addAll(file,options);
        borderPane.setTop(menuBar);
    }
}
