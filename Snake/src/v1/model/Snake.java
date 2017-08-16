package v1.model;

import java.util.LinkedList;
import java.util.List;

import v1.enumerate.Direction;

public class Snake {

	public List<Node> snake;
	
	private Node prevTail;
	public Snake(){
		snake = new LinkedList<Node>();
		Node n1 = new Node(2,1);
		Node n2 = new Node(1,1);
		addHead(n1);
		addTail(n2);
	}
	
	public List<Node> getBody(){
		return snake;
	}
	
	public Node getHead(){
		return snake.get(0);
	}
	
	public int size(){
		return snake.size();
	}
	
	public void move(Direction direction){
		Node head = getHead();
		Node newHead;
		switch(direction){
		case UP: 
			newHead = new Node(head.getX(), head.getY()-1);
			break;
		case DOWN:
			newHead = new Node(head.getX(), head.getY()+1);
			break;
		case LEFT:
			newHead = new Node(head.getX()-1, head.getY());
			break;
		case RIGHT:
			newHead = new Node(head.getX()+1, head.getY());
			break;
		default:
			newHead = new Node(-1, -1);
		}
		addHead(newHead);
		prevTail = removeTail();
	}
	
	public Node eat(Node food){
		if(getHead().equals(food)){
			this.addTail(prevTail);
			return null;
		}
		return food;
	}
	public boolean isWinded(){
		Node head = this.getHead();
		for(int i = 1 ; i<size() ; i++){
			if(head.equals(snake.get(i))) return true;
		}
		return false;
	}
	
	
	public Node getPrevTail(){
		return this.prevTail;
	}
	private void addHead(Node n){
		snake.add(0,n);
	}
	
	private void addTail(Node n){
		snake.add(n);
	}
	
	private Node removeTail(){
		return snake.remove(size()-1);
	}
}
