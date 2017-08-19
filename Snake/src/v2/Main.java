package v2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import v2.controller.GameController;
import v2.model.Game;
import v2.model.board.Board;
import v2.model.board.BoardFactory;
import v2.ui.BoardView;
import v2.util.Settings;


public class Main {
	Game game = new Game(1, 5);
	BoardView gameView;
	public void init() {

        //创建游戏窗体
        JFrame window = new JFrame("多等级贪吃蛇游戏");

        // 画出棋盘和贪吃蛇
        Container contentPane = window.getContentPane();

        // 基于Grid初始化gamaView
        gameView = new BoardView(game.getBoard());
        gameView.init();

        // 设置gameView中JPanel的大小 Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT
        gameView.getCanvas().setPreferredSize(new Dimension(Settings.CANVAS_HEIGHT,Settings.CANVAS_WIDTH));

        // 将gameView中JPanel加入到窗口中
        contentPane.add(gameView.getCanvas(), BorderLayout.CENTER);
        
        GameController gameController = new GameController(game, gameView);
        window.addKeyListener(gameController);
        

        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        
        while(!game.pass()){
			try {
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
			if(!game.getBoard().nextRound()) {break;}
			gameView.getCanvas().repaint();
		}
        window.removeKeyListener(gameController);
        gameView.showGameOverMessage();
    }

    public static void main(String[] args) {
    	Main game = new Main();
    	game.init();
    }
}
