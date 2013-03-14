package testCases;

import zelda.engine.Game;

public class SwordCommand extends Command{

	public SwordCommand(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		this.game.setjPressed(true) ;
	}

	@Override
	public void finish() {
		this.game.setjPressed(false) ;
		
	}
	
	

}
