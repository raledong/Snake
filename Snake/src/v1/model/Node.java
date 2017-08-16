package v1.model;

public class Node {

	private int x;
	private int y;
	
	public Node(){
		
	}
	
	public Node(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj){
		if(obj==this) return true;
		if(obj!=null && obj.getClass()==this.getClass()){
			Node node = (Node)obj;
			if(node.getX() == this.getX() && node.getY() == this.getY()) return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "("+this.getX()+","+this.getY()+")";
	}
}
