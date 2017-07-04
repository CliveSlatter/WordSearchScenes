import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
public class MusicPlayerSceneController{
    Scene playerScene;
    BorderPane borderPane;
    Pane pane;
    MenuBar menuBar;
    Menu file;
    Menu options;
    MenuItem quit;
    MenuItem border;
    MenuItem standard;
    ListView<Music> listView;
    List<Music> targetList;
    TableView<Music> tableView;
    TableColumn<Music, String> trackTitleCol;
    TableColumn<Music, String> trackArtistCol;
    TableColumn<Music, String> trackStudioCol;
    TableColumn<Music, String> trackGenreCol;
    public MusicPlayerSceneController(){
        borderPane = new BorderPane();
        playerScene = new Scene(borderPane, 1000, 800);
        
        menuBar = new MenuBar();
        file = new Menu("File");
        options = new Menu("Options");
        quit = new MenuItem("Exit");
        border = new MenuItem("View Border Pane Layout");
        standard = new MenuItem("View Standard Pane Layout");
        file.getItems().add(quit);
        options.getItems().addAll(border, standard);
        menuBar.getMenus().addAll(file, options);
        
        listView = new ListView();
        tableView = new TableView();
        trackTitleCol = new TableColumn("Track Name");
        trackArtistCol = new TableColumn("Artist Name");
        trackStudioCol = new TableColumn("Recording Studio");
        trackGenreCol = new TableColumn("Genre");        

        targetList = listView.getItems();
        //Music.ReadAll(targetList);    

        tableView.setEditable(true);
        trackTitleCol.setMinWidth(100);
        trackTitleCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("title"));

        trackArtistCol.setMinWidth(100);
        trackArtistCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("artist"));

        trackStudioCol.setMinWidth(200);
        trackStudioCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("studio"));

        trackGenreCol.setMinWidth(100);
        trackGenreCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("format"));        
        
        // assigns the contents of the observable list from the readObs() method
        //tableView.setItems(Music.readObs());

        // adds the columns above to the TableView control
        tableView.getColumns().addAll(trackTitleCol, trackArtistCol, trackStudioCol, trackGenreCol);  
        
        borderPane.setTop(menuBar);
        borderPane.setCenter(tableView);
    }
    

    
    public Pane CreatePane(){
        
        return pane;
    }
}