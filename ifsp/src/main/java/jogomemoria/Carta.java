package jogomemoria;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Carta {
    private int id;  // Identificador único para cada carta
    private boolean revelada = false;  // Indica se a carta está virada ou não
    private ImageView imageView;  // A imagem que será mostrada na carta
    private static final String PATH_VERSO = "/resources/interrogacao.png";  // Ajuste o caminho conforme necessário

    // Nome das imagens de frente
    private static final String[] IMAGENS = {
        "imagemcachorro.png", "imagemgato.png", "imagembaleia.png", "imagemMacaco.png", 
        "imagemarara.png", "imagemcavalo.png", "imagemcobra.png", "imagemelefante.png", 
        "imagemfoca.png", "imagemhamster.png", "imagemleao.png", "imagemovelha.png", 
        "imagempeixe.png", "imagemtigre.png", "imagemvaca.png"
    };

    public Carta(int id) {
        this.id = id;
        this.imageView = new ImageView();
        this.imageView.setFitHeight(100);  // Define o tamanho da carta
        this.imageView.setFitWidth(70);
        esconder();  // Inicializa a carta com o verso (interrogação)
    }

    public int getId() {
        return id;  // Retorna o ID da carta
    }

    public boolean isRevelada() {
        return revelada;  // Verifica se a carta está virada
    }

    public ImageView getImageView() {
        return imageView;  // Retorna o ImageView para ser adicionado ao GridPane
    }

    // Revela a carta (mostra a imagem correspondente)
    public void revelar() {
        this.revelada = true;
        String caminhoImagemFrente = "path/to/" + IMAGENS[id];  // Caminho da imagem da carta
        Image imagemFrente = new Image("file:" + caminhoImagemFrente);  // Carregar a imagem correspondente
        imageView.setImage(imagemFrente);  // Exibe a imagem da frente
    }

    // Esconde a carta (mostra o verso com interrogação)
    public void esconder() {
        this.revelada = false;
        Image imagemVerso = new Image("file:" + PATH_VERSO);  // Imagem do verso
        imageView.setImage(imagemVerso);  // Exibe a imagem de interrogação
    }
}
