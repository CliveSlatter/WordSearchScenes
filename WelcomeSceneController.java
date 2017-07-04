import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomeSceneController
{
    // instance variables - replace the example below with your own
    public static Scene welcomeScene;
    public static Pane root;
    Text msg;
    Text text;
    TextField textField;
    BackgroundImage myBI;
    KeyValue initKeyValue;
    KeyFrame initFrame;
    KeyValue endKeyValue;
    KeyFrame endFrame;
    Timeline timeline;
    Blend blend;
    Blend blend1;
    Blend blend2;
    DropShadow ds;
    DropShadow ds1;
    InnerShadow is;
    InnerShadow is1;

    public WelcomeSceneController()
    {
        root = new Pane();
        welcomeScene = new Scene(root,1000,800);
        
        msg = new Text("Welcome to Clive's Word Search Games");
        msg.setTextOrigin(VPos.TOP);
        msg.setFont(Font.font(50));
        msg.setFill(Color.YELLOW);
        double sceneWidth = welcomeScene.getWidth();
        double msgWidth = msg.getLayoutBounds().getWidth();

        initKeyValue = new KeyValue(msg.translateXProperty(), sceneWidth);
        initFrame = new KeyFrame(Duration.ZERO, initKeyValue);

        endKeyValue = new KeyValue(msg.translateXProperty(), -1.0 * msgWidth);
        endFrame = new KeyFrame(Duration.seconds(10), endKeyValue);

        timeline = new Timeline(initFrame, endFrame);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

        text = new Text("Clive's Stuff");
        text.setFont(Font.font(100));
        text.setFill(Color.YELLOW);
        text.setLayoutX(250);
        text.setLayoutY(200);
        
        blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);

        ds = new DropShadow();
        ds.setColor(Color.rgb(254, 235, 66, 0.3));
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);

        blend.setBottomInput(ds);

        ds1 = new DropShadow();
        ds1.setColor(Color.web("#f13a00"));
        ds1.setRadius(20);
        ds1.setSpread(0.2);

        blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);

        is = new InnerShadow();
        is.setColor(Color.web("#feeb42"));
        is.setRadius(15);
        is.setChoke(0.7);
        blend2.setBottomInput(is);

        is1 = new InnerShadow();
        is1.setColor(Color.web("#f13a00"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);

        blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);

        blend.setTopInput(blend1);

        text.setEffect(blend);
        msg.setEffect(blend);
        
        myBI= new BackgroundImage(new Image("Charlie.jpg",1000,800,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        root.getChildren().addAll(msg,text);
    }


}
