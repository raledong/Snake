package v1.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import v1.model.Board;
import v1.model.Node;
import v1.model.Snake;
import v1.util.Settings;

public class GameView {

	private final Board board;
	
	public GameView(Board board) {
	       this.board = board;
	       init();
	 }
	 
	private JPanel canvas;
	
	public void init() {
	        canvas = new JPanel() {
	            @Override
	            public void paintComponent(Graphics graphics) {
	                drawGridBackground(graphics);
	                drawSnake(graphics, board.getSnake());
	                drawFood(graphics, board.getFood());
	            }
	        };
	 }
	
	 public void draw() {
	     canvas.repaint();
	 }

	 public JPanel getCanvas() {
	     return canvas;
	 }
	 
	 public void drawSnake(Graphics graphics, Snake snake) {
		 for(Node node : snake.getBody()){
			 graphics.setColor(Color.BLUE);
			 graphics.fill3DRect(node.getX()*Settings.CELL_LENGTH, node.getY()*Settings.CELL_LENGTH, Settings.CELL_LENGTH, Settings.CELL_LENGTH, true);
		 }
	 }

	 public void drawFood(Graphics graphics, Node node) {
		 graphics.setColor(Color.RED);
		 graphics.fill3DRect(node.getX()*Settings.CELL_LENGTH, node.getY()*Settings.CELL_LENGTH, Settings.CELL_LENGTH, Settings.CELL_LENGTH, true);
	 }

	 public void drawGridBackground(Graphics graphics) {
		 for (int i = 0; i < board.getRow(); i++) {
	            //内层循环控制的是列数即当前坐标的横坐标x
	            for (int j = 0; j < board.getColumn(); j++) {
	                //如果是墙壁，则设置墙壁的颜色为深灰色
	                  if(board.isBorder(new Node(j, i)))    
	                       graphics.setColor(Color.DARK_GRAY);
	                  else
	                      //如果是活动区域则设置为白色
	                      graphics.setColor(Color.WHITE);
	                  //在对应的panel上绘制30*40（即1200个）正方形(20*20)的单元格从而构成我们的游戏界面
	                  graphics.fill3DRect(j*Settings.CELL_LENGTH, i*Settings.CELL_LENGTH, Settings.CELL_LENGTH, Settings.CELL_LENGTH, true);
	            }
	        }
	 }
	 
	 public void showGameOverMessage() {
		    JOptionPane.showMessageDialog(null, "游戏结束", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
		}
	
}
