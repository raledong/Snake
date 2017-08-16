package v1.model;

import v1.enumerate.Direction;

public class Board {

	final int row;
	final int column;
	boolean[][] status;
	Snake snake;
	Node food;
	int score;
	
	Direction currentDirection = Direction.RIGHT;
	
	public Board(int row, int column){
		this.row = row;
		this.column = column;
		status = new boolean[row][column];
		for(int i = 0 ; i<row ; i++){status[i][0] = true; status[i][column-1]=true;}
		for(int j = 0 ; j<column ; j++){status[0][j]=true;status[row-1][j]=true;}
		
		initSnake();
		initFood();
	}
	
	public void initSnake(){
		snake = new Snake();
		updateBoard(snake);
	}
	
	public void initFood(){
		food = generateFood();
		updateBoard(food);
	}
	
	public Node generateFood(){
		while(true){
			int x = (int)(Math.random()*column);
			int y = (int)(Math.random()*row);
			Node node = new Node(x,y);
			System.out.println(node);
			if(!isOccupied(node)){ return node;}
		}
	}
	
	public boolean isBorder(Node node){
		return node.getX()==0 || node.getX()==column-1 || node.getY()==0 || node.getY()==row-1;
	}
	
	public boolean changeDirection(Direction direction){
		if(currentDirection == Direction.LEFT && direction == Direction.RIGHT) return false;
		if(currentDirection == Direction.RIGHT && direction == Direction.LEFT) return false;
		if(currentDirection == Direction.UP && direction == Direction.DOWN) return false;
		if(currentDirection == Direction.DOWN && direction == Direction.UP) return false;
		currentDirection = direction;
		return true;
	}
	
	public boolean nextRound(){
		snake.move(currentDirection);
		if(isBorder(snake.getHead()) || snake.isWinded()) return false;
		if(snake.eat(food)==null){
			score++;
			food = generateFood();
			updateBoard(food);
		}else{
			updateBoard(snake.getHead());
			updateBoard(snake.getPrevTail());
		}
		return true;
	}
	
	public boolean isOccupied(Node node){
		return status[node.getY()][node.getX()];
	}
	
	public void updateBoard(Node node){
		status[node.getY()][node.getX()] = !status[node.getY()][node.getX()];
	}
	
	public void updateBoard(Snake snake){
		for(Node node : snake.getBody()){
			status[node.getY()][node.getX()] = true;
		}
	}
	
	
	public int getRow(){
		return this.row;
	}
	
	public int getColumn(){
		return this.column;
	}
	
	public Snake getSnake(){
		return this.snake;
	}
	
	public Node getFood(){
		return this.food;
	}
	
	public int getInterval(){
		return 150 - 15 * (score/5);
	}
	
	public int getScore(){
		return this.score;
	}
}
