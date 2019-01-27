package Othello;

import java.io.File;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.OthelloAppView;
import view.OthelloStartScene;

public class OthelloApp extends Application{
	private static Stage primaryStage;
	private static MediaPlayer player;
    @Override
    public void start(Stage stage) throws Exception {
    	primaryStage = stage;
    	OthelloStartScene start = new OthelloStartScene();
    	
    	OthelloAppView app = new OthelloAppView();
    	Scene scene = start.makeScene();
    	
        primaryStage.setTitle("OthelloApp");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        try {
        	  Media media = new Media(getClass().getResource("/media/ItalianMusic-BackgroundChillOut.mp3").toURI().toString());
        	  player = new MediaPlayer(media);
        	  player.play();
        	} catch (URISyntaxException e) {
        	  e.printStackTrace();
        	} 
    }
    public static MediaPlayer getMediaPlayer() {
    	return player;
    }
    public static Stage getStage()
    {
    	return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
