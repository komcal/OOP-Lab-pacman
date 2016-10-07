package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private Texture pacmanImg;
	private PacmanGame pacmanGame;
	 
    public GameScreen(PacmanGame pacmanGame) {
        this.pacmanGame = pacmanGame;
        pacmanImg = new Texture("pacman.png");
    }
    
    @Override
    public void render(float delta) {
        SpriteBatch batch = pacmanGame.batch;
        batch.begin();
        batch.draw(pacmanImg, 100, 100);
        batch.end();
    }
}
