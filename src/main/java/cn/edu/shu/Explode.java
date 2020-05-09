package cn.edu.shu;

import java.awt.*;
import java.lang.management.ThreadInfo;
import java.util.Scanner;

/**
 * @ Author     ：Liu-jianxiang
 * @ Date       ：Created in 2020/5/9 - 11:31
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGTH = ResourceMgr.explodes[0].getHeight();

    private int x,y;

    TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()-> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if(step >= ResourceMgr.explodes.length)
            tf.explodes.remove(this);


    }


}
