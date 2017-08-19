package v2.model;

import java.util.LinkedList;
import java.util.List;

import v2.enumerate.Direction;


public class Snake {

	public List<Coordinate> snake;
	
	private Coordinate prevTail;
	public Snake(){
		snake = new LinkedList<Coordinate>();
		Coordinate n1 = new Coordinate(2,1);
		Coordinate n2 = new Coordinate(1,1);
		addHead(n1);
		addTail(n2);
	}
	
	public List<Coordinate> getBody(){
		return snake;
	}
	
	public Coordinate getHead(){
		return snake.get(0);
	}
	
	public int size(){
		return snake.size();
	}
	
	public void move(Direction direction){
		Coordinate head = getHead();
		Coordinate newHead;
		switch(direction){
		case UP: 
			newHead = new Coordinate(head.getX(), head.getY()-1);
			break;
		case DOWN:
			newHead = new Coordinate(head.getX(), head.getY()+1);
			break;
		case LEFT:
			newHead = new Coordinate(head.getX()-1, head.getY());
			break;
		case RIGHT:
			newHead = new Coordinate(head.getX()+1, head.getY());
			break;
		default:
			newHead = new Coordinate(-1, -1);
		}
		addHead(newHead);
		prevTail = removeTail();
	}
	
	public Coordinate eat(Coordinate food){
		if(getHead().equals(food)){
			this.addTail(prevTail);
			return null;
		}
		return food;
	}
	public boolean isWinded(){
		Coordinate head = this.getHead();
		for(int i = 1 ; i<size() ; i++){
			if(head.equals(snake.get(i))) return true;
		}
		return false;
	}
	
	
	public Coordinate getPrevTail(){
		return this.prevTail;
	}
	private void addHead(Coordinate n){
		snake.add(0,n);
	}
	
	private void addTail(Coordinate n){
		snake.add(n);
	}
	
	private Coordinate removeTail(){
		return snake.remove(size()-1);
	}
}
