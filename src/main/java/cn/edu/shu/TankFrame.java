package cn.edu.shu;



import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @ Author     ：Liu-jianxiang
 * @ Date       ：Created in 2020/5/8 - 9:19
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class TankFrame extends Frame {

        Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD,this);
        List<Bullet> bullets = new ArrayList<>();
        public List<Tank> tanks = new ArrayList<>();
        List<Explode> explodes = new ArrayList<>();

        static final int GAME_WIDTH = 1080,GAME_HEIGHT = 960;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MykeyListenr());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            }
        });
    }

    //双缓存解决屏幕刷新率高，屏闪问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void  paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        //观察屏幕现存子弹数量的变化，观察子弹的生命周期
        g.drawString("子弹的数量："+bullets.size(),10,60);
        g.drawString("敌人的数量："+tanks.size(),10,80);
        g.drawString("爆炸的数量："+explodes.size(),10,100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //collision detect

        for (int i = 0; i <bullets.size() ; i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }

        }
    }






//这里让自定义类继承Adapter抽象类，就只需要重写自己需要的方法了
    //内部类是因为其他的类不需要使用这个类，独属于本类的
    class MykeyListenr extends KeyAdapter {
    boolean bL = false;
    boolean bU = false;
    boolean bR = false;
    boolean bD = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;

            default:
                break;
        }

        setMainTankDir();
//新增坦克移动音效
        new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
    }

@Override
    public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
        case KeyEvent.VK_LEFT:
            bL = false;
            break;
        case KeyEvent.VK_UP:
            bU = false;
            break;
        case KeyEvent.VK_RIGHT:
            bR = false;
            break;
        case KeyEvent.VK_DOWN:
            bD = false;
            break;

        case KeyEvent.VK_CONTROL:
            myTank.fire();
            break;

        default:
            break;
    }

    setMainTankDir();
    }
//这里使用内部类，因为要调用这个方法必然得先调用myKeyListener方法，且需要使用类的成员变量
    private void setMainTankDir() {
        if (!bL && !bU && !bR && !bD) myTank.setMoving(false);
        else {
            myTank.setMoving(true);

            if (bL) myTank.setDir(Dir.LEFT);
            if (bU) myTank.setDir(Dir.UP);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bD) myTank.setDir(Dir.DOWN);

        }
    }

}

}