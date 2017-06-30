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
    MenuItem wordSearch;
    MenuItem exit;
    MenuItem topics;
    MenuItem about;
    BorderPane borderPane;
    Scene scene;
    public MainSceneController(){
        borderPane = new BorderPane();
        scene = new Scene(borderPane,1000,800);
        menuBar = new MenuBar();
        file = new Menu("File");
        gameOptions = new Menu("Game Options...");
        options = new Menu("Options");
        wordSearch = new MenuItem("Word Search");
        exit = new MenuItem("Exit Application");
        topics = new MenuItem("Topics");
        about = new MenuItem("About");
        gameOptions.getItems().add(wordSearch);
        file.getItems().addAll(gameOptions,exit);
        options.getItems().addAll(topics,about);
        menuBar.getMenus().addAll(file,options);
        borderPane.setTop(menuBar);
    }
}
