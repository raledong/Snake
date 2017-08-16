package v1.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import v1.enumerate.Direction;
import v1.model.Board;
import v1.ui.GameView;


public class GameController implements KeyListener, Runnable{
	Board board;
	GameView gameView;
	boolean running;
	
	public GameController(Board board, GameView gameView){
		this.board  = board;
		this.gameView = gameView;
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
		if(board.nextRound()){
			System.out.println(board.getSnake().getHead());
			gameView.getCanvas().repaint();}
		
	}
	@Override
	public void run() {
		while(running){
			try {
                Thread.sleep(board.getInterval());
            } catch (InterruptedException e) {
                break;
            }
			System.out.println("interval"+board.getInterval() + "score" + board.getScore());
			if(!board.nextRound()) {break;}
			gameView.getCanvas().repaint();
		}
		gameView.showGameOverMessage();
		running = false;
	}
}
