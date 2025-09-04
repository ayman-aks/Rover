package com.rover.model;

public record Plateau(int maxX, int maxY) {
    public Plateau {
        if (maxX < 0 || maxY < 0) {
            throw new IllegalArgumentException("Plateau size must be positive");
        }
    }
}
