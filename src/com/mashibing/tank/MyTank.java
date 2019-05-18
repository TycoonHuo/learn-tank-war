package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 继承Frame 我理解这里的目的是为了调用paint 使用那根画笔
 */
public class MyTank extends Frame {
    private int x = 200;
    private int y = 200;

    private static final int SPEED = 10;

    public MyTank() {
        setVisible(true);
        setSize(800, 600);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            // 监听关闭窗口的事件 让系统退出
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 监听按键，上下左右，记录状态
        addKeyListener(new KeyAdapter() {
            private boolean left, right, up, down;

            private void move() {
                if (left) {
                    x -= SPEED;
                }
                if (up) {
                    y -= SPEED;
                }
                if (down) {
                    y += SPEED;
                }
                if (right) {
                    x += SPEED;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        left = true;
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
                    default:
                        break;
                }
                move();
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
//                move();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
    }
}
