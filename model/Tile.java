package model;
import controller.OthelloController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import view.OthelloAppView;

public class Tile extends Rectangle {

    private Piece piece;
    private MoveIndicator moveIndicator;
    private int x;
    private int y;
    private OthelloController controller;
    public MoveIndicator getMoveIndicator() {
		return moveIndicator;
	}

	public int getTileX() {
		return x;
	}

	public int getTileY() {
		return y;
	}

	public void setMoveIndicator(MoveIndicator moveIndicator) {
		this.moveIndicator = moveIndicator;
	}
	
	public boolean hasMoveIndicator() {
		return moveIndicator != null;
	}
	
	public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public Tile(int x, int y, OthelloController controller) {
    	this.controller = controller;
        setWidth(OthelloAppView.TILE_SIZE);
        setHeight(OthelloAppView.TILE_SIZE);
        this.x = x;
        this.y = y;
        relocate(x * OthelloAppView.TILE_SIZE + 50, y * OthelloAppView.TILE_SIZE + 50);

        setFill(Color.DARKOLIVEGREEN);
        setStrokeType(StrokeType.INSIDE);
        setStroke(Color.BLACK);
        
    	setOnMouseClicked(e->{
    		if(hasMoveIndicator())
            {
    			Piece piece = new Piece(moveIndicator.getTurn(), x, y);
    			setPiece(piece);
//    			controller.setPiece(piece);
    			System.out.println("x:" + x + "  y" + y);
            }
    		//System.out.println("x:" + x + "  y" + y);
    	});
    }
}