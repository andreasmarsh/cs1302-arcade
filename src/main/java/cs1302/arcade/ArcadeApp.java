package cs1302.arcade;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


/**
 * Application subclass for {@code ArcadeApp}.
 * @version 2019.fa
 */
public class ArcadeApp extends Application {

/** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        String bgString = ArcadeApp.class.getResource("/mainMenuBG.png").toExternalForm();
        String titleStr = ArcadeApp.class.getResource("/mainTitle.png").toExternalForm();

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Image bgImage = new Image(bgString);
        Image titleImage = new Image(titleStr);
        ImageView titleIV = new ImageView(titleImage);
        Insets titlePad = new Insets(30.0, 0.0, 0.0, 60.0);
        String style = "-fx-background-color: rgb(170, 69, 67);";

        vbox.getChildren().addAll(titleIV, hbox);
        vbox.setMargin(titleIV, titlePad);

        vbox.setPrefSize(700,700);
        vbox.setStyle(style);
        /*vbox.setBackground
            (new Background
             (new BackgroundImage(bgImage,
                                  BackgroundRepeat.NO_REPEAT,
                                  BackgroundRepeat.NO_REPEAT,
                                  BackgroundPosition.DEFAULT,
                                  new BackgroundSize(BackgroundSize.AUTO,
                                                     BackgroundSize. AUTO,
                                                     false, false, true,
                                                     false))));
        */

        Scene scene = new Scene(vbox, 700, 700);
        stage.setTitle("cs1302-arcade");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    } // start

} // ArcadeApp
