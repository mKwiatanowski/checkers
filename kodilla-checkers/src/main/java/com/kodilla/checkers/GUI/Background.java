package com.kodilla.checkers.gui;


import com.kodilla.checkers.messages.Confirm;
import com.kodilla.checkers.logic.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.net.URI;

public class Background {

    public static final String GAME_PREAMBLE_AND_INSTRUCTIONS = "\n\t \t  WELCOME IN THE CHECKERS GAME! \n" +
            "\n" +
            "\nIMPORTANT:\n" +
            "- Before You start to play please pick up player type (human or computer) from left side panel.\n" +
            "- If You change player type during current play, the game will restart automatically.\n" +
            "- If You want to take break from game, You can save it by button placed in left side panel.\n" +
            "\nMAIN RULES:\n" +
            "- Black pawns start first.\n" +
            "- Kills are mandatory (if You have possibility to beat enemy pawn You have to do it).\n" +
            "- Multiple kills are allowed (You can beat few pawns if they are set diagonally after themselves).\n" +
            "- Normal pawn can move and kill diagonally only in one direction - forward.\n" +
            "- The pawn which reached enemy boarder becomes a king.\n" +
            "- King can move forward and back diagonally.\n" +
            "- When a pawn kills enemy king it becomes the king.\n" +
            "\nCOLORS INTERPRETATION:\n" +
            "- Green highlighted squares marked pawns that You can choose.\n" +
            "- Blue highlighted squares marked tiles where You can go.\n" +
            "- Red highlighted squares marked mandatory kills.\n" +
            "***********************************************************";
    private TextAreaManager textAreaManager;
    private MoveHighlightingManager moveHighlightingManager;
    private Game game;

    public Background(Stage primaryStage) {
        configureApplicationWindow(primaryStage);
        Scene GUI = new Scene(createGUI());
        primaryStage.setScene(GUI);

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram(primaryStage);
        });

        primaryStage.show();
    }

    public void closeProgram(Stage primaryStage) {
        Confirm confirmBox = new Confirm();
        boolean answer = confirmBox.display();
        if (answer)
            primaryStage.close();
    }

    private void configureApplicationWindow(Stage primaryStage) {
        primaryStage.setTitle("Checkers");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNIFIED);
    }

    private void initialiseApplicationBackend() {
        textAreaManager = new TextAreaManager();
        Player initialBlackPlayer = new HumanPlayer(Team.BLACK);
        Player initialWhitePlayer = new HumanPlayer(Team.WHITE);
        game = new Game(initialBlackPlayer, initialWhitePlayer, textAreaManager, moveHighlightingManager);
    }

    private Parent createGUI() {
        Image imageBack = new Image("file:resources/background_wood.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBack, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        javafx.scene.layout.Background woodenTexture = new javafx.scene.layout.Background(backgroundImage);

        VBox controls = buildControls();
        initialiseApplicationBackend();

        Pane gameBoard = getGameBoard();

        TextArea output = textAreaManager.setUpGameOutputFeed();

        HBox layout = new HBox(10, controls, gameBoard, output);
        layout.setPadding(new Insets(10));
        layout.setBackground(woodenTexture);

        return layout;
    }

    private Pane getGameBoard() {
        Pane gameBoard = new Pane();
        int squareEdgeLength = Game.BOARD_SIZE * Game.TILE_SIZE;
        gameBoard.setMinSize(squareEdgeLength, squareEdgeLength);
        gameBoard.getChildren().setAll(game.getComponents());

        return gameBoard;
    }

    private VBox buildControls() {
        Button newGameButton = getNewGameButton();
        Button saveGameButton = getSaveGameButton();
        Button loadGameButton = getLoadGameButton();
        Button userMoveHighlightingToggleButton = getUserMoveHighlightingToggleButton();
        Button computerMoveHighlightingToggleButton = getAIMoveHighlightingToggleButton();
        moveHighlightingManager = new MoveHighlightingManager(userMoveHighlightingToggleButton, computerMoveHighlightingToggleButton);
        Button displayInstructionsButton = getDisplayInstructionsButton();
        VBox playerControls = getPlayerControls();

        VBox userControls = new VBox(10, newGameButton, saveGameButton, loadGameButton,
                userMoveHighlightingToggleButton, computerMoveHighlightingToggleButton, displayInstructionsButton, playerControls);

        userControls.setPrefWidth(300);
        userControls.setMinWidth(300);

        return userControls;
    }

    private VBox getPlayerControls() {
        ComboBox<String> blackPlayer = getPlayerMenu(Team.BLACK);
        ComboBox<String> whitePlayer = getPlayerMenu(Team.WHITE);

        GridPane teamPlayerMenus = new GridPane();

        Label blackPlayerLabel = new Label("Black player");
        blackPlayerLabel.setTextFill(Color.valueOf("white"));
        teamPlayerMenus.add(blackPlayerLabel, 0, 0);
        teamPlayerMenus.add(blackPlayer, 1, 0);

        Label whitePlayerLabel = new Label("White player");
        whitePlayerLabel.setTextFill(Color.valueOf("white"));
        teamPlayerMenus.add(whitePlayerLabel, 0, 1);
        teamPlayerMenus.add(whitePlayer, 1, 1);
        teamPlayerMenus.setHgap(10);

        return new VBox(10, teamPlayerMenus);
    }

    private ComboBox<String> getPlayerMenu(Team team) {
        ComboBox<String> playerMenu = new ComboBox<>();
        playerMenu.getItems().setAll("Human", "Computer");
        playerMenu.getSelectionModel().select("Human");
        playerMenu.setOnAction((event -> {
            switch (playerMenu.getSelectionModel().getSelectedIndex()) {
                case 0:
                    game.restartGame(new HumanPlayer(team));
                    break;
                case 1:
                    game.restartGame(new AIPlayer(team));
                    break;
            }
        }));
        return playerMenu;
    }

    private Button getUserMoveHighlightingToggleButton() {
        Button userMoveHighlightingToggleButton = new Button("Disable: user moves backlight \n");

        userMoveHighlightingToggleButton.setOnAction(value -> {
            moveHighlightingManager.toggleUserMovesHighlighting();
            game.refreshBoard();
            if (moveHighlightingManager.isUserMoveHighlighting()) {
                textAreaManager.display("User moves backlight enabled.\n");
            } else {
                textAreaManager.display("User moves backlight disabled.\n");
            }
        });

        userMoveHighlightingToggleButton.setMaxWidth(Double.MAX_VALUE);
        return userMoveHighlightingToggleButton;
    }

    private Button getAIMoveHighlightingToggleButton() {
        Button AIMoveHighlightingToggleButton = new Button("Disable: computer moves backlight \n");

        AIMoveHighlightingToggleButton.setOnAction(value -> {
            moveHighlightingManager.toggleComputerMovesHighlighting();
            game.refreshBoard();
            if (moveHighlightingManager.isComputerMoveHighlighting()) {
                textAreaManager.display("Computer move backlight enabled.\n");
            } else {
                textAreaManager.display("Computer move backlight disabled.\n");
            }
        });

        AIMoveHighlightingToggleButton.setMaxWidth(Double.MAX_VALUE);
        return AIMoveHighlightingToggleButton;
    }

    private Button getDisplayInstructionsButton() {
        Button displayInstructionsButton = new Button("Game description");
        displayInstructionsButton.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://ctycms.com/mn-rochester/docs/checkers-instructions.pdf"));
                textAreaManager.display("Instructions has been displayed in Your internet browser.\n");
            } catch (Exception exception) {
                textAreaManager.display("We're sorry, something went wrong please try again.\n");
            }
        });

        displayInstructionsButton.setMaxWidth(Double.MAX_VALUE);
        return displayInstructionsButton;
    }

    private Button getNewGameButton() {
        Button newGameButton = new Button("Start new game");
        newGameButton.setOnAction(value -> game.restartGame(null));
        newGameButton.setMaxWidth(Double.MAX_VALUE);
        return newGameButton;
    }

    private Button getSaveGameButton() {
        Button saveGameButton = new Button("Save game");
        saveGameButton.setOnAction(value -> game.saveGame());
        saveGameButton.setMaxWidth(Double.MAX_VALUE);
        return saveGameButton;
    }

    private Button getLoadGameButton() {
        Button loadGameButton = new Button("Load game");
        loadGameButton.setOnAction(value -> game.loadGame());
        loadGameButton.setMaxWidth(Double.MAX_VALUE);
        return loadGameButton;
    }
}
