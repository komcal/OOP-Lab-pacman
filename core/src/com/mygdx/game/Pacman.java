package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class Pacman {
	private Vector2 position;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_STILL = 0;
    public static final int SPEED = 5;
    private int currentDirection;
    private int nextDirection;
    private World world;
    private LinkedList<DotEattenListener> listeners;
	 
    private static final int [][] DIR_OFFSETS = new int [][] {
        {0,0},
        {0,-1},
        {1,0},
        {0,1},
        {-1,0}
    };
    public Pacman(int x, int y, World world) {
        position = new Vector2(x,y);
        currentDirection = DIRECTION_STILL;
        nextDirection = DIRECTION_STILL;
        this.world = world;
        listeners = new LinkedList<DotEattenListener>();
    }    
 
    public Vector2 getPosition() {
        return position;    
    }
    public void move(int dir) { 
        position.x += SPEED * DIR_OFFSETS[dir][0];
        position.y += SPEED * DIR_OFFSETS[dir][1];
    }
    public void setNextDirection(int dir) {
        nextDirection = dir;
    }
    public boolean isAtCenter() {
        int blockSize = WorldRenderer.BLOCK_SIZE;
 
        return ((((int)position.x - blockSize/2) % blockSize) == 0) &&
                ((((int)position.y - blockSize/2) % blockSize) == 0);
    }
    public void update() {
    	Maze maze = world.getMaze();
        if(isAtCenter()) {
            if(canMoveInDirection(nextDirection)) {
                currentDirection = nextDirection;    
            } else {
                currentDirection = DIRECTION_STILL;    
            }
        	if(maze.hasDotAt(getRow(), getColumn())) {
        		maze.removeDotAt(getRow(), getColumn());
        		notifyDotEattenListeners();
        	}
        }
        position.x += SPEED * DIR_OFFSETS[currentDirection][0];
        position.y += SPEED * DIR_OFFSETS[currentDirection][1];
    }
    private boolean canMoveInDirection(int dir) {
    	Maze maze = world.getMaze();
    	int newRow = getRow() + DIR_OFFSETS[dir][1]; 
        int newCol = getColumn()  + DIR_OFFSETS[dir][0];
        if(!maze.hasWallAt(newRow, newCol)) {
        	return true;
        }	
        return false;
    }
    private int getRow() {
        return ((int)position.y) / WorldRenderer.BLOCK_SIZE; 
    }
 
    private int getColumn() {
        return ((int)position.x) / WorldRenderer.BLOCK_SIZE; 
    }
    public interface DotEattenListener {
        void notifyDotEatten();			
    }
    public void registerDotEattenListener(DotEattenListener l) {
        listeners.add(l);
    }
 
    private void notifyDotEattenListeners() {
        for(DotEattenListener l : listeners) {
            l.notifyDotEatten();
        }
    }
}
