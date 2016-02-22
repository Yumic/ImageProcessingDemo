package yumic.diverbob.love.imageprocessingdemo.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import yumic.diverbob.love.imageprocessingdemo.Config;
import yumic.diverbob.love.imageprocessingdemo.R;
import yumic.diverbob.love.imageprocessingdemo.base.State;
import yumic.diverbob.love.imageprocessingdemo.core.StateManager;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final static String TAG="MainActivity";

    private LinearLayout ll_name;
    private LinearLayout ll_value;
    private LinearLayout ll_button;
    private SeekBar seekBar;

    private String[] stateList = Config.STATE_LIST;
    private String[] nameList =new String[stateList.length];
    private Button[] buttons = new Button[stateList.length];
    private TextView[] nameTextViews = new TextView[stateList.length];
    private TextView[] valueTextViews = new TextView[stateList.length];
    private State states[] = new State[stateList.length];

    private State currentState;
    private StateManager stateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用之前确保StateManager初始化
        stateManager = StateManager.getInstance();
        stateManager.init();
        states = StateManager.getInstance().getStates();
        //布局加载
        initView();
        initListener();
    }

    private void initView(){
        ll_name = (LinearLayout) findViewById(R.id.ll_name);
        ll_value = (LinearLayout) findViewById(R.id.ll_value);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        ll_button = (LinearLayout) findViewById(R.id.ll_button);
        //动态加载部分
        initNameList();
        initButtons();
        initTextView();
    }

    /**
     * 初始化名称列表
     */
    private void initNameList(){
        //从Config中获取中文
        for(int i = 0;i < stateList.length;i++){
            nameList[i] = Config.STATE_NAME_MAP.get(stateList[i]);
        }
    }

    /**
     * 动态添加Button
     */
    private void initButtons(){
        for(int i = 0;i<3;i++){
            buttons[i] = new Button(this);
            buttons[i].setText(nameList[i]);
            buttons[i].setId(i);
            buttons[i].setPadding(2, 4, 2, 4);
            buttons[i].setBackgroundColor(Color.WHITE);
            buttons[i].setOnClickListener(this);
            ll_button.addView(buttons[i],i);
        }
        //默认选择第0个button
        buttons[0].performClick();
    }

    /**
     * 初始化显示区域
     */
    private void initTextView(){
        for(int i = 0;i<3;i++){
            nameTextViews[i] = new TextView(this);
            nameTextViews[i].setText(nameList[i] + ":");
            nameTextViews[i].setTextSize(24);
            nameTextViews[i].setId(i);
            ll_name.addView(nameTextViews[i],i);
        }
        for(int i = 0;i<3;i++){
            valueTextViews[i] = new TextView(this);
            valueTextViews[i].setText("50");
            valueTextViews[i].setTextSize(24);
            valueTextViews[i].setId(i);
            states[i].setTextView(valueTextViews[i]);
            ll_value.addView(valueTextViews[i]);
        }
    }

    /**
     * 给各种View设置监听器
     */
    private void initListener(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //通过stateManager进行值的设置和界面更新
                stateManager.setValue(progress);
                stateManager.update();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //通过stateManager执行要对图像进行的操作
                stateManager.execute();
            }
        });
    }

    /**
     * Button的点击事件处理，在此进行不同模式的切换
     * @param v
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        //Button颜色的简单处理
        for(Button button:buttons){
            button.setBackgroundColor(Color.WHITE);
        }
        v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //设置当前状态
        currentState = stateManager.setState(states[id]);
        Log.d(TAG, "" + currentState.toString());
        //设置seekBar的值
        seekBar.setProgress(currentState.getValue());
    }
}
