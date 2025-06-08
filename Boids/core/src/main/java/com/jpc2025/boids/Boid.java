package com.jpc2025.boids;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boid {
	private Sprite sprite;
	
	// Parameters
	float minspeed = 3f;
	float maxspeed = 6f;
	float turnfactor = 0.2f;
	float visualRange = 40f;
	float protectedRange = 8f;
	float centeringfactor = 0.0005f;
	float avoidfactor = 0.05f;
	float matchingfactor = 0.05f;
	float maxbias = 0.01f;
	float bias_increment = 0.00004f;
	float biasval = 0.001f;
	
	// Separation
	float close_dx;
	float close_dy;
	
	//Alignment
	float xvel_avg;
	float yvel_avg;
	float neighboring_boids;
	
	//Cohesion
	float xpos_avg = 0f;
	float ypos_avg = 0f;
	
	
	
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
