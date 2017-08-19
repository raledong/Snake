package v2.model;

import v2.model.board.Board;
import v2.model.board.BoardFactory;

public class Game {
	
	private final int currentLevel;
	private Board board;
	
	private final int targetScore;
	public Game(final int currentLevel, int targetScore){
		this.currentLevel = currentLevel;
		this.setBoard(BoardFactory.getBoard(currentLevel));
		this.targetScore = targetScore;
	}
	
	public boolean nextRound(){

		return getBoard().nextRound();
	}

	public boolean pass(){
		return getBoard().getScore() == targetScore;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
