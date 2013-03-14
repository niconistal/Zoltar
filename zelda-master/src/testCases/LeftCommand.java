package testCases;

import zelda.engine.Game;

public class LeftCommand extends Command{

	public LeftCommand(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		this.game.setaPressed(true) ;
	}

	@Override
	public void finish() {
		this.game.setaPressed(false) ;
		
	}
	
	

}
