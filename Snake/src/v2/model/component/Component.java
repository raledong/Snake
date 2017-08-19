package v2.model.component;

import v2.model.Coordinate;

public abstract class Component implements ComponentAction{
	
	private Coordinate coordinate;
	
	public Component(Coordinate coordinate){
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public int getX(){
		return coordinate.getX();
	}
	
	public int getY(){
		return coordinate.getY();
	}
	
}
