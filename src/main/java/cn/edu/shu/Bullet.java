package cn.edu.shu;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.text.CollationElementIterator;

/**
 * @ Author     ：Liu-jianxiang
 * @ Date       ：Created in 2020/5/8 - 9:13
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class Bullet {
    private static final int SPEED = 20;
    private static int WIDTH = 10, HEIGHT = 10;

    private int x,y;
    private Dir dir;

    private boolean live = true;
    TankFrame tf = null;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.bullets.remove(this);
        }

        Color c= g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }

}
