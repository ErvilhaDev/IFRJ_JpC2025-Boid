package com.jpc2025.boids;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boid {
	private Sprite sprite;
	
	public Boid(Texture texture, float x, float y) {
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
        this.sprite.setOriginCenter();
    }
	
	public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
	
	public void update() {
        
    }
	
	public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
	
	public Sprite getSprite() {
        return sprite;
    }
	
}
