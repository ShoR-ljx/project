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

        while (true) {
            Thread.sleep(100);
            tf.repaint();
        }
    }
}