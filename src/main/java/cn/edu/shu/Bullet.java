package cn.edu.shu;

import org.omg.CORBA.PUBLIC_MEMBER;

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
    private static final int SPEED = 6;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private Group group = Group.BAD;//坦克的阵营
//记录子弹的区域属性 用来做碰撞检测  避免每次移动的时候会创建新的对象
    Rectangle rec = new Rectangle();

    private int x,y;
    private Dir dir;

    private boolean living = true;
    TankFrame tf = null;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;

        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }

//        Color c= g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(c);
        //采用圆形作子弹，先获取画笔颜色，后设置颜色，使用后将颜色退回原色

        //加入子弹图片  根据方向画不同的图片
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

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

        //update rec
        rec.x = this.x;
        rec.y = this.y;


        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }
        public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;


        }
}
