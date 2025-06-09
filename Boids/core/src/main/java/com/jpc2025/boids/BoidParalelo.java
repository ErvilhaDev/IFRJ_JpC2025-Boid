package com.jpc2025.boids;

import java.util.List;

public class BoidParalelo extends Thread {
    private List<Boid> boidsChunk;
    private List<Boid> allBoids;
    private float screenWidth, screenHeight;
    
    public BoidParalelo(List<Boid> boidsChunk, List<Boid> allBoids, float screenWidth, float screenHeight) {
        this.boidsChunk = boidsChunk;
        this.allBoids = allBoids;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void run() {
        for (Boid boid : boidsChunk) {
            boid.update(allBoids, screenWidth, screenHeight);
        }
    }
}
