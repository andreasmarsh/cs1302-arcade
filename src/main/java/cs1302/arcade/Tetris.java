package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

// TO DO:
// set up grid pane/IVs for board
// set child under titleIV, holding IVs with menu and control

public class Tetris {

    private VBox root;
    private HBox hbox;
    private String BG = Mancala.class.getResource("/mancala/mancalaBG.png").toExternalForm();
    private String titleStr = Mancala.class.getResource("/mancala/mancalaTitle.png").toExternalForm();
    private ImageView titleIV;
    private ImageView prompt;
    private ImageView[] marblesIV;

    public Tetris() {
        GridPane gpane = new GridPane();
        gpane.setGridLinesVisible(true);
        root = new VBox();
        hbox = new HBox();

        Image titleImage = new Image(titleStr);
        titleIV = new ImageView(titleImage);
        prompt = new ImageView();
        prompt.setFitHeight(100);

        // make IVS for blanks
        ImageView blank1 = new ImageView();
        ImageView blank2 = new ImageView();
        blank1.setFitWidth(150);
        blank1.setFitHeight(220);

        // =-=-=-=-=- gridpane IV's =-=-=-=-
        ImageView t1 = new ImageView();
        ImageView t2 = new ImageView();
        ImageView t3 = new ImageView();
        ImageView b1 = new ImageView();
        t1.setFitHeight(110);
        t1.setFitWidth(80);
        b1.setFitHeight(110);
        b1.setFitWidth(80);
        //method to put imageviews into array
        //
        //makeBoardGrid(// put imageviews in array, then have the method to do above

        gpane.add(t1, 0, 0);
        gpane.add(t2, 1, 0);
        gpane.add(t3, 2, 0);
        gpane.add(b1, 0, 1);
        hbox.getChildren().addAll(blank1, gpane, blank2);
    }

/*    private ImageView[] createIV() {
        ImageView[] out;

        return out;
        }*/

    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + BG + "\')";
        Insets titlePad = new Insets(30.0, 0.0, 0.0, 100.0);

        root.getChildren().addAll(titleIV, prompt, hbox);
        root.setMargin(titleIV, titlePad);
        root.setStyle(styleBG);
        return root;
    }
}
