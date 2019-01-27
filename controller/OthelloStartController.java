package controller;

import Othello.OthelloApp;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import view.OthelloAppView;
import view.OthelloEndScene;
import view.OthelloInstructions;
import view.OthelloSettings;
import view.OthelloStartScene;

public class OthelloStartController {
	private OthelloAppView appView;
	private OthelloApp app;
	private OthelloInstructions instructions;
	private OthelloStartScene startScene;
	private OthelloSettings settings;
	private OthelloEndScene end;
	private Integer hardMode = 15;
	
	public OthelloStartController()
	{	}
	public OthelloStartController(Integer hard)
	{	
		this.hardMode = hard;
	}
	public void toAppScene() {
		appView = new OthelloAppView(this.hardMode);
		OthelloApp.getStage().setScene(new Scene(appView.createContent()));
	}
	public void toEndScene(String winner, int winCount) {
		end = new OthelloEndScene();
		OthelloApp.getStage().setScene(end.makeScene(winner, winCount));
	}
	public void toSettings()
	{
		settings = new OthelloSettings();
		OthelloApp.getStage().setScene(settings.makeScene());
	}
	public void toInstructions()
	{
		instructions = new OthelloInstructions();
		OthelloApp.getStage().setScene(instructions.makeScene());
	}
	public void toStartScene()
	{
		startScene = new OthelloStartScene();
		startScene.setHardMode(this.hardMode);
		Scene scene = startScene.makeScene();
		OthelloApp.getStage().setScene(scene);
	}
	public void startStopMusic()
	{
		if(OthelloApp.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING)
		{
			OthelloApp.getMediaPlayer().pause();
		}
		else
		{
			OthelloApp.getMediaPlayer().play();
		}
	}
	public void setHardMode(Integer i)
	{
		this.hardMode = i;
	}
	public Integer getHardMode() {
		return this.hardMode;
	}
	public void setStartTime()
	{
		if(hardMode == 15)
		{
			hardMode = 5;
		}
		else 
		{
			hardMode = 15;
		}
	}
}
