package yumic.diverbob.love.imageprocessingdemo.core;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import yumic.diverbob.love.imageprocessingdemo.Config;
import yumic.diverbob.love.imageprocessingdemo.base.ImageAlgo;
import yumic.diverbob.love.imageprocessingdemo.base.State;

/**
 * 状态管理器，应用了状态模式的设计思想，将各个状态对象包装起来，
 * 保存所有的状态并定义了当前的状态
 * Created by Oathkeeper on 2016/2/21.
 */
public class StateManager {
    private final static String TAG = "StateManager";

    private State state;
    private State[] states;
    private String[] stateName = Config.STATE_LIST;

    private StateManager(){}
    /**
     * 进行状态类的加载和算法类的绑定
     */
    public void init(){
        loadStateClass();
        bindingAlgo();
    }

    /**
     * 执行对图片的操作，根据运行时state的类执行相应方法
     */
    public void execute(){
        state.execute();
    }

    /**
     * 更新界面，根据运行时state的类执行相应方法
     */
    public void update(){
        state.update();
    }

    public State getState() {
        return state;
    }

    public State setState(State state) {
        this.state = state;
        return state;
    }
    public void setValue(int value){
        state.setValue(value);
    }

    public State[] getStates() {
        return states;
    }

    /**
     * 根据配置文件Config.java加载具体的State类
     */
    private void loadStateClass() {
        states = new State[stateName.length];
        //根据状态名列表加载状态类
        for(int i  = 0;i< stateName.length;i++ ){
            try {
                Class<?> cls = Class.forName(Config.STATE_PACKAGE_PREFIX + stateName[i]);
                Constructor<?> cons[] = cls.getConstructors();
                states[i] = (State)cons[0].newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, "指定的类名" + stateName[i] + "不存在，请检查Config.java中的配置");
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将状态与要使用的算法进行绑定
     */
    private void bindingAlgo() {
        for(int i = 0;i< stateName.length;i++){
            try{
                //找到状态要绑定的算法名
                String algoName = Config.STATE_ALGO_REGISTRY.get(stateName[i]);
                Class<?> cls = Class.forName(Config.ALGO_PACKAGE_PREFIX + algoName);
                Constructor<?> cons[] = cls.getConstructors();
                ImageAlgo imageAlgo = (ImageAlgo)cons[0].newInstance();
                //将状态与算法进行绑定
                states[i].setImageAlgo(imageAlgo);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, "指定的类名" + stateName[i] + "不存在，请检查Config.java中的配置");
            }catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * StateManager为单例，用此方法取得唯一实例
     * @return
     */
    public static StateManager getInstance(){
        return Holder.sInstance;
    }

    private static class Holder{
        private static final StateManager sInstance = new StateManager();
    }
}
