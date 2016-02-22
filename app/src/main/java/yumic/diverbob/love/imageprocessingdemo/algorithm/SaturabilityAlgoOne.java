package yumic.diverbob.love.imageprocessingdemo.algorithm;

import yumic.diverbob.love.imageprocessingdemo.base.SaturabilityAlgo;

/**
 * 饱和度调节算法类（模拟实现）
 * Created by Oathkeeper on 2016/2/21.
 */
public class SaturabilityAlgoOne implements SaturabilityAlgo {
    @Override
    public String adgustImage(int value) {
        return "用"+this.toString()+"饱和度调节为"+value;
    }

    @Override
    public String toString() {
        return "SaturabilityAlgoOne";
    }
}
