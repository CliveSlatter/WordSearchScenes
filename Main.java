import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import javafx.scene.input.MouseEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class Main extends Application
{
    public static Stage theStage;
    public static DatabaseConnection database;
    WelcomeSceneController wSc;
    public static MainSceneController mSc;
    public static MusicPlayerSceneController playController;
    public static WordSearchController wordSc;
    public static Categories categories;
    //PaneSceneController psc = new PaneSceneController();

    @Override
    public void start(Stage primaryStage){
        database = new DatabaseConnection("WordSearch.db");
        theStage = primaryStage;
        wSc = new WelcomeSceneController();
        mSc = new MainSceneController();
        wordSc = new WordSearchController();
        playController = new MusicPlayerSceneController();
        //playController = new MusicPlayerController();
        theStage.setOnCloseRequest((WindowEvent we) -> displayCloseDialogue(we));
        wSc.root.setOnMousePressed(e -> mouseClicked(e));
        mSc.wordSearch.setOnAction(e-> MenuClicked(e));
        mSc.musicPlayer.setOnAction(e-> MenuClicked(e));
        wordSc.create.setOnAction(e-> ButtonClicked(e));
        wordSc.courses.setOnAction(e-> ButtonClicked(e));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(wSc.welcomeScene);
        primaryStage.show();
    }

    public static void displayCloseDialogue(WindowEvent we){
        System.exit(0);
    }

    public static void main(String[] args){
        try{
            Main.launch(args);
        }
        catch(IllegalStateException is){

        }
    }

    public static void MenuClicked(ActionEvent e){
        if(e.getSource()==mSc.wordSearch)
            theStage.setScene(wordSc.scene);
        else if(e.getSource()==mSc.musicPlayer)
            theStage.setScene(playController.playerScene);
    }

    public static void mouseClicked(MouseEvent e){
        if(e.isPrimaryButtonDown())
        {
            theStage.setScene(mSc.scene);
        }else if(e.isSecondaryButtonDown()){
            System.out.println("Right mouse button clicked");        
        }
    }

    public static void ButtonClicked(ActionEvent e){
        if(e.getSource()==wordSc.create){
            wordSc.gridPane.getChildren().clear();
            String courseID = wordSc.courses.getText();
            String topic = wordSc.categories.getText();
            char[][] grid = new char[20][20];
            //grid = wordSc.AddWords();
            
            
            grid = Words.Filler();

            for(int r = 0; r < 20; r++){
                for(int c = 0; c < 20; c++){
                    char letter = grid[r][c];
                    Button button = new Button(Character.toString(letter));
                    button.setMaxWidth(30);
                    button.setMaxHeight(30);
                    wordSc.gridPane.add(button, c, r);            
                }
            }            
        }else if(e.getSource()==wordSc.courses){
            wordSc.courses.getItems().clear();


        }
    }
}
