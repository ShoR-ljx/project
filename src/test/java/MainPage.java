import cn.edu.shu.Dir;
import cn.edu.shu.Group;
import cn.edu.shu.Tank;
import cn.edu.shu.TankFrame;

/**
 * @ Author     ：Liu-jianxiang
 * @ Date       ：Created in 2020/5/8 - 10:20
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class MainPage {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        //初始化敌方tank
        for(int i=0; i<5; i++) {
            tf.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, tf));
        }
        while (true) {
            Thread.sleep(100);
            tf.repaint();
        }
    }
}
