package com.jpc2025.boids;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture boidTexture;
    
    private ArrayList<Boid> boids;
    
    private final int arrayBoids = 500;

    @Override
    public void create() {
        batch = new SpriteBatch();
        boidTexture = new Texture("bird.png");
        boids = new ArrayList<>();
        
        for (int i = 0; i < arrayBoids; i++) {
			float x = (float)Math.random() * Gdx.graphics.getWidth();
			float y = (float)Math.random() * Gdx.graphics.getHeight();
			boids.add(new Boid(boidTexture, x, y));
		}
        
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        for (Boid boid : boids) {
			boid.update(boids, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			boid.render(batch);
		}
        batch.end();
    }

    @Override
    public void dispose() {
    	batch.dispose();
        boidTexture.dispose();
    }
}
