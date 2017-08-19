package v2.model.component;

import v2.model.Coordinate;

public class Block extends Component{
	
	public Block(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public boolean isOver() {
		return true;
	}

	@Override
	public Coordinate next() {
		return null;
	}

}
