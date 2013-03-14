package testCases;

import zelda.engine.Game;

public class BowCommand extends Command{

	public BowCommand(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		this.game.setkPressed(true) ;
	}

	@Override
	public void finish() {
		this.game.setkPressed(false) ;
		
	}
	
	

}
