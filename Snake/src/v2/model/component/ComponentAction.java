package v2.model.component;

import v2.model.Coordinate;

public interface ComponentAction {
	
	boolean isOver();
	
	Coordinate next();
}
