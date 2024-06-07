package org.example.main;

import org.example.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // USTAWIENIA EKRANU
    final int originalTileSize = 16; // 16x16 rozmiar gracza npc oraz kafelków na mapie;
    final int scale = 3; // skalowanie dla monitorów
    public int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; // liczba w kafelków kolumnie kafelków na ekranie
    final  int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 px
    final int screenHeight = tileSize * maxScreenRow; // 576 px

    final  int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    //stworzenie zegaru gry
    public void  startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {


            //aktualizacja innformacji o zdarzeniach
            update();

            //rysownaie ekranu
            repaint();



            try {
                double remainingTime = (nextDrawTime - System.nanoTime());
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void update() {
        player.update();
        }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
