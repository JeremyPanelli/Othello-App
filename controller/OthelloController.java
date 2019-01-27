package controller;

import java.util.ArrayList;

import Othello.OthelloApp;
import model.Game;
import model.MoveIndicator;
import model.Piece;
import model.PieceType;
import model.Tile;
import view.OthelloStartScene;

public class OthelloController {
	private Game game;
	public OthelloController(Tile[][] board)
	{
		game = new Game(board);
	}
	public OthelloController()
	{
		game = new Game();
	}
	public void checkMove(int x, int y)
	{
		game.checkMove(x, y);
	}
	public void clearFlipList()
	{
		game.clearFlipList();
	}
	public ArrayList<Tile> getFlipList()
	{
		return game.getFlipList();
	}
	public void addBoard(Tile[][] board)
	{
		game.addBoard(board);
	}
	//returns the player who's turn it is
	public PieceType getTurn()
	{
		return game.getTurn();
	}
	
	//Update the board within the Game 
	public void setBoard(Tile[][] board)
	{
		game.updateBoard(board);
	}
	//returns the current board
	public Tile[][] getBoard()
	{
		return game.getBoard();
	}
	//Gets the indicators that tell where the player can move
	public ArrayList<MoveIndicator> getIndicators()
	{
		game.calculateIndicators();
		return game.getIndicators();
	}
	public void clearIndicators()
	{
		game.clearIndicators();
	}
	public int[] getPieceCount()
	{
		return game.getPieceCount();
	}
	public void changeTurns()
	{
		game.changeTurns();
	}
	public boolean checkWin()
	{
		return game.checkWin();
	}
	public void deleteIndicators()
	{
		game.deleteIndicators();
	}
}
