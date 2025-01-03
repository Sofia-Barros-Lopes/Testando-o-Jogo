package jogomemoria;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;  // Certifique-se de que esta importação está aqui!
import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class SecondController {

    private static final int NUM_CARTAS = 16;
    private int jogador1Pontos = 0;
    private int jogador2Pontos = 0;
    private int turnos = 0;

    private List<Carta> cartas;
    private Carta cartaSelecionada1;
    private Carta cartaSelecionada2;
    private boolean jogoAtivo = false;

    @FXML
    private GridPane gridPane;
    
    @FXML
    private Label labelJogador1;  // Certifique-se de que esta variável é do tipo Label
    @FXML
    private Label labelJogador2;  // Certifique-se de que esta variável é do tipo Label
    
    @FXML
    private Button reiniciarButton;

    @FXML
    private void initialize() {
        reiniciarButton.setOnAction(this::reiniciarJogo);
    }

    public void iniciarJogo() {
        inicializarCartas();
        mostrarCartasTemporariamente();
    }

    private void inicializarCartas() {
        cartas = new ArrayList<>();
        for (int i = 0; i < NUM_CARTAS / 2; i++) {
            cartas.add(new Carta(i));  // Adiciona a primeira carta do par
            cartas.add(new Carta(i));  // Adiciona a segunda carta do par
        }
        Collections.shuffle(cartas);  // Embaralha as cartas para criar uma disposição aleatória

        gridPane.getChildren().clear(); // Limpa o GridPane para adicionar as novas cartas
        for (int i = 0; i < NUM_CARTAS; i++) {
            Carta carta = cartas.get(i);
            gridPane.add(carta.getImageView(), i % 4, (i / 4) + 1);  // Adiciona a carta ao grid
            carta.getImageView().setOnMouseClicked(e -> selecionarCarta(carta));  // Define o evento de clique
        }
    }

    private void mostrarCartasTemporariamente() {
        // Revela todas as cartas por 5 segundos
        for (Carta carta : cartas) {
            carta.revelar();
        }

        // Usar PauseTransition para esconder as cartas após 5 segundos
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> {
            for (Carta carta : cartas) {
                carta.esconder();
            }
            jogoAtivo = true; // Ativar o jogo após esconder as cartas
        });
        pause.play();
    }

    private void selecionarCarta(Carta carta) {
        if (!jogoAtivo || carta.isRevelada()) {
            return;
        }

        carta.revelar();
        if (cartaSelecionada1 == null) {
            cartaSelecionada1 = carta;
        } else if (cartaSelecionada2 == null) {
            cartaSelecionada2 = carta;
            verificarPares();
        }
    }

    private void verificarPares() {
        turnos++;
        if (cartaSelecionada1.getId() == cartaSelecionada2.getId()) {
            if (turnos % 2 == 1) {
                jogador1Pontos++;
            } else {
                jogador2Pontos++;
            }
            atualizarPontuacao();
            if (jogoVencido()) {
                reiniciarJogo(null);
            }
        } else {
            cartaSelecionada1.esconder();
            cartaSelecionada2.esconder();
        }
        cartaSelecionada1 = null;
        cartaSelecionada2 = null;
    }

    private void atualizarPontuacao() {
        // Aqui você chama o setText nos Labels, pois as variáveis estão corretamente associadas.
        labelJogador1.setText("Jogador 1: " + jogador1Pontos);
        labelJogador2.setText("Jogador 2: " + jogador2Pontos);
    }

    private boolean jogoVencido() {
        return cartas.stream().allMatch(Carta::isRevelada);
    }

    private void reiniciarJogo(ActionEvent event) {
        jogador1Pontos = 0;
        jogador2Pontos = 0;
        atualizarPontuacao();
        cartas.forEach(Carta::esconder);
        Collections.shuffle(cartas);
        jogoAtivo = false; // Desativar o jogo até que as cartas sejam escondidas
        mostrarCartasTemporariamente();
    }
}
