package v2.model;

public class Coordinate {

	private int X;
	private int Y;
	
	public Coordinate(int X, int Y){
		this.X = X;
		this.Y = Y;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(obj==this) return true;
		if(obj!=null && obj.getClass()==this.getClass()){
			Coordinate coordinate = (Coordinate)obj;
			if(coordinate.getX()==this.getX() && coordinate.getY()==this.getY()) return true;
		}
		return false;
	}
	
}
