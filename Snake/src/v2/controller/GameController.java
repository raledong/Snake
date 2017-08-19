package v2.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import v2.enumerate.Direction;
import v2.model.Game;
import v2.model.board.Board;
import v2.ui.BoardView;
import v2.util.Settings;

public class GameController implements KeyListener, Runnable{
	Board board;
	BoardView boardView;
	boolean running;
	Game game;
	public GameController(Game game,  BoardView boardView){
		this.game = game;
		this.board  = game.getBoard();
		this.boardView = boardView;
		this.running = true;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:    
            board.changeDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            board.changeDirection(Direction.RIGHT);
        case 102: //小键盘的数字6
        case 54:  //大键盘的数字6
            
            break;
        case KeyEvent.VK_UP:
            board.changeDirection(Direction.UP);
            break;
        case KeyEvent.VK_DOWN:
            board.changeDirection(Direction.DOWN);
            break;
        default:
            break;
        }
		if(game.nextRound()){
			boardView.getCanvas().repaint();
			running = game.pass();
//			System.out.println(game.pass());
		}
		
	}
	@Override
	public void run() {
		while(running){
			try {
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
			if(game.pass()||!board.nextRound()) {break;}
			boardView.getCanvas().repaint();
		}
		boardView.showGameOverMessage();
		running = false;
	}
}
