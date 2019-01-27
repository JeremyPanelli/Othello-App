package view;

import controller.OthelloStartController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class OthelloInstructions {
	public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private OthelloStartController controller;
	
	private Scene scene;
	
	public Scene getScene()
	{
		return scene;
	}
	public Scene makeScene()
	{
		controller = new OthelloStartController();
		VBox newRoot = new VBox();
		newRoot.setStyle("-fx-background-color: black;");
		newRoot.setAlignment(Pos.CENTER);
		Label instructionsText = new Label("Othello is a 2 player game played on an 8x8 checkerboard type of layout. Pieces are \"flanked\" and flipped over, with the goal of the game being to have the most pieces of their color (white or black) by the end of the game. You can flank moves on as many sides as possible by strategically putting your piece on a square that the opponents square. Each player continues making moves until no moves are possible. In this version of Othello, each player has 15 seconds (5 seconds on hard) to make a move or a move will be played for that player randomly. Move by hovering over the piece you wish to move and clicking the mouse on that piece. Once the mouse is pressed, it is the other player's turn and their timer will start. Continue play until no moves are possible.");
		instructionsText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		instructionsText.setWrapText(true);
		instructionsText.setTextFill(Color.WHITE);
		instructionsText.setTextAlignment(TextAlignment.CENTER);
		
		
		Button backButton = new Button();
		backButton.setText("Back");
		backButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		backButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 300px;");
		backButton.setOnAction(e->{
			controller.toStartScene();
		});
		
		newRoot.getChildren().addAll(instructionsText, backButton);
		scene = new Scene(newRoot, WIDTH * TILE_SIZE + 300, HEIGHT * TILE_SIZE + 100);
		return scene;
	}
}
