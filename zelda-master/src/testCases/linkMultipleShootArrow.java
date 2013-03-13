package testCases;

import static org.junit.Assert.*;

import java.lang.Thread.State;

import javax.swing.JFrame;


import org.junit.Before;
import org.junit.Test;

import zelda.Controller;
import zelda.TestController;
import zelda.View;
import zelda.enemy.BlueSoldier;
import zelda.enemy.GhostSoldier;
import zelda.enemy.PasiveBehavior;
import zelda.enemy.PatrolBehavior;
import zelda.enemy.Soldier;
import zelda.enemy.StandState;
import zelda.engine.GObject;
import zelda.engine.Game;
import zelda.engine.Scene;
import zelda.items.Arrow;
import zelda.karacter.Direction;
import zelda.link.Link;
import zelda.link.SwordState;
import zelda.link.WalkState;
import zelda.scene.ZeldaScene;

public class linkMultipleShootArrow {

	private Game game;
	private View view;
	private TestController ctl;
	private JFrame mainView;
	private Soldier malo1,malo2,malo3,malo4;
	
	@Before
	public void setUp(){
		game = new Game();
		mainView = new JFrame();
		mainView.setIgnoreRepaint(true);
		mainView.setUndecorated(true);
		
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainView.setVisible(false);

		
		
		Scene testScene = game.initScene("TestScene");
		//A�adimos los malos
		
		malo1 = new BlueSoldier(game, game.getWidth()/2, game.getHeight()/2+80, Direction.DOWN,5);
		PasiveBehavior maloBehave = new PasiveBehavior(malo1);
		malo1.setBehavior(maloBehave);
		malo1.setState(new StandState(malo1));
		testScene.addGObject(malo1);
		malo2 = new BlueSoldier(game, game.getWidth()/2, game.getHeight()/2-80, Direction.UP,5);
		malo2.setBehavior(maloBehave);
		malo2.setState(new StandState(malo2));
		testScene.addGObject(malo2);
		malo3 = new BlueSoldier(game, game.getWidth()/2-80, game.getHeight()/2, Direction.LEFT,5);
		malo3.setBehavior(maloBehave);
		malo3.setState(new StandState(malo3));
		testScene.addGObject(malo3);
		malo4 = new BlueSoldier(game, game.getWidth()/2+80, game.getHeight()/2, Direction.RIGHT,5);
		malo4.setBehavior(maloBehave);
		malo4.setState(new StandState(malo4));
		testScene.addGObject(malo4);
		
		
		Link link = game.getLink();
		link.setX(game.getWidth()/2);
		link.setY(game.getHeight()/2);
		game.setScene(testScene);
		view = new View(game, mainView);
		ctl = new TestController(game, view, mainView);
		
	}
	@Test
	public void test() throws InterruptedException {
		
		
		Link link = game.getLink();
		//StandState ss = new StandState(link) ;
		
		Thread.sleep(1000);
		game.setkPressed(true);
		link.handleInput();
		game.setkPressed(false);
		
		game.getScene().handleInput();
		
		Thread.sleep(100);

		
		game.setaPressed(true);
		link.handleInput();
		
		Thread.sleep(100);
		
		game.setkPressed(true);
		link.handleInput();
		
		game.getScene().handleInput();



		
		

		
		Thread.sleep(1000);
	
	
	
	}

}
