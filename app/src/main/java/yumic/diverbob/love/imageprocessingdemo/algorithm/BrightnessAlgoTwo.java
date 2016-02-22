package yumic.diverbob.love.imageprocessingdemo.algorithm;

import yumic.diverbob.love.imageprocessingdemo.base.BrightnessAlgo;

/**
 * 亮度调节算法类（模拟实现）
 * Created by Oathkeeper on 2016/2/21.
 */
public class BrightnessAlgoTwo implements BrightnessAlgo {
    @Override
    public String adgustImage(int value) {
        return "用"+this.toString()+"亮度调节为"+value;
    }

    @Override
    public String toString() {
        return "BrightnessAlgoTwo";
    }
}
