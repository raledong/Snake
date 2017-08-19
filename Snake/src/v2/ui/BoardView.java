package v2.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import v2.model.board.Board;
import v2.model.component.Component;
import v2.model.Coordinate;
import v2.model.Snake;
import v2.util.Settings;

public class BoardView {

	private final Board board;
	
	public BoardView(Board board) {
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
	                drawComponents(graphics, board.getComponents());
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
		 for(Coordinate node : snake.getBody()){
			 graphics.setColor(Color.BLUE);
			 graphics.fill3DRect(node.getX()*Settings.CELL_LENGTH, node.getY()*Settings.CELL_LENGTH, Settings.CELL_LENGTH, Settings.CELL_LENGTH, true);
		 }
	 }

	 public void drawFood(Graphics graphics, Coordinate node) {
		 graphics.setColor(Color.RED);
		 graphics.fill3DRect(node.getX()*Settings.CELL_LENGTH, node.getY()*Settings.CELL_LENGTH, Settings.CELL_LENGTH, Settings.CELL_LENGTH, true);
	 }

	 public void drawGridBackground(Graphics graphics) {
		 for (int i = 0; i < board.row; i++) {
	            //内层循环控制的是列数即当前坐标的横坐标x
	            for (int j = 0; j < board.column; j++) {
	                //如果是墙壁，则设置墙壁的颜色为深灰色
	                  if(board.isBorder(new Coordinate(j, i)))    
	                       graphics.setColor(Color.DARK_GRAY);
	                  else
	                      //如果是活动区域则设置为白色
	                      graphics.setColor(Color.WHITE);
	                  //在对应的panel上绘制30*40（即1200个）正方形(20*20)的单元格从而构成我们的游戏界面
	                  graphics.fill3DRect(j*Settings.CELL_LENGTH, i*Settings.CELL_LENGTH, Settings.CELL_LENGTH, Settings.CELL_LENGTH, true);
	            }
	        }
	 }
	 
	 public void drawComponents(Graphics graphics, List<Component> components){
		 for(Component tempComponent : components){
			 Coordinate c = tempComponent.getCoordinate();
			 graphics.setColor(Color.BLACK);
			 graphics.fill3DRect(c.getX()*Settings.CELL_LENGTH, c.getY()*Settings.CELL_LENGTH, Settings.CELL_LENGTH, Settings.CELL_LENGTH, true);
		 }
	 }
	 public void showGameOverMessage() {
		    JOptionPane.showMessageDialog(null, "游戏结束", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
		}
	
}
