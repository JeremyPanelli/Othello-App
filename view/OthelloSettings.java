package view;

import controller.OthelloStartController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class OthelloSettings {
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
		CheckBox music = new CheckBox("No Music");
		music.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		music.setTextFill(Color.WHITE);
		
		music.setOnAction(e->{
			controller.startStopMusic();
		});
		Label settings = new Label("Settings");	
		settings.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));
		settings.setTextFill(Color.WHITE);
		Text text = new Text("");
		Text text2 = new Text("");
		
		CheckBox difficulty = new CheckBox("Hard Mode (5 second moves)");
		difficulty.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		difficulty.setTextFill(Color.WHITE);
		difficulty.setOnAction(e->{
			controller.setStartTime();
		});
		
		Button backButton = new Button();
		backButton.setText("Back to Start");
		backButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		backButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 300px;");
		backButton.setOnAction(e->{
			controller.toStartScene();
		});
		
		newRoot.getChildren().addAll(settings, music, text, difficulty, text2, backButton);
		scene = new Scene(newRoot, WIDTH * TILE_SIZE + 300, HEIGHT * TILE_SIZE + 100);
		return scene;
	}
}
