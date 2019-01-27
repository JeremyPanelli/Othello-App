package model;
import static view.OthelloAppView.TILE_SIZE;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends StackPane {

    private PieceType type;
    private Circle circle;
    private double mouseX, mouseY;
    private double oldX, oldY;

    public PieceType getType() {
        return type;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);
        
        circle = new Circle(TILE_SIZE * 0.3125);
        
        
        circle.setStrokeWidth(TILE_SIZE * 0.03);

        circle.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        circle.setTranslateY((TILE_SIZE - TILE_SIZE * 0.3125 * 2) / 2);
        
        if(type == PieceType.BLACK)
        {
        	circle.setFill(Color.BLACK);
        }
        else
        {
        	circle.setFill(Color.WHITE);
        }
        getChildren().addAll(circle);
    }
    
    public void flipPiece()
    {
    	if(type == PieceType.BLACK)
    	{
    		setType(PieceType.WHITE);
    		circle.setFill(Color.WHITE);
    	}
    	else
    	{
    		setType(PieceType.BLACK);
    		circle.setFill(Color.BLACK);
    	}
    }
    
    public void setType(PieceType type) {
		this.type = type;
	}

	public void move(int x, int y) {
        oldX = x * TILE_SIZE + 50;
        oldY = y * TILE_SIZE + 50;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
}