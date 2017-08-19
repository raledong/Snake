package v2.model.component;

import v2.model.Coordinate;

public class Gateway extends Component{

	private Coordinate toPortal;
	public Gateway(Coordinate c1, Coordinate c2){
		super(c1);
		toPortal = c2;
	}
	
	@Override
	public boolean isOver() {
		return false;
	}

	@Override
	public Coordinate next() {
		return toPortal;
	}
	
	
	public Coordinate getToPortal() {
		return toPortal;
	}
	public void setToPortal(Coordinate toPortal) {
		this.toPortal = toPortal;
	}

}
