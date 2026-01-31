package bird;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BirdBot bird;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image birdImage = new Image(this.getClass().getResourceAsStream("/images/Bird.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    //public void setDuke(Duke d) {
    //    duke = d;
    //}

    public void setBird(BirdBot b) {
        bird = b;

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(bird.getWelcomeMessage(), birdImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bird.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, birdImage)
        );
        userInput.clear();
    }
}

