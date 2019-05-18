package com.mashibing;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Dir;
import com.mashibing.tank.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 这个类就是一个大画布。
 * 里面的坦克和子弹 都面向对象 new出来
 * 继承Frame 我理解这里的目的是为了调用paint 使用那根画笔
 *
 * @author huoguangyao
 */
public class TankFrame extends Frame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private static Tank tank = new Tank(200, 200, Dir.DOWN);

    private Bullet b;

    TankFrame() {
        setVisible(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            // 监听关闭窗口的事件 让系统退出
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


//        监听按键，上下左右，记录状态
        addKeyListener(new KeyAdapter() {
            private boolean left, right, up, down;

            // 根据按键的状态改变坦克的方向。
            private void setTankDir() {
                if (!left && !right && !up && !down) {
                    tank.setMoving(false);
                } else {
                    tank.setMoving(true);
                    if (left) {
                        tank.setDir(Dir.LEFT);
                    }
                    if (up) {
                        tank.setDir(Dir.UP);
                    }
                    if (down) {
                        tank.setDir(Dir.DOWN);
                    }
                    if (right) {
                        tank.setDir(Dir.RIGHT);
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // 按下Ctrl开炮
                    case KeyEvent.VK_CONTROL:
                        b = new Bullet(tank.getX(), tank.getY(), tank.getDir());
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        break;
                    case KeyEvent.VK_UP:
                        up = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = true;
                        break;
                    default:
                        break;
                }

                setTankDir();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                    case KeyEvent.VK_UP:
                        up = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = false;
                        break;
                    default:
                        break;
                }
                setTankDir();
            }
        });

    }

    /**
     * 双缓冲代码 解决闪烁
     */
    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    @Override
    public void paint(Graphics g) {
        // 画出主站坦克, 他自己画。 面向对象
        tank.paint(g);
        if (b != null) {
            b.paint(g);
        }
    }
}
