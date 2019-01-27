package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static view.OthelloAppView.TILE_SIZE;

import javafx.scene.layout.StackPane;
public class MoveIndicator extends StackPane {
	private Circle indicator;
	private PieceType turn;
	//private Tile tile;
	private int x;
	private int y;
	
	public MoveIndicator(int x, int y, PieceType turn)
	{
		//this.tile = tile;
		this.x = x;
		this.y = y;
		this.turn = turn;
		relocate(x * TILE_SIZE + 50, y * TILE_SIZE + 50);
		indicator = new Circle(TILE_SIZE * 0.3125);
		indicator.setStrokeWidth(0.6);
		indicator.setStroke(Color.BLACK);
		indicator.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
		indicator.setTranslateY((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
		indicator.setFill(Color.DARKOLIVEGREEN);
        indicator.setOpacity(0.5);
        getChildren().add(indicator);
        
        setOnMouseEntered(e ->{
        	if(turn == PieceType.BLACK)
        	{
        		indicator.setFill(Color.BLACK);
        	}
        	else
        	{
        		indicator.setFill(Color.WHITE);
        	}
        });
        setOnMouseExited(e ->{
        	indicator.setFill(Color.DARKOLIVEGREEN);
        });
        setOnMouseClicked(e ->{
        	System.out.println("x:" + x + "  y" + y);
        	//tile.setPiece(makePiece());
        });
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public PieceType getTurn() {
		return turn;
	}

	public void setTurn(PieceType turn) {
		this.turn = turn;
	}

	public Piece makePiece()
	{
		Piece piece = new Piece(turn, x, y);
		piece.move(x, y);
        return piece;
	}
}
