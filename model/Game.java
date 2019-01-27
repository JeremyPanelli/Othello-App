package model;

import java.util.ArrayList;

public class Game {
	public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board;
    private PieceType turn;
    private ArrayList<MoveIndicator> indicators;
    private boolean[][] possibleMoves;
	private ArrayList<Tile> flipList;
    
	public ArrayList<Tile> getFlipList() {
		return flipList;
	}
	public Game(Tile[][] board)
	{
		indicators = new ArrayList<MoveIndicator>();
		possibleMoves = new boolean[WIDTH][HEIGHT];
		flipList = new ArrayList<Tile>();
		turn = PieceType.WHITE;
		this.board = board;
	}
	public Game()
	{
		indicators = new ArrayList<MoveIndicator>();
		possibleMoves = new boolean[WIDTH][HEIGHT];
		flipList = new ArrayList<Tile>();
		turn = PieceType.WHITE;
	}
	
	public void addBoard(Tile[][] board)
	{
		this.board = board;
	}
    public ArrayList<MoveIndicator> getIndicators() {
		return indicators;
	}

	//Calculates the current arraylist of indicators
    public void calculateIndicators()
    {
    	MoveIndicator indicator = null;
    	possibleMoves = calcPossibleMoves();
    	for(int i = 0; i < HEIGHT; i++ )
    	{
    		for(int j = 0; j < WIDTH; j++)
    		{
    			if(possibleMoves[i][j])
    			{
    				indicator = new MoveIndicator(i, j, turn);
    				indicators.add(indicator);
    			}
    		}
    	}
    }
    public boolean[][] getPossibleMoves() {
		return possibleMoves;
	}
    //Change turns within the game
	public void switchTurn()
    {
    	indicators.clear();
    	if(turn == PieceType.BLACK)
    	{
    		turn = PieceType.WHITE;
    	}
    	else
    	{
    		turn = PieceType.BLACK;
    	}
    	calculateIndicators();
    	checkWin();
    }
    
    public boolean checkWin()
    {
    	if(indicators.isEmpty())
    	{
    		return true;
    	}
    	return false;
    }
    
  //Returns the number of pieces for each color. First element in the array is white and the second is black
  	public int[] getPieceCount()
  	{
  		int numPieces[] = new int[2];
  		numPieces[0] = 0;
  		numPieces[1] = 0;
  		for(int i = 0; i < WIDTH; i++)
  		{
  			for(int j = 0; j < HEIGHT; j++)
  			{
  				if(!board[i][j].hasPiece())
  				{
  					continue;
  				}
  				else if(board[i][j].getPiece().getType() == PieceType.WHITE)
  				{
  					numPieces[0]++;
  				}
  				else if(board[i][j].getPiece().getType() == PieceType.BLACK)
  				{
  					numPieces[1]++;
  				}
  			}
  		}
  		return numPieces;
  	}
  	
  //Player makes their move and the pieces are moved based on the flipList created in checkMove
  	public void move(int x, int y)
  	{
  		if(possibleMoves[x][y])
  		{
  			checkMove(x,y);
  			for(int i = 0; i < flipList.size(); i++)
  			{
  				flipList.get(i).getPiece().flipPiece();
  			}
  			flipList.clear();
  		}
  	}
  	
  public PieceType getTurn() {
		return turn;
	}

	public void setTurn(PieceType turn) {
		this.turn = turn;
	}

	//Returns an array of booleans where the corresponding position in the board is true if it is a possible move
  	public boolean[][] calcPossibleMoves()
  	{
  		possibleMoves = new boolean[WIDTH][HEIGHT];
  		for(int i = 0; i < HEIGHT; i++)
  		{
  			for(int j = 0; j < WIDTH; j++)
  			{
  				//check every possible move in the board
  				possibleMoves[i][j] = checkMove(i, j);
//  				if(possibleMoves[i][j])
//  					System.out.println("TRUE!!! x: " + i + "  Y: " + j);
  			}
  		}
  		//checking every move creates a large fliplist that we need to clear
  		flipList.clear();
  		return possibleMoves;
  	}
  	
  //This method checks if a particular move is possible for the color who's turn it is
  	public boolean checkMove(int x, int y)
  	{
  		PieceType enemy;
  		if(turn == PieceType.WHITE)
  		{
  			enemy = PieceType.BLACK;
  		}
  		else
  		{
  			enemy = PieceType.WHITE;
  		}
  		if(board[x][y].hasPiece())
  		{
  			return false;
  		}
  		else 
  		{
  			//Check every direction of the board
  			boolean isTrue = false;
  			if(iterateCheck(x,y,1,0,enemy)) isTrue = true;
  			if(iterateCheck(x,y,-1,0,enemy)) isTrue = true;
  			if(iterateCheck(x,y,1,1,enemy)) isTrue = true;
  			if(iterateCheck(x,y,-1,-1,enemy)) isTrue = true;
  			if(iterateCheck(x,y,1,-1,enemy)) isTrue = true;
  			if(iterateCheck(x,y,-1,1,enemy)) isTrue = true;
  			if(iterateCheck(x,y,0,1,enemy)) isTrue = true;
  			if(iterateCheck(x,y,0,-1,enemy)) isTrue = true;
  			return isTrue;
  		}
  	}
  	
  	//Check if a move is possible in any direction
  	//Condition - there has to be at least one enemy piece in between your piece
  	private boolean iterateCheck(int x, int y, int dirX, int dirY, PieceType enemy)
  	{
  		int enemyCount = 0;
  		ArrayList<Tile> tilesToAdd = new ArrayList<Tile>();
  		if(x + dirX < 0 || y + dirY < 0 ||  x + dirX > 7 || y + dirY > 7)
  		{
  			return false;
  		}
  		if(board[x + dirX][y + dirY].hasPiece() && board[x + dirX][y + dirY].getPiece().getType() == turn)
  		{
  			return false;
  		}
  		//single for loop based on where the piece starts out and which direction you need to head
  		for(int i = x + dirX, j = y + dirY; i < WIDTH && i >= 0 && j < HEIGHT && j >= 0; i+=dirX, j+=dirY )
  		{
  			if(!board[i][j].hasPiece())
  			{
  				break;
  			}
  			//increment if we encounter an enemy in the direction we are heading
  			if(board[i][j].getPiece().getType() == enemy)
  			{
  				enemyCount++;
  				tilesToAdd.add(board[i][j]);
  			}
  			//stop condition for the loop -- if there are enemies that we passed and we reach our own piece, 
  			//add to flip list 
  			else if(enemyCount > 0 && board[i][j].getPiece().getType() == turn)
  			{
  				//Add to the flip list of the legal move
  				for(int a = 0; a < tilesToAdd.size(); a++)
  				{
  					flipList.add(tilesToAdd.get(a));
  				}
  				return true;
  			}
  		}
  		return false;
  	}
  	
  	//Update the Board to a new Board
    public void updateBoard(Tile[][] board)
    {
    	this.board = board;
    }
    
    //Returns the current board
    public Tile[][] getBoard()
    {
    	return board;
    }
    //clears the flip list
    public void clearFlipList()
    {
    	flipList.clear();
    }
    //clears all the indicators
    public void clearIndicators()
    {
    	indicators.clear();
    }
    //switches turns
    public void changeTurns()
    {
    	if(this.turn == PieceType.BLACK)
    	{
    		this.turn = PieceType.WHITE;
    	}
    	else
    	{
    		this.turn = PieceType.BLACK;
    	}
    }
    public void deleteIndicators()
    {
    	indicators.clear();
    }
}
