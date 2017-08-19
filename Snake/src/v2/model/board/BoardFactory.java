package v2.model.board;

import v2.model.Coordinate;
import v2.model.component.Block;
import v2.model.component.Component;
import v2.util.Settings;

public class BoardFactory {

	public static Board getBoard(int level){
		switch(level){
		case 1 : return initLevel1Board();
		
		}
		return null;
	}
	
	private static Board initLevel1Board(){
		Board board = new Board(Settings.BOARD_DEFAULT_HEIGHT, Settings.BOARD_DEFAULT_WIDTH);
		Component block1 = new Block(new Coordinate(3,3));
		board.addComponent(block1);
		Component block2 = new Block(new Coordinate(3,4));
		board.addComponent(block2);
		Component block3 = new Block(new Coordinate(3,5));
		board.addComponent(block3);
		
		return board;
	}
}
