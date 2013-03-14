package testCases;

import zelda.engine.Game;

public class DownCommand extends Command{

	public DownCommand(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		this.game.setsPressed(true) ;
	}

	@Override
	public void finish() {
		this.game.setsPressed(false) ;
		
	}
	
	

}
