import javafx.scene.Scene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableRow;
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
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
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
    TableRow<Music> tableRow;
    TableColumn<Music, String> trackTitleCol;
    TableColumn<Music, String> trackArtistCol;
    TableColumn<Music, String> trackStudioCol;
    TableColumn<Music, String> trackGenreCol;
    MediaPlayer mp;
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
        tableRow = new TableRow();
        trackTitleCol = new TableColumn("ID");
        trackArtistCol = new TableColumn("Track Name");
        trackStudioCol = new TableColumn("Artist");
        trackGenreCol = new TableColumn("Duration");        

        targetList = listView.getItems();
        //Music.ReadAll(targetList);    

        tableView.setEditable(true);
        trackTitleCol.setMinWidth(100);
        trackTitleCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("trackId"));

        trackArtistCol.setMinWidth(100);
        trackArtistCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("trackTitle"));

        trackStudioCol.setMinWidth(200);
        trackStudioCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("artistName"));

        trackGenreCol.setMinWidth(100);
        trackGenreCol.setCellValueFactory(
            new PropertyValueFactory<Music, String>("duration"));        
        
        // assigns the contents of the observable list from the readObs() method
        tableView.setItems(Music.readObs());

        // adds the columns above to the TableView control
        tableView.getColumns().addAll(trackTitleCol, trackArtistCol, trackStudioCol, trackGenreCol); 
        tableView.setOnMouseClicked((MouseEvent event) -> {
        if(event.getButton().equals(MouseButton.PRIMARY) && ){
            String track = tableView.getSelectionModel().getSelectedItem() + ".mp3";
            mp = new MediaPlayer(new Media(MusicPlayerSceneController.class.getResource(track).toString()));
            mp.play();
        }
    });
        /*tableView.getSelectionModel().setCellSelectionEnabled(true); */ // used if you want to be able to select individual cells
        borderPane.setTop(menuBar);
        borderPane.setCenter(tableView);
    }
    

    
    public Pane CreatePane(){
        
        return pane;
    }
}