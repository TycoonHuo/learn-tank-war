package com.mashibing.tank;

import com.mashibing.TankFrame;

import java.awt.*;

/**
 * 抽离出坦克类，new一个坦克 出来一个。 面向对象思想
 *
 * @author huoguangyao
 * @date 2019-5-18 17:04:21
 */
public class Tank {
    private int x;
    private int y;

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final int SPEED = 10;

    private boolean moving;

    private Dir dir;

    private TankFrame tf;

    /**
     * 初始化坦克的信息
     *
     * @param x   位置x
     * @param y   位置y
     * @param dir 方向，炮筒朝向。子弹的方向和坦克的方向保持一致。
     */
    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.CYAN);

        graphics.fillRect(x, y, WIDTH, HEIGHT);

        if (!moving) {
            return;
        } else {
            move();
        }
        // 把画笔的颜色还原回去，不要污染画笔
        graphics.setColor(c);
    }

    private void move() {
        switch (dir) {
            case DOWN:
                this.y += SPEED;
                break;
            case UP:
                this.y -= SPEED;
                break;
            case LEFT:
                this.x -= SPEED;
                break;
            case RIGHT:
                this.x += SPEED;
            default:
                break;
        }
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        // 开炮了！ 开始new 子弹 先吃饭去
        tf.getBulletList().add(new Bullet(this.x, this.y, this.dir, this.tf));
    }
}
