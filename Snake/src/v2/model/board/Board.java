package v2.model.board;


import java.util.ArrayList;
import java.util.List;

import v2.enumerate.Direction;
import v2.model.Coordinate;
import v2.model.Snake;
import v2.model.component.Component;

public class Board {

	private List<Component> components;
	private boolean[][] isOccupied;
	
	private Coordinate food;
	private Snake snake;
	
	private Direction currentDirection = Direction.RIGHT;
	
	private int score;
	public final int row;
	public final int column;
	
	public Board(int row, int column){
		this.row = row;
		this.column = column;
		this.setComponents(new ArrayList<Component>());
		
		initBoard();
		initSnake();
		initFood();
	}
	
	private void initBoard(){
		this.isOccupied = new boolean[row][column];
		for(int i = 0 ; i<row ; i++){
			isOccupied[i][0] = true;
			isOccupied[i][column-1] = true;
		}
		for(int i = 0 ; i<column ; i++){
			isOccupied[0][i] = true;
			isOccupied[row-1][i] = true;
		}
	}
	
	private void initSnake(){
		this.setSnake(new Snake());
		updateBoard(getSnake());
	}
	
	private void initFood(){
		setFood(generateFood());
		updateBoard(getFood());
	}
	
	
	public void addComponent(Component component){
		this.getComponents().add(component);
		updateBoard(component);
	}
	

	
	public Coordinate generateFood(){
		while(true){
			int x = (int)(Math.random()*column);
			int y = (int)(Math.random()*row);
			Coordinate node = new Coordinate(x,y);
			System.out.println(node);
			if(!isOccupied[y][x]){ return node;}
		}
	}
	
	public boolean nextRound(){
		getSnake().move(currentDirection);
		if(isBorder(snake.getHead()) || snake.isWinded()) return false;
		for(Component component : this.components){
			if(snake.getHead().equals(component.getCoordinate()) && component.isOver()) return false;
		}
		if(getSnake().eat(getFood())==null){
			setScore(getScore() + 1);
			setFood(generateFood());
			updateBoard(getFood());
		}else{
			updateBoard(getSnake().getHead());
			updateBoard(getSnake().getPrevTail());
		}
		return true;
	}
	public boolean snakeIsSafe(){
		Coordinate head = getSnake().getHead();
		return !isOccupied[head.getY()][head.getX()];
	}
	
	public boolean changeDirection(Direction direction){
		if(currentDirection == Direction.LEFT && direction == Direction.RIGHT) return false;
		if(currentDirection == Direction.RIGHT && direction == Direction.LEFT) return false;
		if(currentDirection == Direction.UP && direction == Direction.DOWN) return false;
		if(currentDirection == Direction.DOWN && direction == Direction.UP) return false;
		currentDirection = direction;
		return true;
	}
	
	public boolean isOccupied(Coordinate coordinate){
		return isOccupied[coordinate.getY()][coordinate.getX()];
		
	}
	private void updateBoard(Component component){
		this.updateBoard(component.getCoordinate());
	}
	
	private void updateBoard(Snake snake){
		for(Coordinate c : snake.getBody()){
			this.updateBoard(c);
		}
	}
	private void updateBoard(Coordinate coordinate){
		isOccupied[coordinate.getY()][coordinate.getX()] = !isOccupied[coordinate.getY()][coordinate.getX()];
	}

	public boolean isBorder(Coordinate coordinate){
		return coordinate.getX()==0 || coordinate.getX()==column-1 || coordinate.getY()==0 || coordinate.getY()==row-1;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Coordinate getFood() {
		return food;
	}

	public void setFood(Coordinate food) {
		this.food = food;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}
	
}
