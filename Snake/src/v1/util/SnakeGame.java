package v1.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import v1.controller.GameController;
import v1.model.Board;
import v1.ui.GameView;

public class SnakeGame{
    Board board = new Board(30, 30);
	GameView gameView;
	public void init() {

        //创建游戏窗体
        JFrame window = new JFrame("贪吃蛇游戏");

        // 画出棋盘和贪吃蛇
        Container contentPane = window.getContentPane();

        // 基于Grid初始化gamaView
        gameView = new GameView(board);
        gameView.init();

        // 设置gameView中JPanel的大小 Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT
        gameView.getCanvas().setPreferredSize(new Dimension(Settings.CANVAS_HEIGHT,Settings.CANVAS_WIDTH));

        // 将gameView中JPanel加入到窗口中
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
        
        GameController gameController = new GameController(board, gameView);
        window.addKeyListener(gameController);
        

        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
//        while(true){
//			try {
//                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
//            } catch (InterruptedException e) {
//                break;
//            }
//			if(!board.nextRound()) {break;}
//			gameView.getCanvas().repaint();
//		}
//        window.removeKeyListener(gameController);
//        gameView.showGameOverMessage();
        new Thread(gameController).start();
    }

    public static void main(String[] args) {
    	SnakeGame game = new SnakeGame();
    	game.init();
    }
}
