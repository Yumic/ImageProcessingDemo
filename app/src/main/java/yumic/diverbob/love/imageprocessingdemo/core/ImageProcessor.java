package yumic.diverbob.love.imageprocessingdemo.core;

import android.util.Log;
import yumic.diverbob.love.imageprocessingdemo.base.ImageAlgo;


/**
 * 图片处理器，负责对图片执行指定的算法
 * Created by Oathkeeper on 2016/2/21.
 */
public class ImageProcessor {
    private final static String TAG = "ImageProcessor";
    private ImageProcessor(){}

    /**
     * 根据传入的参数和当前的图像调节器对图像进行处理，返回一个BitMap(模拟实现时先用String)
     * @param value
     * @param iamgeAlgo
     * @return
     */
    public String processImage(int value , ImageAlgo iamgeAlgo){
        Log.d(TAG, "当前算法：" + iamgeAlgo.toString() + "  值：" + value);
        return iamgeAlgo.adgustImage(value);
    }

    /**
     * 采用单例模式
     * @return
     */
    public static ImageProcessor getInstance(){
        Log.d(TAG,"getInstance()");
        return Holder.sInstance;
    }

    private static class Holder{
        private static final ImageProcessor sInstance = new ImageProcessor();
    }
}
