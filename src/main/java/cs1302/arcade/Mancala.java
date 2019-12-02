package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

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
        gpane.setGridLinesVisible(true);
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

        ImageView t1 = new ImageView();
        ImageView t2 = new ImageView();
        ImageView t3 = new ImageView();
        ImageView t4 = new ImageView();
        ImageView t5 = new ImageView();
        ImageView b1 = new ImageView();
        ImageView b2 = new ImageView();
        ImageView b3 = new ImageView();
        setSizeIV(t1);
        setSizeIV(t2);
        setSizeIV(t3);
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
        iv.setFitWidth(80);
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
}
