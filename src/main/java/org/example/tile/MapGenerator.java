package org.example.tile;

import java.util.Random;

public class MapGenerator {
    private int[][] map;
    private int width, height;

    public MapGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        map = new int[height][width];
    }

    public int[][] generateMap() {
        initializeMap();
        generateMaze(0, 0);
        return map;
    }

    private void initializeMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = 1; // 1 represents a wall
            }
        }
    }

    private void generateMaze(int x, int y) {
        map[y][x] = 0; // 0 represents a passage
        Random rand = new Random();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newX = x + (dx[i] * 2);
            int newY = y + (dy[i] * 2);

            if (isInBounds(newX, newY) && map[newY][newX] == 1) {
                map[newY / 2 * dy[i] + y][newX / 2 * dx[i] + x] = 0;
                generateMaze(newX, newY);
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}