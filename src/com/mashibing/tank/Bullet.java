package com.mashibing.tank;

import java.awt.*;

/**
 * 子弹类
 *
 * @author huogunagyao
 * @date 2019-5-18 18:10:08
 */
public class Bullet {
    private int x;
    private int y;
    private static final int BULLET_WIDTH = 5;
    private static final int BULLET_HEIGHT = 5;
    private int speed = 10;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }


    public void paint(Graphics g) {
        Color color = g.getColor();
        g.fillOval(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        move();
        g.setColor(color);
    }

    private void move() {
        switch (dir) {
            case RIGHT:
                this.x += speed;
                break;
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case DOWN:
                this.y += speed;
                break;
            default:
                break;
        }
    }
}
