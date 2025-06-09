package com.jpc2025.boids;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boid {
	private Sprite sprite;
	
	public float x, y;
	public float vx, vy;
	
	// Parameters
	float minspeed = 3f;
	float maxspeed = 6f;
	float turnfactor = 1f;
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
        this.x = x;
        this.y = y;
        this.vx = (float)(Math.random() * 2 - 1);
        this.vy = (float)(Math.random() * 2 - 1);
        sprite.setOriginCenter();
    }
	
	public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
	
	public void update(List<Boid> allBoids, float screenWidth, float screenHeight) {
		float close_dx = 0, close_dy = 0;
		float xpos_avg = 0, ypos_avg = 0;
		float xvel_avg = 0, yvel_avg = 0;
		int neighboring_boids = 0;
		
		for (Boid other : allBoids) {	
				if (other == this) continue;
				float dx = this.x - other.x;
				float dy = this.y - other.y;
				
				if (Math.abs(dx) < visualRange && Math.abs(dy) < visualRange) {
					float distanceSquared = dx * dx + dy * dy;
					
					if (distanceSquared < protectedRange * protectedRange) {
						close_dx += dx;
						close_dy += dy;
					}
					else if (distanceSquared < visualRange * visualRange) {
						xpos_avg += other.x;
						ypos_avg += other.y;
						xvel_avg += other.vx;
						yvel_avg += other.vy;
						neighboring_boids++;
					}
				}
			}
		
		if (neighboring_boids > 0) {
			xpos_avg /= neighboring_boids;
			ypos_avg /= neighboring_boids;
			xvel_avg /= neighboring_boids;
			yvel_avg /= neighboring_boids;
			this.vx += (xpos_avg - this.x) * centeringfactor + (xvel_avg - this.vx) * matchingfactor;
			this.vy += (ypos_avg - this.y) * centeringfactor + (yvel_avg - this.vy) * matchingfactor;
		}
		this.vx += close_dx * avoidfactor;
		this.vy += close_dy * avoidfactor;
		
		//Wall
		if (this.y < 0) vy += turnfactor;
		if (this.y > screenHeight) vy -= turnfactor;
		if (this.x < 0) vx += turnfactor;
		if (this.x > screenWidth) vx -= turnfactor;
		
		// Bias
		vx = (1 - biasval) * vx + biasval * 1;
		
		float speed = (float)Math.sqrt(vx * vx + vy * vy);
		if (speed < minspeed) {
			vx = (vx / speed) * minspeed;
			vy = (vy / speed) * minspeed;
		}
		if (speed > maxspeed) {
			vx = (vx / speed) * maxspeed;
			vy = (vy / speed) * maxspeed;
		}
        
		x += vx;
		y += vy;
		sprite.setPosition(x, y);
		sprite.setRotation((float)Math.toDegrees(Math.atan2(vy, vx)));
		
		
    }
	
}
