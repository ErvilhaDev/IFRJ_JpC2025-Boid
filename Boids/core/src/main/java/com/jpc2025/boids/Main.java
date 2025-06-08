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
    private Texture image;
    private Texture boidTexture;
    
    private ArrayList<Boid> boids;

    @Override
    public void create() {
        batch = new SpriteBatch();
        boidTexture = new Texture("bird.png");
        boids = new ArrayList<>();
        
        boids.add(new Boid(boidTexture, 100, 150));
        
    }
    
    

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        
        for (Boid boid : boids) {
            boid.update();
        }
        
        batch.begin();
        for (Boid boid : boids) {
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
