package com.jpc2025.boids;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture boidTexture;
    
    private ArrayList<Boid> boids;
    private ArrayList<BoidParalelo> boidsParalelo;
    
    private int arrayBoids = 2000;
    
    private BitmapFont font;
    
    private boolean threadMode = false;
    
    private long totalTime = 0;
    private int frameCount = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        boidTexture = new Texture("bird.png");
        font = new BitmapFont();
        
        boids = new ArrayList<>();
        boidsParalelo = new ArrayList<>();
        
                
        for (int i = 0; i < arrayBoids; i++) {
			float x = (float)Math.random() * Gdx.graphics.getWidth();
			float y = (float)Math.random() * Gdx.graphics.getHeight();
			boids.add(new Boid(boidTexture, x, y));
		}
        
        
        
    }

    @Override
    public void render() {
    	
    	long startTime = System.nanoTime();

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
        	threadMode = !threadMode;
        }
    	
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        
        if (threadMode) {
            int numThreads = 10;
            int chunkSize = boids.size() / numThreads;
            ArrayList<BoidParalelo> threads = new ArrayList<>();
            
            for (int i = 0; i < numThreads; i++) {
                int start = i * chunkSize;
                int end = (i == numThreads - 1) ? boids.size() : (start + chunkSize);
                List<Boid> chunk = boids.subList(start, end);
                BoidParalelo t = new BoidParalelo(chunk, boids, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                threads.add(t);
                t.start();
            }
            
            for (BoidParalelo t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            for (Boid boid : boids) {
                boid.render(batch);
            }
        }
        
        
        else {
        	for (Boid boid : boids) {
                boid.update(boids, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                boid.render(batch);
            }
        }
        
        //FPS
        long frameTime = System.nanoTime() - startTime;
        totalTime += frameTime;
        frameCount++;
        float avgFrameMs = (totalTime / 1_000_000f) / frameCount;

        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        font.draw(batch, "Avg Frame Time: " + String.format("%.2f ms", avgFrameMs), 10, 40);
        font.draw(batch, "Modo: " + (threadMode ? "Paralelo" : "Sequencial"), 10, 60);
        
        batch.end();
    }

    @Override
    public void dispose() {
    	batch.dispose();
        boidTexture.dispose();
        font.dispose();
    }
}
