package cn.edu.shu;

import java.awt.*;
import java.awt.peer.ScrollPanePeer;
import java.nio.channels.Pipe;
import java.text.CollationElementIterator;
import java.util.logging.XMLFormatter;

/**
 * @ Author     ：Liu-jianxiang
 * @ Date       ：Created in 2020/5/8 - 9:13
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class Tank {
    //设置tank的大小和初始方向
    //对于不想二次加载的属性使用static对于不想其他人员修改的成员变量使用final对于不想外部类访问的变量使用private
    private int x,y;
    private Dir dir =Dir.DOWN;
    private static final int SPEED = 5;

    private boolean moving = false;

    private TankFrame tf  = null;

    public Tank(int x, int y ,Dir dir, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);

        move();
    }

    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;//差点忘了加break，防止switch穿透....
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.tf));
    }


}
