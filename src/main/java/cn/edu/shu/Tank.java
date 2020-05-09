package cn.edu.shu;

import java.awt.*;
import java.awt.peer.ScrollPanePeer;
import java.nio.channels.Pipe;
import java.text.CollationElementIterator;
import java.util.Random;
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
    private static final int SPEED = 2;

    private static int WIDTH = ResourceMgr.goodTankU.getWidth();
    private static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    Rectangle rec = new Rectangle();

    private Random random = new Random();

    private boolean moving = false;
    private TankFrame tf  = null;
    private boolean living = true;
    private Group group = Group.BAD;

    public Tank(int x, int y ,Dir dir, Group group,TankFrame tf) {
        super();
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
//        Color c = g.getColor();
//        g.setColor(Color.YELLOW);
//        g.fillRect(x,y,50,50);
//        g.setColor(c);

        if (!living) tf.tanks.remove(this);

        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

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
        //让子弹从tank中心位置射出
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group,this.tf));

        if (this.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }



}
