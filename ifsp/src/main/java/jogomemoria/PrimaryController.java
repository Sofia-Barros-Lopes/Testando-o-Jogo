package jogomemoria;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable {

    // Variável de controle para saber se a imagem foi carregada
    //private boolean clicou = false;

    @FXML
    private VBox vboxPrincipal;

    @FXML
    private ImageView jogoLogo;

    @FXML
    private void switchToSecondary() throws IOException {
        // Muda para a tela secundária
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Define o fundo inicial do VBox
        BackgroundFill bf = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bf);
        vboxPrincipal.setBackground(bg);
    }
}