package view;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MoveIndicator;
import model.Piece;
import model.PieceType;
import model.Tile;
import controller.OthelloController;
import controller.OthelloStartController;

public class OthelloAppView {//extends Application {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private OthelloController controller;
    private Tile[][] board = new Tile[WIDTH][HEIGHT];
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private Group indicatorGroup = new Group();
    private Integer startTime;
    private Integer seconds = startTime;
    private Label timer1 = new Label("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\tWhite Time: " );
    private Label timer2 = new Label("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\tBlack Time: ");
    private Integer wCount = 2;
    private Integer bCount = 2;
    private Label whiteCount = new Label("\n\t\tWhite Pieces: " + wCount + "  (50%)");
    private Label blackCount = new Label("\n\t\t\t\t\t\t\t\t\t\tBlack Pieces: " + bCount+ "  (50%)");
    private Timeline time= new Timeline();
    private Button restartButton;
    private Button backButton;
    private Pane root;
    private OthelloStartController myController;
    public OthelloAppView() {}
    public OthelloAppView(Integer startTime)
    {
    	this.startTime = startTime;
    	this.seconds = startTime;
    }
    public Parent createContent() {
        root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE + 300, HEIGHT * TILE_SIZE + 100);
        root.setStyle("-fx-background-color: black;");
        restartButton = new Button("Restart");
        backButton = new Button("Quit");
        root.getChildren().addAll(tileGroup, pieceGroup, indicatorGroup, timer1, timer2, whiteCount, blackCount, restartButton, backButton);
        timer1.toBack();
        timer2.toBack();
        timer1.setTextFill(Color.WHITE);
        timer2.setTextFill(Color.WHITE);
        backButton.setLayoutX(WIDTH * TILE_SIZE + 90);
        backButton.setLayoutY(400);
        backButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		backButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 200px;");
		myController = new OthelloStartController();
		backButton.setOnAction(e->{
			myController.toStartScene();
		});
        
        restart();
        restartButton.setLayoutX(WIDTH * TILE_SIZE +90);
        restartButton.setLayoutY(300);
        restartButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        restartButton.setStyle("-fx-border-width: 2px;-fx-pref-width: 200px;");
        restartButton.setOnAction(e->{
        	restart();
        });
        return root;
    }
    private void restart()
    {
    	controller = new OthelloController();
    	tileGroup.getChildren().clear();
    	pieceGroup.getChildren().clear();
    	indicatorGroup.getChildren().clear();
    	seconds = startTime; 
    	doTime();
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(x, y, controller);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);
                setUpStart(x, y, tile);
            }
        }
        controller.addBoard(board);
        getIndicators();
        doLabels();
    }
    public void setUpStart(int x, int y, Tile tile)
    {
    	Piece piece = null;

        if (y == 3 && x == 3) {
            piece = makePiece(PieceType.BLACK, x, y);
        }
        if (y == 4 && x == 3) {
            piece = makePiece(PieceType.WHITE, x, y);
        }
        if (y == 4 && x == 4) {
            piece = makePiece(PieceType.BLACK, x, y);
        }
        if (y == 3 && x == 4) {
            piece = makePiece(PieceType.WHITE, x, y);
        }
        if (piece != null) {
            tile.setPiece(piece);
            pieceGroup.getChildren().add(piece);
        }
    }
    
    public void addPiece(Piece piece)
    {
    	pieceGroup.getChildren().add(piece);
    }
    
    
    //Creates a piece at a particular location
    public Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);
        return piece;
    }
    
    public void updateBoard()
    {
    	board = controller.getBoard();
    	tileGroup.getChildren().clear();
    	pieceGroup.getChildren().clear();
    	for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
            	tileGroup.getChildren().add(board[x][y]);
            	pieceGroup.getChildren().add(board[x][y].getPiece());             	
            }
        }
    }
    //returns the indicators from Game and adds them to the scene
    private void getIndicators()
    {
    	//calls the getIndicators which calls it within game. It calculates all the indicators
    	ArrayList<MoveIndicator> myIndicators = controller.getIndicators();
    	for(int i = 0; i < myIndicators.size(); i++)
    	{
    		int x = myIndicators.get(i).getX();
    		int y = myIndicators.get(i).getY();
    		
    		indicatorGroup.getChildren().add(myIndicators.get(i));
    		myIndicators.get(i).setOnMouseClicked(e->{
    			onClick(x,y);
    		});
    	}
    }
    //Executed when an indicator is clicked. Creates a piece, adds the piece to the board, 
    //changes the piece color, updates the turn, and recalculates the indicators.
    public void onClick(int x, int y)
    {
    	Piece piece = makePiece(controller.getTurn(),x,y);
		controller.checkMove(x,y);
		ArrayList<Tile> flipList = controller.getFlipList();
		int tileX, tileY = 0;
		for(int j = 0; j < flipList.size(); j++)
		{
			//gets where the tile in the flip list is
			tileX = flipList.get(j).getTileX();
			tileY = flipList.get(j).getTileY();
			System.out.println("tileX: " + tileX + "    tileY:" + tileY);
			//removes the tile from the current board and updates it
			tileGroup.getChildren().remove(board[tileX][tileY]);
			board[tileX][tileY] = flipList.get(j);
			board[tileX][tileY].getPiece().flipPiece();
			tileGroup.getChildren().add(board[tileX][tileY]);
		}
		updateAll(x, y, piece);
		
		if(controller.checkWin() == true)
		{
			time.stop();
			String winner;
			int winCount = 0;
			if(bCount > wCount)
			{
				winner = "Black";
				winCount = bCount;
			}
			else if(wCount > bCount)
			{
				winner = "White";
				winCount = wCount;
			}
			else 
			{
				winner = "No Winner";
			}
			myController.toEndScene(winner, winCount);
			System.out.println("Game Over!");
		}
    }
    private void updateAll(int x, int y, Piece piece)
    {
    	//Clears the flip list within game, deletes all indicators, removes them from view
    			controller.clearFlipList();
    			controller.clearIndicators();
    			indicatorGroup.getChildren().clear();
    			//changes turns
    			controller.changeTurns();
    			//puts piece where clicked
    			board[x][y].setPiece(piece);
    			//adds board to game
    			controller.addBoard(board);
    			pieceGroup.getChildren().add(piece);
    			getIndicators();
    			doLabels();
    }
    private void doLabels()
    {
    	bCount = controller.getPieceCount()[1];
		wCount = controller.getPieceCount()[0];
//		System.out.println("B count" + bCount + "w count: " + wCount);
		root.getChildren().remove(whiteCount);
		root.getChildren().remove(blackCount);
		double percentOne = (double)wCount/((double)wCount + (double)bCount) * 100;
		double percenttwo = (double)bCount/((double)wCount + (double)bCount) * 100;
		DecimalFormat df = new DecimalFormat("#.##");
		whiteCount = new Label("\n\t\tWhite Pieces: " + wCount + "  (" + df.format(percentOne) + "%)");
		whiteCount.setTextFill(Color.WHITE);
	    blackCount = new Label("\n\t\t\t\t\t\t\t\t\t\tBlack Pieces: " + bCount+ "  (" + df.format(percenttwo) + "%)");
	    blackCount.setTextFill(Color.WHITE);
	    root.getChildren().add(whiteCount);
		root.getChildren().add(blackCount);
		seconds = startTime;
		doTime();
    }
    //updates the timers
    private void doTime() 
    { 
    	time.getKeyFrames().clear();
    	KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){ 
    		@Override public void handle(ActionEvent event) 
    		{ 
    			
				if(controller.getTurn() == PieceType.WHITE)
    			{
    				timer1.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\tWhite Time: " + seconds.toString()); 
    			}
    			else
    			{
    				timer2.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\t\tBlack Time: " + seconds.toString());
    			}
    			if(seconds<=0)
    			{ 
    				seconds = startTime; 
    				
    				ArrayList<MoveIndicator> myIndicators = controller.getIndicators();
    				Random r = new Random();
    				int rand = r.nextInt(myIndicators.size());
    				onClick(myIndicators.get(rand).getX(), myIndicators.get(rand).getY());
    			} 
    			seconds--; 
    		} 
    	}); 
    	time.setCycleCount(Timeline.INDEFINITE); 
    	time.getKeyFrames().add(frame);
    	if(time!=null)
    	{ 
    		time.stop(); 
    	} 
    	time.play(); 
    }
    
}