package yumic.diverbob.love.imageprocessingdemo;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件
 * Created by Oathkeeper on 2016/2/22.
 */
public class Config {
    //状态注册表，在数组中添加要使用的状态的类名
    public static final String[] STATE_LIST ={
            "BrightnessState",
            "HueState",
            "SaturabilityState"
    } ;

    //状态和算法的注册表--指定在某状态下使用哪种图像算法,Key为状态类的类名，Value为算法实现类的类名
    //此后可以在这里很方便地扩展
    public static final Map<String,String> STATE_ALGO_REGISTRY = new HashMap<String,String>(){
        {
            put(STATE_LIST[0],"BrightnessAlgoTwo");
            put(STATE_LIST[1],"HueAlgoOne");
            put(STATE_LIST[2],"SaturabilityAlgoOne");
        }
    };

    //模式和对应显示文字的映射
    public static final Map<String,String> STATE_NAME_MAP = new HashMap<String,String>(){
        {
            put(STATE_LIST[0],"亮度");
            put(STATE_LIST[1],"色彩");
            put(STATE_LIST[2],"饱和度");
        }
    };

    //包名前缀
    public static final String PACKAGE_PREFIX =
            "yumic.diverbob.love.imageprocessingdemo.";

    //状态的包名前缀
    public static final String STATE_PACKAGE_PREFIX =
            PACKAGE_PREFIX+"state.";

    //算法的包名前缀
    public static final String ALGO_PACKAGE_PREFIX =
            PACKAGE_PREFIX+"algorithm.";
}
