package com.mashibing;

import com.mashibing.tank.MyTank;

/**
 * 第一天坦克大战
 *
 * @author huoguangyao
 * @date 2019-5-18 15:02:46
 */
public class Main {

    public static void main(String[] args) {
        MyTank tank = new MyTank();
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            tank.repaint();
        }
    }
}
