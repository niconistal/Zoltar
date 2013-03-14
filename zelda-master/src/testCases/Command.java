package testCases;

import zelda.engine.Game;

public abstract class Command {
	
	protected Game game ;
	
	public Command( Game game ) {
		this.game = game ;
	}
	
	public abstract void execute() ;
	
	public abstract void finish() ;

}
