package org.example.entity;


import org.example.main.GamePanel;
import org.example.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDeafaultValues();
        getPlayerImage();
    }
    public void  setDeafaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void  getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyH.upPressed) {
            y -= speed;
            direction = "up";
        }
        if(keyH.downPressed) {
            y += speed;
            direction = "down";
        }
        if(keyH.leftPressed) {
            x -= speed;
            direction = "left";
        }
        if(keyH.rightPressed) {
            x += speed;
            direction = "right";
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case"up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case"down":
                if (spriteNum == 2) {
                    image = up2;
                } if (spriteNum == 2) {
                image = up2;
            }
                break;
            case"left":
                image = left1;
                break;
            case"right":
                image = right1;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
