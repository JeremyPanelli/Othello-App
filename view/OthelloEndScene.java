package view;

import controller.OthelloStartController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class OthelloEndScene {
	public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private OthelloStartController controller;
	
	private Scene scene;
	
	public Scene getScene()
	{
		return scene;
	}
	public Scene makeScene(String winner, int winCount)
	{
		controller = new OthelloStartController();
		VBox newRoot = new VBox();
		newRoot.setStyle("-fx-background-color: black;");
		newRoot.setAlignment(Pos.CENTER);
		String winString = "Results: " + winner + " wins with " + winCount + " Pieces!";
		Label instructionsText = new Label(winString);
		instructionsText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
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
