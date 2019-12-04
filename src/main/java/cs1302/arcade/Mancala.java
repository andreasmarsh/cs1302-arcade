package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

// TO DO:
// set up grid pane/IVs for board
// set child under titleIV, holding IVs with menu and control

public class Mancala {

    private VBox root;
    private HBox hbox;
    private HBox title;
    private String BG = Mancala.class.getResource("/mancala/mancalaBG.png").toExternalForm();
    private String titleStr = Mancala.class.getResource("/mancala/mancalaTitle.png").toExternalForm();
    private ImageView filler;
    private ImageView titleIV;
    private GridPane prompt;
    private ImageView[] marblesIV;

    private String menuStr = Mancala.class.getResource("/mancala/menu.png").toExternalForm();
    private String controlsStr = Mancala.class.getResource("/mancala/controls.png").toExternalForm();

    // variables for menu and controlls
    Image menuImage = new Image(menuStr);
    ImageView menuIV = new ImageView(menuImage);
    Image controlsImage = new Image(controlsStr);
    ImageView controlsIV = new ImageView(controlsImage);

    public Mancala() {
        GridPane gpane = new GridPane();
        //gpane.setGridLinesVisible(true);
        root = new VBox();
        hbox = new HBox();
        title = new HBox();

        // creates title IV
        Image titleImage = new Image(titleStr);
        titleIV = new ImageView(titleImage);

        // creates gridpane of menu and controls
        filler = new ImageView();
        filler.setFitWidth(500);
        filler.setFitHeight(100);
        prompt = new GridPane();
        prompt.setGridLinesVisible(true);
        prompt.add(menuIV, 0, 0);
        prompt. add(filler, 1, 0);
        prompt.add(controlsIV, 2, 0);

        // event handlers for menu and controls
        menuIV.setOnMouseEntered(e -> {
                root.setCursor(Cursor.HAND);
            });
        menuIV.setOnMouseExited(e -> {
                root.setCursor(Cursor.DEFAULT);
            });
        menuIV.setOnMouseClicked(getExitMenu());

        controlsIV.setOnMouseEntered(e -> {
                root.setCursor(Cursor.HAND);
            });
        controlsIV.setOnMouseExited(e -> {
                root.setCursor(Cursor.DEFAULT);
            });
        controlsIV.setOnMouseClicked(e -> {
                //Tetris tGame = new Tetris();
                //Scene tScene = new Scene(tGame.getRoot(), 700, 700);
                //stage.setScene(tScene);
                //stage.setResizable(false);
            });




        // make IVS for blanks
        ImageView blank1 = new ImageView();
        ImageView blank2 = new ImageView();
        blank1.setFitWidth(150);
        blank1.setFitHeight(220);

        // =-=-=-=-=- gridpane IV's =-=-=-=-

        //method to put imageviews into array
        //
        //makeBoardGrid(// put imageviews in array, then have the method to do above
        // method put in IV in gpane dynamically
        //instIV();

        // add test marble1 to imageview
        String m1Str = Mancala.class.getResource("/mancala/m1.png").toExternalForm();
        String m4Str = Mancala.class.getResource("/mancala/m4.png").toExternalForm();
        String m8Str = Mancala.class.getResource("/mancala/m8.png").toExternalForm();
        String m10Str = Mancala.class.getResource("/mancala/m10.png").toExternalForm();
        Image marble1 = new Image(m1Str);
        Image marble4 = new Image(m4Str);
        Image marble8 = new Image(m8Str);
        Image marble10 = new Image(m10Str);
        ImageView t1 = new ImageView(marble1);
        ImageView t2 = new ImageView(marble4);
        ImageView t3 = new ImageView(marble8);
        ImageView t4 = new ImageView();
        ImageView t5 = new ImageView();
        ImageView b1 = new ImageView(marble10);
        ImageView b2 = new ImageView();
        ImageView b3 = new ImageView();
        setSizeIV(t1);
        setSizeIV(t2);
        setSizeIV(t3);
        setSizeIV(t4);
        setSizeIV(t5);
        setSizeIV(b1);


        gpane.add(t1, 0, 0);
        gpane.add(t2, 1, 0);
        gpane.add(t3, 2, 0);
        gpane.add(b1, 0, 1);
        hbox.getChildren().addAll(blank1, gpane, blank2);
    }

    //it'd probably be better if this took in an array of imageviews
    /*private void instIV() {

      }*/
    private void setSizeIV(ImageView iv) {
        iv.setFitHeight(110);
        iv.setFitWidth(77);
    } // setSizeIV
/*    private ImageView[] createIV() {
        ImageView[] out;

        return out;
        }*/

    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + BG + "\')";
        Insets titlePad = new Insets(50.0, 0.0, 0.0, 00.0);

        title.getChildren().addAll(titleIV);
        root.getChildren().addAll(title, prompt, hbox);
        root.setMargin(title, titlePad);
        title.setAlignment(Pos.CENTER);
        root.setStyle(styleBG);
        return root;
    }

    /**
     * Returns exit menu.
     *
     * @return exitMenu
     */
    public EventHandler<MouseEvent> getExitMenu() {
        EventHandler<MouseEvent> exitMenu = event -> {
            // creates new window stage
            Stage newWindow = new Stage();

            // sets exit text and creates buttons
            Text exit = new Text("Are you sure you wish to quit Mancala?");
            Button yes = new Button("Yes");
            Button no = new Button("No");

            // adds text buttons to hboxes
            HBox exitBox = new HBox();
            exitBox.getChildren().addAll(exit);
            exitBox.setAlignment(Pos.CENTER);
            exitBox.setPadding(new Insets(10, 0, 0, 0));
            HBox yesNo = new HBox();
            yesNo.setSpacing(263);
            yesNo.setPadding(new Insets(20, 5, 5, 5));
            yesNo.getChildren().addAll(yes, no);

            // puts text and hbox into a vbox
            VBox vBox = new VBox();
            vBox.getChildren().addAll(exitBox, yesNo);
            vBox.setAlignment(Pos.TOP_CENTER);
            Scene exits = new Scene(vBox, 350, 80);

            // event handlers for buttons
            EventHandler<ActionEvent> noHandler = event2 -> {
                newWindow.close();
            };
            EventHandler<ActionEvent> yesHandler = event3 -> {
                //code to open new menu scene which replaces current
            };

            // set hanlers to buttons
            no.setOnAction(noHandler);
            yes.setOnAction(yesHandler);

            // New window of stage
            newWindow.setMaxWidth(350);
            newWindow.setMaxHeight(80);
            newWindow.setMinWidth(350);
            newWindow.setMinHeight(80);

            newWindow.setTitle("Exit Mancala");
            newWindow.sizeToScene();
            newWindow.setScene(exits);
            newWindow.setResizable(false);

            // modality
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.show();
        }; // exitMenu
        return exitMenu;
    } // getExitMenu()

}
