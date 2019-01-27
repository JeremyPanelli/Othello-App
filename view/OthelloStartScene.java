package view;

import controller.OthelloStartController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class OthelloStartScene {
	public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private OthelloStartController controller;
    private Integer hardMode;
	
	private Scene scene;
	
	public Scene getScene()
	{
		return scene;
	}
	public Scene makeScene()
	{
		if(hardMode != null)
		{
			controller = new OthelloStartController(hardMode);
		}
		else
		{
			controller = new OthelloStartController();
		}
		VBox newRoot = new VBox();
		newRoot.setAlignment(Pos.CENTER);
		Text startText = new Text("Othello");
		startText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 90));
		startText.setStroke(Color.BLACK);
		startText.setStrokeWidth(5);
		startText.setFill(Color.WHITE);
		
		Button startButton = new Button();
		startButton.setText("Start");
		startButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		startButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 300px;");
		startButton.setOnAction(e->{
			controller.toAppScene();
		});
		
		Button instructionsButton = new Button();
		instructionsButton.setText("Instructions");
		instructionsButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		instructionsButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 300px;");
		instructionsButton.setOnAction(e->{
			controller.toInstructions();
		});
		
		Button settingsButton = new Button();
		settingsButton.setText("Settings");
		settingsButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		settingsButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 300px;");
		settingsButton.setOnAction(e->{
			controller.toSettings();
		});
		
//		Button leaderBoardButton = new Button();
//		leaderBoardButton.setText("Leaderboard");
//		leaderBoardButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
//		leaderBoardButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 300px;");
		
		BackgroundImage myBI= new BackgroundImage(new Image("images/10-Othello-Game-Pieces-Replacement-Black-and-White.jpg",WIDTH * TILE_SIZE + 300, HEIGHT * TILE_SIZE + 100,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		newRoot.setBackground(new Background(myBI));
		
		newRoot.getChildren().addAll(startText, startButton, instructionsButton, settingsButton);
		scene = new Scene(newRoot, WIDTH * TILE_SIZE + 300, HEIGHT * TILE_SIZE + 100);
		return scene;
	}
	public Integer getHardMode() {
		return hardMode;
	}
	public void setHardMode(Integer hardMode) {
		this.hardMode = hardMode;
	}
}
