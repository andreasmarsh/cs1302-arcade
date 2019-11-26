package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

public class Mancala {

    private VBox root;
    private GridPane gpane;
    private String BG = Mancala.class.getResource("/mancala/mancalaBG.png").toExternalForm();
    private String titleStr = Mancala.class.getResource("/mancala/mancalaTitle.png").toExternalForm();
    private ImageView titleIV;

    public Mancala() {
        gpane = new GridPane();
        gpane.setGridLinesVisible(true);
        root = new VBox();

        Image titleImage = new Image(titleStr);
        titleIV = new ImageView(titleImage);

    }

    public VBox getRoot() {
        String styleBG = "-fx-background-image: url(\'" + BG + "\')";

        root.getChildren().addAll(titleIV, gpane);
        root.setStyle(styleBG);
        return root;
    }
}
