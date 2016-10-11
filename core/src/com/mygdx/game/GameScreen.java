package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {
	private Texture pacmanImg;
	private PacmanGame pacmanGame;
	private Pacman pacman;
	private WorldRenderer worldRenderer;
	private World world;
	 
    public GameScreen(PacmanGame pacmanGame) {
        this.pacmanGame = pacmanGame;
        pacmanImg = new Texture("pacman.png");
        world = new World(pacmanGame);
        worldRenderer = new WorldRenderer(pacmanGame, world);
    }
    
    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	update(delta);
    	worldRenderer.render(delta);
    }
    public void update(float delta) {
		Pacman pacman = world.getPacman();
    	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
    		pacman.setNextDirection(Pacman.DIRECTION_LEFT);
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	pacman.setNextDirection(Pacman.DIRECTION_RIGHT);
        }
        if(Gdx.input.isKeyPressed(Keys.UP)) {
        	pacman.setNextDirection(Pacman.DIRECTION_UP);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
        	pacman.setNextDirection(Pacman.DIRECTION_DOWN);
        }
        world.update(delta);
    }
}
