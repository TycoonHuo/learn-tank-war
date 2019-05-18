package com.mashibing.tank;

import com.mashibing.TankFrame;

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
    private static final int BULLET_WIDTH = 10;
    private static final int BULLET_HEIGHT = 10;
    private int speed = 10;
    private Dir dir;
    private TankFrame tf;
    /**
     * 子弹出了画面就删掉，防止内存泄漏。java也是有内存泄漏的 一般出现于容器里的引用没有删除。
     */
    private boolean live = true;


    Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.magenta);
        g.fillOval(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        move();
        g.setColor(color);
    }

    private void move() {
        if (x < 0 || y < 0 || x > tf.getWidth() || y > tf.getHeight()) {
            this.live = false;
        } else {
            this.live = true;
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

    public boolean isLive() {
        return live;
    }
}
