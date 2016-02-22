package yumic.diverbob.love.imageprocessingdemo.base;

import android.util.Log;
import android.widget.TextView;

import yumic.diverbob.love.imageprocessingdemo.core.ImageProcessor;

/**
 * 抽象状态类，包装了对图片的操作
 * 每个状态通过状态管理器动态地绑定图像算法
 * Created by Oathkeeper on 2016/2/21.
 */
public abstract class State {
    protected int value = 50;
    protected final static String TAG="State";
    protected TextView textView;
    protected ImageAlgo imageAlgo;

    public void setImageAlgo(ImageAlgo imageAlgo) {
        this.imageAlgo = imageAlgo;
        Log.d(TAG, getClass().getName() + "设置了算法" + imageAlgo.toString());
    }

    /**
     * 执行相应算法
     */
    public  void execute(){
        String result = ImageProcessor.getInstance().processImage(value, imageAlgo);
        Log.d(TAG,result);
    }

    /**
     * 算法执行结束后更新界面
     */
    public  void update(){
        //更新界面，这里先用TextView来展示
        textView.setText(""+value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public TextView getTextView() {
        return textView;
    }
}

